package services

import java.util.concurrent.Executors

import cats.data.Reader
import com.outworkers.phantom.ResultSet
import entities.Book
import repository.BookRepository

import scala.concurrent.{ExecutionContext, Future}

sealed trait BookServiceAlg {
  def createBook(book: Book): Reader[BookRepository, Future[ResultSet]]

  def deleteBook(book: Book): Reader[BookRepository, Future[ResultSet]]

  def updateBook(book: Book): Reader[BookRepository, Future[ResultSet]]

  def searchBook(isbn: String): Reader[BookRepository, Future[Option[Book]]]
}

sealed trait BookService extends BookServiceAlg {
  private implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))

  override def createBook(book: Book): Reader[BookRepository, Future[ResultSet]] = Reader {
    repository: BookRepository =>
      repository.searchBook(book.isbn)
        .map(optionBook => optionBook.fold {
          repository.saveBook(book)
        } { b => {
          Future(throw new Exception("isbn existente"))
        }
        })
        .flatten
  }

  override def deleteBook(book: Book): Reader[BookRepository, Future[ResultSet]] = Reader {
    repository: BookRepository =>
      repository.searchBook(book.isbn)
        .map(ob => ob.fold {
          throw new Exception("libro inexistente")
        } { b => repository.deleteBook(b) })
        .flatten
  }

  override def updateBook(book: Book): Reader[BookRepository, Future[ResultSet]] = Reader {
    repository: BookRepository =>
      repository.searchBook(book.isbn)
        .map(ob => ob.fold {
          throw new Exception("libro inexistente")
        } { b => repository.updateBook(b) }).flatten
  }

  override def searchBook(isbn: String): Reader[BookRepository, Future[Option[Book]]] = Reader {
    repository: BookRepository =>
      repository.searchBook(isbn)
  }
}

object BookService extends BookService