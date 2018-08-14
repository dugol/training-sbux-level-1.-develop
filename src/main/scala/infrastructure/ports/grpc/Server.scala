package infrastructure.ports.grpc

import java.util.concurrent.Executors

import io.grpc.bookMessages._

import scala.concurrent.{ExecutionContext, Future}
import io.grpc.{Server, ServerBuilder}
import java.util.logging.Logger


import entities.Book
import services.BookService

class GrpcServer(executionContext: ExecutionContext){ self =>
  implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))
  private[this] var server:Server=null

  private def start()={
    server=ServerBuilder
      .forPort(50051)
      .addService(BookGrpc.bindService(new BookGrpcImpl,executionContext))
      //.addService(ProtoReflectionService.newInstance())
      .build.start
    println("Server started, listening on 50051")
    sys.addShutdownHook{
      System.err.println("***shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }


  private def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  private def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }


  private class BookGrpcImpl extends BookGrpc.Book{
    override def createBook(request: BookCreateRequest): Future[BookCreateResponse] = {
      BookService.createBook(Book(request.isbn,request.tittle,request.author,request.gender,request.publisher,request.country,request.edition))
      Future.successful(BookCreateResponse("Libro creado"))
    }

    override def deleteBook(request: BookDeleteRequest): Future[BookDeleteResponse] = {
      BookService.deleteBook(Book(request.isbn,"","","","","",0))
      Future.successful(BookDeleteResponse(s"Libro con isbn ${request.isbn} borrado exitosamente"))
    }

    override def searchBook(request: BookSearchRequest): Future[BookSearchResponse] = {
      val res: Future[Option[Book]] =BookService.searchBook(request.isbn)
      res.map(optionBook=>optionBook.fold(BookSearchResponse("inexistente","","","","","",0))
      (book=>BookSearchResponse(book.isbn,book.tittle,book.author,book.gender,book.publisher,book.country,book.edition)))
    }

    override def updateBook(request: BookUpdateRequest): Future[BookUpdateResponse] = {
      BookService.updateBook(Book(request.isbn,request.tittle,request.author,request.gender,request.publisher,request.country,request.edition))
      Future.successful(BookUpdateResponse("Libro actualizado"))
    }
  }
}
object GrpcServer {
  private val logger = Logger.getLogger(classOf[GrpcServer].getName)

  def main(args: Array[String]): Unit = {
    val server = new GrpcServer(ExecutionContext.global)
    server.start()
    server.blockUntilShutdown()
  }

  private val port = 50051
}

