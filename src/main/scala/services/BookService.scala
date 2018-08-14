package services

import com.outworkers.phantom.ResultSet
import entities.Book
import repository.BookRepository

import scala.concurrent.Future

sealed trait BookServiceAlg{
  def createBook(book:Book):Future[ResultSet]
  def deleteBook(book:Book):Future[ResultSet]
  def updateBook(book:Book):Future[ResultSet]
  def searchBook(isbn:String):Future[Option[Book]]
}
sealed trait BookService extends BookServiceAlg{
  override def createBook(book: Book): Future[ResultSet] = {
    BookRepository.saveBook(book)
  }

  override def deleteBook(book: Book): Future[ResultSet] = {
    BookRepository.deleteBook(book)
  }

  override def updateBook(book: Book): Future[ResultSet] = {
    BookRepository.updateBook(book)
  }

  override def searchBook(isbn: String): Future[Option[Book]] = {
    BookRepository.searchBook(isbn)
  }
}

object BookService extends BookService