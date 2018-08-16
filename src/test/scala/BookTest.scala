import java.util.concurrent.Executors

import com.outworkers.phantom.ResultSet
import com.outworkers.phantom.connectors.KeySpace
import entities.Book
import org.apache.cassandra.db.Columns
import org.scalatest.FunSuite
import repository.{BookRepository, TestBookRepository}
import services.BookService

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class BookTest extends FunSuite {


  test("Consultar BD") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val res = BookService.searchBook("1234567").run(TestBookRepository)
    val res2 = Await.result(res, 10 seconds).get
    assert(res2 == Book("test", "test", "test", "test", "test", "test", 0))
  }
}
  /*
  test("Crear book"){
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val res=BookService.createBook(Book("","","","","","",0)).run(TestBookRepository)
    val res2=Await.result(res,10 seconds)
    assert(res2==Future(ResultSet(ResultSet(null,null),null)))
  }

  /*
  test("Service CreateBook"){
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    val resultSetC=BookService.createBook(Book("XXXXXXXX","XXXXXXXX","XXXXXXX","XXXXXX","XXXXXXXX","XXXXXXX",0))
    //.recover{case e:Exception => 1}
    val res=Await.result(resultSetC,10 seconds)
    assert(res==ResultSet(ResultSet[exhausted: true, Columns[]],V4))
    val resultsetD=BookService.deleteBook(Book("XXXXXXXX","","","","","",0))
    assert(res==1)

  }
*/
}
