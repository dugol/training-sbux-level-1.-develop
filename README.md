Requisitos:
Base de datos Cassandra Local corriendo por el puerto 9042, con los siguientes atributos.

CREATE KEYSPACE training WITH replication = {'class': 'SimpleStrategy','replication_factor':1};
CREATE table book(isbn text primary key, tittle text, author text, gender text, publisher text, country text, edition int);

Ejecutar el comando: docker-compose up -d, en la carpeta que se encuentra el archivo docker-compose.yml, el cual se encuentra en este repositorio.

Ejecutar el comando: docker exec -it IDCONTAINERKAFKA bash, donde el IDCONTAINERKAFKA se puede conocer ejecutando el comando:
docker ps

Dentro de la terminal de Kafka, la cual se habilitará luego de ejecutar el comando anterior, ejecutar los siguientes comandos.

Para crear un topic.
/usr/bin/kafka-topics --create --zookeeper localhost:32181 --replication-factor 1 --partitions 1 --topic Book

Para ejecutar un consumer.
/usr/bin/kafka-console-consumer --bootstrap-server localhost:29092 --topic Book

Los puertos, estan especificados en el archivo docker-compose.yml, por lo tanto estos dependerán de los cambios que se hagan sobre el archivo.

