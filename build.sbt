ThisBuild / scalaVersion := "2.13.16"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "Akka",

    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
      "org.postgresql" % "postgresql" % "42.7.4",
      "org.mindrot" % "jbcrypt" % "0.4",
      "com.typesafe.akka" %% "akka-stream" % "2.8.7",
      "com.typesafe.akka" %% "akka-http" % "10.5.3",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.8.7",
      "com.typesafe.akka" %% "akka-slf4j" % "2.8.7",
      "com.typesafe.akka" %% "akka-serialization-jackson" % "2.8.7",
      "com.typesafe.play" %% "play-slick" % "5.3.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.3.0",
      "com.softwaremill.sttp.client3" %% "core" % "3.10.1",
      "com.softwaremill.sttp.client3" %% "akka-http-backend" % "3.10.1",
      "com.typesafe.play" %% "play-json" % "2.10.5",
      "com.lihaoyi" %% "upickle" % "4.0.1",
      "io.spray" %% "spray-json" % "1.3.6"
    )
  )
