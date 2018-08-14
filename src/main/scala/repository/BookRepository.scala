package repository

import java.util.concurrent.Executors

import com.outworkers.phantom.ResultSet
import com.outworkers.phantom.connectors.KeySpace
import entities.Book

import scala.concurrent.{ExecutionContext, Future}


sealed trait BookRepositoryAlg{
  def saveBook(book:Book):Future[ResultSet]
  def deleteBook(book: Book):Future[ResultSet]
  def updateBook(book: Book):Future[ResultSet]
  def searchBook(isbn:String):Future[Option[Book]]
}
sealed trait BookRepository extends BookRepositoryAlg{
  implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
  implicit val session = LocalDatabase.session
  implicit val keySpace = KeySpace(LocalDatabase.keyspace.name)

  override def saveBook(book: Book): Future[ResultSet] = {
    LocalDatabase.books.saveBook(book)
  }

  override def deleteBook(book: Book): Future[ResultSet] ={
    LocalDatabase.books.deleteBook(book)
  }

  override def updateBook(book: Book):Future[ResultSet]={
    LocalDatabase.books.updateBook(book)
  }

  override def searchBook(isbn: String): Future[Option[Book]] ={
    LocalDatabase.books.searchBook(isbn)
  }
}
object BookRepository extends BookRepository