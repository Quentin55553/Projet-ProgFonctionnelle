ThisBuild / scalaVersion := "2.13.16"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "Akka",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.8.7",
      "com.typesafe.akka" %% "akka-http" % "10.5.3",
      "io.spray" %% "spray-json" % "1.3.6",
      "com.softwaremill.sttp.client3" %% "core" % "3.10.1",
      "com.softwaremill.sttp.client3" %% "akka-http-backend" % "3.10.1",
      "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
    )
  )