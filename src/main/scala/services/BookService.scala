package services

import java.util.concurrent.Executors

import com.outworkers.phantom.ResultSet
import entities.Book
import repository.BookRepository

import scala.concurrent.{ExecutionContext, Future}

sealed trait BookServiceAlg {
  def createBook(book: Book): Future[ResultSet]

  def deleteBook(book: Book): Future[ResultSet]

  def updateBook(book: Book): Future[ResultSet]

  def searchBook(isbn: String): Future[Option[Book]]
}

sealed trait BookService extends BookServiceAlg {
  private implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))

  override def createBook(book: Book): Future[ResultSet] = {
    BookRepository.searchBook(book.isbn)
      .map(optionBook => optionBook.fold {
        BookRepository.saveBook(book)
      } { b => {
        Future(throw new Exception("isbn existente"))
      }
      })
      .flatten
    //BookRepository.saveBook(book)
  }

  override def deleteBook(book: Book): Future[ResultSet] = {
    BookRepository.searchBook(book.isbn)
      .map(ob => ob.fold {
        throw new Exception("libro inexistente")
      } { b => BookRepository.deleteBook(b) })
      .flatten
    //BookRepository.deleteBook(book)
  }

  override def updateBook(book: Book): Future[ResultSet] = {
    BookRepository.searchBook(book.isbn)
      .map(ob => ob.fold {
        throw new Exception("libro inexistente")
      } { b => BookRepository.updateBook(b) }).flatten
  }

  override def searchBook(isbn: String): Future[Option[Book]] = {
    BookRepository.searchBook(isbn)
  }
}

object BookService extends BookService