import sbt._
import Keys._

object Build extends Build {
  val moduleName = "rtp-test-lib"

  lazy val root = Project(id = moduleName, base = file("."))
    .settings(
      name := moduleName,
      organization := "uk.gov.homeoffice",
      scalaVersion := "2.12.6",
      crossScalaVersions := Seq("2.11.8", "2.12.6"),
      libraryDependencies ++= {
        val `specs2-version` = "3.8.9"

        Seq(
        "org.clapper" %% "grizzled-slf4j" % "1.3.1",
          "ch.qos.logback" % "logback-classic" % "1.2.3",
          "org.specs2" %% "specs2-core" % `specs2-version` withSources(),
          "org.specs2" %% "specs2-mock" % `specs2-version` withSources(),
          "org.specs2" %% "specs2-matcher-extra" % `specs2-version` withSources(),
          "org.specs2" %% "specs2-junit" % `specs2-version` withSources()
        )
      }
    )
}
