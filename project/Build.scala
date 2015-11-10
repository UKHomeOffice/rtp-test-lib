import sbt._
import Keys._

object Build extends Build {
  val moduleName = "rtp-test-lib"

  val specs2Version = "3.6.2"

  lazy val root = Project(id = moduleName, base = file("."))
    .settings(
      name := moduleName,
      organization := "uk.gov.homeoffice",
      version := "1.0",
      scalaVersion := "2.11.7",
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
        "Artifactory Snapshot Realm" at "http://artifactory.registered-traveller.homeoffice.gov.uk/artifactory/libs-snapshot-local/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
        "Kamon Repository" at "http://repo.kamon.io"),
      libraryDependencies ++= Seq(
        "org.scalactic" %% "scalactic" % "2.2.4" withSources(),
        "org.clapper" %% "grizzled-slf4j" % "1.0.2",
        "ch.qos.logback" % "logback-classic" % "1.1.3",
        "org.mockito" % "mockito-all" % "1.10.19" withSources(),
        "org.specs2" %% "specs2-core" % specs2Version withSources(),
        "org.specs2" %% "specs2-mock" % specs2Version withSources() excludeAll ExclusionRule(organization = "org.mockito"),
        "org.specs2" %% "specs2-matcher-extra" % specs2Version withSources(),
        "org.specs2" %% "specs2-junit" % specs2Version withSources()))
}
