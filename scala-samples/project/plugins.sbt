
// Protobuf

addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.12")

libraryDependencies += "com.trueaccord.scalapb" %% "compilerplugin" % "0.6.7"

// Avro

addSbtPlugin("com.julianpeeters" % "sbt-avrohugger" % "1.1.0")


// Thrift

resolvers += Resolver.bintrayRepo("twittercsl", "sbt-plugins")

addSbtPlugin("com.twitter" % "scrooge-sbt-plugin" % "17.12.0")