import scalapb._

val shared = Seq(
  organization := "de.christianuhl",
  version      := "0.1.0",
  scalaVersion := "2.12.4"
)

lazy val protocolRoot = (project in file("."))
  .aggregate(protobuf)
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
    description := "protobuf examples",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.4" % "test"
    )
  )

def scalaXml = Def.setting {
  scalaBinaryVersion.value match {
    case "2.10" => Nil
    case _      => ("org.scala-lang.modules" %% "scala-xml" % "1.0.6") :: Nil
  }
}