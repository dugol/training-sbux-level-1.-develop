import java.util.concurrent.Executors

import com.outworkers.phantom.ResultSet
import com.outworkers.phantom.connectors.KeySpace
import entities.Book
import org.scalatest.FunSuite
import repository.BookRepository
import services.BookService

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class BookTest extends FunSuite{

  test("Consultar BD") {
    implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
    BookService.searchBook("123456").map(x=>x.map(x=>println(s"${x.tittle}")))
  }

}
