addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.12")

libraryDependencies += "com.trueaccord.scalapb" %% "compilerplugin" % "0.6.6"

addSbtPlugin("com.julianpeeters" % "sbt-avrohugger" % "1.1.0")

resolvers += Resolver.bintrayRepo("twittercsl", "sbt-plugins")

addSbtPlugin("com.twitter" % "scrooge-sbt-plugin" % "17.11.0")