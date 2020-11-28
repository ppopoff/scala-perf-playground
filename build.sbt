import Dependencies._
import pl.project13.scala.sbt.JmhPlugin

ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(JmhPlugin)
  .settings(
    name := "scala-perf-playground",
    libraryDependencies ++= Seq(
      scalaCheck % Test,
      scalaTest % Test,
    )
  )
