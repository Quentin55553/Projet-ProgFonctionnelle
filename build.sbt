ThisBuild / scalaVersion := "2.13.16"
ThisBuild / version := "1.0-SNAPSHOT"
javaOptions ++= Seq("-source", "11", "-target", "11")
javacOptions ++= Seq("-source", "11", "-target", "11")

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "Akka",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "com.typesafe.akka" %% "akka-stream" % "2.6.20",
      "com.typesafe.akka" %% "akka-http" % "10.2.10",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.6.20",
      "com.typesafe.akka" %% "akka-slf4j" % "2.6.20",
      "com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.20",
      "com.softwaremill.sttp.client3" %% "core" % "3.8.15",
      "com.softwaremill.sttp.client3" %% "akka-http-backend" % "3.8.15",
      "com.typesafe.play" %% "play-json" % "2.9.4",
      "com.softwaremill.sttp.client3" %% "core" % "3.8.3",
      "com.lihaoyi" %% "upickle" % "3.1.0"
    )

  )