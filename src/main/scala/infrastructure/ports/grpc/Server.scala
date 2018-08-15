package infrastructure.ports.grpc

import java.util.concurrent.Executors

import io.grpc.bookMessages._

import scala.concurrent.{ExecutionContext, Future}
import io.grpc.{Server, ServerBuilder}
import java.util.logging.Logger
import io.grpc.protobuf.services.ProtoReflectionService


import entities.Book
import services.BookService

class GrpcServer(executionContext: ExecutionContext) {
  self =>

  private[this] var server: Server = null


  private def start() = {
    server = ServerBuilder
      .forPort(50051)
      .addService(ProtoReflectionService.newInstance())
      .addService(BookGrpc.bindService(new BookGrpcImpl, executionContext))
      .build.start
    println("Server started, listening on 50051")
    println("Boo")
    sys.addShutdownHook {
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

  implicit val ecBook = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))

  private class BookGrpcImpl extends BookGrpc.Book {
    override def createBook(request: BookCreateRequest): Future[BookCreateResponse] = {
      BookService.createBook(Book(request.isbn, request.tittle, request.author, request.gender, request.publisher, request.country, request.edition))
        .map(x => Future.successful(BookCreateResponse("Libro Creado")))
        .flatten
        .recoverWith { case e: Exception => Future.successful(BookCreateResponse("Creacion fallida, ISBN existente")) }
    }

    override def deleteBook(request: BookDeleteRequest): Future[BookDeleteResponse] = {
      BookService.deleteBook(Book(request.isbn, "", "", "", "", "", 0))
        .map(x => Future.successful(BookDeleteResponse(s"Libro con isbn ${request.isbn} borrado exitosamente")))
        .flatten
        .recoverWith { case e: Exception => Future.successful(BookDeleteResponse(s"Libro con isbn ${request.isbn} inexistente")) }

    }

    override def searchBook(request: BookSearchRequest): Future[BookSearchResponse] = {
      BookService.searchBook(request.isbn)
        .map(optionBook => optionBook.fold {
          BookSearchResponse("inexistente", "", "", "", "", "", 0)
        } { book => BookSearchResponse(book.isbn, book.tittle, book.author, book.gender, book.publisher, book.country, book.edition) })
    }

    override def updateBook(request: BookUpdateRequest): Future[BookUpdateResponse] = {
      BookService.updateBook(Book(request.isbn, request.tittle, request.author, request.gender, request.publisher, request.country, request.edition))
        .map(x => Future.successful(BookUpdateResponse("Libro actualizado")))
        .flatten
        .recoverWith { case e: Exception => Future.successful(BookUpdateResponse(s"sLibro con isbn${request.isbn} inexistente")) }
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

