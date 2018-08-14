package services

import entities.Book
import repository.BookRepository

import scala.concurrent.Future

sealed trait BookServiceAlg{
  def createBook(book:Book)
  def deleteBook(book:Book)
  def updateBook(book:Book)
  def searchBook(isbn:String):Future[Option[Book]]
}
sealed trait BookService extends BookServiceAlg{
  override def createBook(book: Book): Unit = {
    BookRepository.saveBook(book)
  }

  override def deleteBook(book: Book): Unit = {
    BookRepository.deleteBook(book)
  }

  override def updateBook(book: Book): Unit = {
    BookRepository.updateBook(book)
  }

  override def searchBook(isbn: String): Future[Option[Book]] = {
    BookRepository.searchBook(isbn)
  }
}

object BookService extends BookService