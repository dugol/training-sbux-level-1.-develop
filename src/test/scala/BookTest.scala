import java.util.concurrent.Executors

import com.outworkers.phantom.ResultSet
import entities.Book
import org.scalatest.FunSuite
import repository.TestBookRepository
import services.BookService

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class BookTest extends FunSuite {


  test("Consultar libro existente con isbn 1234567") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureOptionBook = BookService.searchBook("1234567").run(TestBookRepository)
    val res2 = Await.result(futureOptionBook, 10 seconds).get
    assert(res2 == Book("test", "test", "test", "test", "test", "test", 0))
  }

  test("Consultar libro con isbn inexistente") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureOptionBook = BookService.searchBook("123").run(TestBookRepository)
    val optionBook = Await.result(futureOptionBook, 10 seconds)
    assert(optionBook == None)
  }


  test("Crear libro con ISBN inexistente") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureResultSet = BookService.createBook(Book("", "", "", "", "", "", 0)).run(TestBookRepository)
    val res2 = Await.result(futureResultSet, 10 seconds)
    assert(res2 == ResultSet(ResultSet(null, null), null))
  }
  test("Crear libro con ISBN existente") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureResultSet = BookService.createBook(Book("1234567", "", "", "", "", "", 0)).run(TestBookRepository)
      .recoverWith { case e: Exception => Future(new Exception("isbn existente")) }
    val resultSet = Await.result(futureResultSet, 10 seconds)
    assert(resultSet !== ResultSet(ResultSet(null, null), null))
  }

  test("Borrar libro con ISBN 1234567") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureResultSet = BookService.deleteBook(Book("1234567", "", "", "", "", "", 0)).run(TestBookRepository)
    val resultSet = Await.result(futureResultSet, 10 seconds)
    assert(resultSet === ResultSet(ResultSet(null, null), null))
  }
  test("Borrar libro con ISBN inexistente") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureResultSet = BookService.deleteBook(Book("123", "", "", "", "", "", 0)).run(TestBookRepository)
      .recoverWith { case e: Exception => Future(new Exception("libro inexistente")) }
    val resultSet = Await.result(futureResultSet, 10 seconds)
    assert(resultSet !== ResultSet(ResultSet(null, null), null))
  }

  test("Actualizar libro con ISBN 1234567") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureResultSet = BookService.updateBook(Book("1234567", "test", "test", "test", "test", "test", 0)).run(TestBookRepository)
    val resultSet = Await.result(futureResultSet, 10 seconds)
    assert(resultSet === ResultSet(ResultSet(null, null), null))
  }

  test("Actualizar libro con ISBN no existente") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val futureResultSet = BookService.updateBook(Book("123", "", "", "", "", "", 0)).run(TestBookRepository)
      .recoverWith { case e: Exception => Future(new Exception("libro inexistente")) }
    val resultSet = Await.result(futureResultSet, 10 seconds)
    assert(resultSet !== ResultSet(ResultSet(null, null), null))
  }


}
