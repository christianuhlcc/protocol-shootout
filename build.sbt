import scalapb._

val shared = Seq(
  organization := "de.christianuhl",
  version      := "0.1.0",
  scalaVersion := "2.12.4",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.4" % "test")
)

lazy val protocolRoot = (project in file("."))
  .aggregate(protobuf, avro, thrift, kryo)
  .settings(
    shared,
    name := "protocol-shootout"
)

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

lazy val avro = (project in file("avro"))
.settings(
  shared,
  avroSourceDirectory := file("avro/src/main/avro"),
  sourceGenerators in Compile += (avroScalaGenerate in Compile).taskValue,
  libraryDependencies += "com.sksamuel.avro4s" %% "avro4s-core" % "1.8.0"
)

lazy val kryo = (project in file("kryo"))
    .settings(
      shared,
      libraryDependencies += "com.twitter" %% "chill" % "0.9.2"
    )

lazy val thrift = (project in file("thrift"))
.settings(
  shared,
  libraryDependencies ++= Seq(
    "org.apache.thrift" % "libthrift" % "0.9.2",
    "com.twitter" %% "scrooge-core" % "17.11.0" exclude("com.twitter", "libthrift"),
    "com.twitter" %% "finagle-thrift" % "17.11.0" exclude("com.twitter", "libthrift")
  ),
  scroogeThriftSourceFolder := file("thrift/src/main/thrift")
)

def scalaXml = Def.setting {
  scalaBinaryVersion.value match {
    case "2.10" => Nil
    case _      => ("org.scala-lang.modules" %% "scala-xml" % "1.0.6") :: Nil
  }
}