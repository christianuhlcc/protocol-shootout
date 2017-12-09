import scalapb._

val shared = Seq(
  organization := "de.christianuhl",
  version      := "0.1.0",
  scalaVersion := "2.12.4",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.4" % "test")
)

lazy val protocolRoot = (project in file("."))
  .aggregate(protobuf, avro)
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

def scalaXml = Def.setting {
  scalaBinaryVersion.value match {
    case "2.10" => Nil
    case _      => ("org.scala-lang.modules" %% "scala-xml" % "1.0.6") :: Nil
  }
}