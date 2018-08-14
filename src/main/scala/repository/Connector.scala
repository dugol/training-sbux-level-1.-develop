package repository

import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoints}
import com.outworkers.phantom.database.Database

private object Connector {
  lazy val connector = ContactPoints(Seq("localhost")).keySpace("training")
}

class LocalDatabase(val keyspace: CassandraConnection) extends Database[LocalDatabase](keyspace) {
  object books extends BookDTO with Connector
}

object LocalDatabase extends LocalDatabase(Connector.connector)
