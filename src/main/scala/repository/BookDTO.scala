package repository

import com.outworkers.phantom.dsl._
import entities.Book

import scala.concurrent.Future

abstract class BookDTO extends Table[BookDTO,Book] with RootConnector {

  override def tableName:String="book"
  object isbn extends StringColumn with PartitionKey
  object tittle extends StringColumn
  object author extends StringColumn
  object gender extends StringColumn
  object publisher extends StringColumn
  object country extends StringColumn
  object edition extends IntColumn

  def saveBook(book: Book): Future[ResultSet] ={
    insert.value(_.isbn,book.isbn)
      .value(_.tittle,book.tittle)
      .value(_.author,book.author)
      .value(_.gender,book.gender)
      .value(_.publisher,book.publisher)
      .value(_.country,book.country)
      .value(_.edition,book.edition)
      .consistencyLevel_=(ConsistencyLevel.ALL)
      .future()
  }

  def updateBook(book: Book): Future[ResultSet]={
    saveBook(book)
  }
  def deleteBook(book: Book):Future[ResultSet]={
    delete
      .where(_.isbn eqs book.isbn)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }

  def searchBook(isbn:String): Future[Option[Book]] ={
    select
      .where(_.isbn eqs isbn)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .one()
  }



}


