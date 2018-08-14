name := """scala-sbux-training-1"""

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)


version := "1.0"

scalaVersion := "2.12.6"

val grpcJavaVersion = com.trueaccord.scalapb.compiler.Version.grpcJavaVersion
val scalapbVersion = com.trueaccord.scalapb.compiler.Version.scalapbVersion

val grpcDependencies = Seq(
  "io.grpc"                    % "grpc-netty"                            % grpcJavaVersion,
  "io.grpc"                    % "grpc-protobuf"                         % grpcJavaVersion,
  "io.grpc"                    % "grpc-services"                         % grpcJavaVersion,
  "com.trueaccord.scalapb"     %% "scalapb-runtime-grpc"                 % scalapbVersion,
  "com.trueaccord.scalapb"     %% "scalapb-runtime"                      % scalapbVersion % "protobuf",
  "com.google.api.grpc"        % "proto-google-common-protos"            % "0.1.13"
)

val cassandraDependencies = Seq(
  "com.outworkers"             %% "phantom-dsl"                          % "2.13.5",
  "com.outworkers"             %% "phantom-jdk8"                         % "2.13.5",
  "org.cassandraunit"          % "cassandra-unit"                        % "3.1.3.2" //% "it,test"
)

val kafkaDependencies = Seq(
  "io.monix" %% "monix-kafka-11" % "0.16"
)


val commonDependencies = Seq(
  "org.typelevel" %% "cats-core"     % "1.1.0",
  "org.scalatest" % "scalatest_2.12" % "3.0.0" % "test"
)


libraryDependencies ++= commonDependencies ++ grpcDependencies ++ cassandraDependencies ++ kafkaDependencies

