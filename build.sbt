import sbt.Keys._
import sbt._

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "org.playframework.anorm" %% "anorm-postgres" % "2.6.5",
  "io.lemonlabs" %% "scala-uri" % "1.5.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)

val circeVersion = "0.12.3"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """scala-timezone-app""",
    organization := "com.thmlogwork",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    scalacOptions ++= Seq("-feature", "-deprecation", "-Xfatal-warnings")
  )
