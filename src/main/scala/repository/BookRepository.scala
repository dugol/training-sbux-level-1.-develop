package repository

import java.util.concurrent.Executors

import com.outworkers.phantom.ResultSet
import com.outworkers.phantom.connectors.KeySpace
import entities.Book

import scala.concurrent.{ExecutionContext, Future}


trait BookRepository{
  def saveBook(book:Book):Future[ResultSet]
  def deleteBook(book: Book):Future[ResultSet]
  def updateBook(book: Book):Future[ResultSet]
  def searchBook(isbn:String):Future[Option[Book]]
}
object RealBookRepository extends BookRepository{
  private implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
  private implicit val session = LocalDatabase.session
  private implicit val keySpace = KeySpace(LocalDatabase.keyspace.name)

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
//object BookRepository extends BookRepository

object TestBookRepository extends BookRepository{
  private implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
  override def saveBook(book: Book): Future[ResultSet] = {

    Future(ResultSet(ResultSet(null,null),null))
  }

  override def deleteBook(book: Book): Future[ResultSet] = {

    Future(ResultSet(ResultSet(null,null),null))
  }

  override def updateBook(book: Book): Future[ResultSet] = {
    Future(ResultSet(ResultSet(null,null),null))

  }

  override def searchBook(isbn: String): Future[Option[Book]] = {
    Future(Option(Book("test","test","test","test","test","test",0)))
  }
}