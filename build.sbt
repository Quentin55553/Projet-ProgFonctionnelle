ThisBuild / scalaVersion := "2.13.16"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "Akka",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
      "com.softwaremill.sttp.client3" %% "core" % "3.10.1",
      "com.softwaremill.sttp.client3" %% "akka-http-backend" % "3.10.1",
      "com.typesafe.akka" %% "akka-stream" % "2.8.7"
    )
  )