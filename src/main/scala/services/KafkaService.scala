package services


import monix.execution.{CancelableFuture, Scheduler}
import monix.kafka._
import org.apache.kafka.clients.producer.RecordMetadata

object KafkaService{
  private implicit val io = Scheduler.io("scheduler")
  private val producerCfg=KafkaProducerConfig.default.copy(bootstrapServers = List("127.0.0.1:29092"))
  private val producer = KafkaProducer[String,String](producerCfg, io)

  def send(message:String): CancelableFuture[Option[RecordMetadata]] ={
    producer.send("Book",message).runAsync
  }

  def close(): CancelableFuture[Unit] ={
    producer.close().runAsync
  }

  /*// For sending one message
  val recordMetadataF = producer.send("my-topic", "my-message").runAsync*/

  // For closing the producer connection
  //val closeF = producer.close().runAsync
}
