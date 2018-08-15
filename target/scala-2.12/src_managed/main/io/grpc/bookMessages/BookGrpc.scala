package io.grpc.bookMessages

object BookGrpc {
  val METHOD_CREATE_BOOK: _root_.io.grpc.MethodDescriptor[io.grpc.bookMessages.BookCreateRequest, io.grpc.bookMessages.BookCreateResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("book.Book", "createBook"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookCreateRequest))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookCreateResponse))
      .build()
  
  val METHOD_DELETE_BOOK: _root_.io.grpc.MethodDescriptor[io.grpc.bookMessages.BookDeleteRequest, io.grpc.bookMessages.BookDeleteResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("book.Book", "deleteBook"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookDeleteRequest))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookDeleteResponse))
      .build()
  
  val METHOD_SEARCH_BOOK: _root_.io.grpc.MethodDescriptor[io.grpc.bookMessages.BookSearchRequest, io.grpc.bookMessages.BookSearchResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("book.Book", "searchBook"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookSearchRequest))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookSearchResponse))
      .build()
  
  val METHOD_UPDATE_BOOK: _root_.io.grpc.MethodDescriptor[io.grpc.bookMessages.BookUpdateRequest, io.grpc.bookMessages.BookUpdateResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("book.Book", "updateBook"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookUpdateRequest))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(io.grpc.bookMessages.BookUpdateResponse))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("book.Book")
      .setSchemaDescriptor(new _root_.com.trueaccord.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(io.grpc.bookMessages.BookMessagesProto.javaDescriptor))
      .addMethod(METHOD_CREATE_BOOK)
      .addMethod(METHOD_DELETE_BOOK)
      .addMethod(METHOD_SEARCH_BOOK)
      .addMethod(METHOD_UPDATE_BOOK)
      .build()
  
  trait Book extends _root_.com.trueaccord.scalapb.grpc.AbstractService {
    override def serviceCompanion = Book
    def createBook(request: io.grpc.bookMessages.BookCreateRequest): scala.concurrent.Future[io.grpc.bookMessages.BookCreateResponse]
    def deleteBook(request: io.grpc.bookMessages.BookDeleteRequest): scala.concurrent.Future[io.grpc.bookMessages.BookDeleteResponse]
    def searchBook(request: io.grpc.bookMessages.BookSearchRequest): scala.concurrent.Future[io.grpc.bookMessages.BookSearchResponse]
    def updateBook(request: io.grpc.bookMessages.BookUpdateRequest): scala.concurrent.Future[io.grpc.bookMessages.BookUpdateResponse]
  }
  
  object Book extends _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[Book] {
    implicit def serviceCompanion: _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[Book] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = io.grpc.bookMessages.BookMessagesProto.javaDescriptor.getServices().get(0)
  }
  
  trait BookBlockingClient {
    def serviceCompanion = Book
    def createBook(request: io.grpc.bookMessages.BookCreateRequest): io.grpc.bookMessages.BookCreateResponse
    def deleteBook(request: io.grpc.bookMessages.BookDeleteRequest): io.grpc.bookMessages.BookDeleteResponse
    def searchBook(request: io.grpc.bookMessages.BookSearchRequest): io.grpc.bookMessages.BookSearchResponse
    def updateBook(request: io.grpc.bookMessages.BookUpdateRequest): io.grpc.bookMessages.BookUpdateResponse
  }
  
  class BookBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[BookBlockingStub](channel, options) with BookBlockingClient {
    override def createBook(request: io.grpc.bookMessages.BookCreateRequest): io.grpc.bookMessages.BookCreateResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_CREATE_BOOK, options), request)
    }
    
    override def deleteBook(request: io.grpc.bookMessages.BookDeleteRequest): io.grpc.bookMessages.BookDeleteResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_DELETE_BOOK, options), request)
    }
    
    override def searchBook(request: io.grpc.bookMessages.BookSearchRequest): io.grpc.bookMessages.BookSearchResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_SEARCH_BOOK, options), request)
    }
    
    override def updateBook(request: io.grpc.bookMessages.BookUpdateRequest): io.grpc.bookMessages.BookUpdateResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_UPDATE_BOOK, options), request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): BookBlockingStub = new BookBlockingStub(channel, options)
  }
  
  class BookStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[BookStub](channel, options) with Book {
    override def createBook(request: io.grpc.bookMessages.BookCreateRequest): scala.concurrent.Future[io.grpc.bookMessages.BookCreateResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_CREATE_BOOK, options), request))
    }
    
    override def deleteBook(request: io.grpc.bookMessages.BookDeleteRequest): scala.concurrent.Future[io.grpc.bookMessages.BookDeleteResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_DELETE_BOOK, options), request))
    }
    
    override def searchBook(request: io.grpc.bookMessages.BookSearchRequest): scala.concurrent.Future[io.grpc.bookMessages.BookSearchResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_SEARCH_BOOK, options), request))
    }
    
    override def updateBook(request: io.grpc.bookMessages.BookUpdateRequest): scala.concurrent.Future[io.grpc.bookMessages.BookUpdateResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_UPDATE_BOOK, options), request))
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): BookStub = new BookStub(channel, options)
  }
  
  def bindService(serviceImpl: Book, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
    _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
    .addMethod(
      METHOD_CREATE_BOOK,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[io.grpc.bookMessages.BookCreateRequest, io.grpc.bookMessages.BookCreateResponse] {
        override def invoke(request: io.grpc.bookMessages.BookCreateRequest, observer: _root_.io.grpc.stub.StreamObserver[io.grpc.bookMessages.BookCreateResponse]): Unit =
          serviceImpl.createBook(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_DELETE_BOOK,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[io.grpc.bookMessages.BookDeleteRequest, io.grpc.bookMessages.BookDeleteResponse] {
        override def invoke(request: io.grpc.bookMessages.BookDeleteRequest, observer: _root_.io.grpc.stub.StreamObserver[io.grpc.bookMessages.BookDeleteResponse]): Unit =
          serviceImpl.deleteBook(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_SEARCH_BOOK,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[io.grpc.bookMessages.BookSearchRequest, io.grpc.bookMessages.BookSearchResponse] {
        override def invoke(request: io.grpc.bookMessages.BookSearchRequest, observer: _root_.io.grpc.stub.StreamObserver[io.grpc.bookMessages.BookSearchResponse]): Unit =
          serviceImpl.searchBook(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_UPDATE_BOOK,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[io.grpc.bookMessages.BookUpdateRequest, io.grpc.bookMessages.BookUpdateResponse] {
        override def invoke(request: io.grpc.bookMessages.BookUpdateRequest, observer: _root_.io.grpc.stub.StreamObserver[io.grpc.bookMessages.BookUpdateResponse]): Unit =
          serviceImpl.updateBook(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): BookBlockingStub = new BookBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): BookStub = new BookStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = io.grpc.bookMessages.BookMessagesProto.javaDescriptor.getServices().get(0)
  
}