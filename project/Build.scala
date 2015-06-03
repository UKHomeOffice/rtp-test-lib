import sbt._
import Keys._

object Build extends Build {
  val moduleName = "rtp-test-lib"

  lazy val root = Project(id = moduleName, base = file("."))
    .settings(
      name := moduleName,
      organization := "uk.gov.homeoffice",
      version := "1.0-SNAPSHOT",
      scalaVersion := "2.11.6",
      scalacOptions ++= Seq(
        "-feature",
        "-language:implicitConversions",
        "-language:higherKinds",
        "-language:existentials",
        "-language:reflectiveCalls",
        "-language:postfixOps",
        "-Yrangepos",
        "-Yrepl-sync"),
      ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
      resolvers ++= Seq(
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
        "Kamon Repository" at "http://repo.kamon.io",
        "Artifactory Snapshot Realm" at "http://artifactory.registered-traveller.homeoffice.gov.uk/artifactory/libs-snapshot-local/"),
      libraryDependencies ++= Seq(
        "org.scalactic" %% "scalactic" % "2.2.4" withSources()),
      libraryDependencies ++= Seq(
        "org.specs2" %% "specs2-core" % "3.6" % Test withSources(),
        "org.specs2" %% "specs2-mock" % "3.6" % Test withSources(),
        "org.specs2" %% "specs2-matcher-extra" % "3.6" % Test withSources()))
}