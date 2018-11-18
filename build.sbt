import scalapb._

val shared = Seq(
  organization := "de.christianuhl",
  version      := "0.1.0",
  scalaVersion := "2.12.7",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test")
)

lazy val protocolRoot = (project in file("."))
  .aggregate(protobuf, avro, thrift, kryo)
  .settings(
    shared,
    name := "protocol-shootout"
)


// Protobuf

lazy val protobuf = (project in file("protobuf"))
  .settings(
    shared,
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value,
    ),
    PB.protoSources in Compile := Seq(file("protobuf/src/main/protobuf")),
    name := "protocol-shootout-protobuf",
    description := "protobuf examples"
  )

// Avro

lazy val avro = (project in file("avro"))
.settings(
  shared,
  avroSourceDirectory := file("avro/src/main/avro"),
  sourceGenerators in Compile += (avroScalaGenerate in Compile).taskValue,
  libraryDependencies += "com.sksamuel.avro4s" %% "avro4s-core" % "2.0.2"
)

// Kryo

lazy val kryo = (project in file("kryo"))
    .settings(
      shared,
      libraryDependencies += "com.twitter" %% "chill" % "0.9.3"
    )

// Thrift

lazy val thrift = (project in file("thrift"))
.settings(
  shared,
  libraryDependencies ++= Seq(
    "org.apache.thrift" % "libthrift" % "0.11.0",
    "com.twitter" %% "scrooge-core" % "18.11.0" exclude("com.twitter", "libthrift"),
    "com.twitter" %% "finagle-thrift" % "18.11.0" exclude("com.twitter", "libthrift")
  ),
  scroogeThriftSourceFolder := file("thrift/src/main/thrift")
)