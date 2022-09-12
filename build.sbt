
lazy val root = Project(id = "rtp-test-lib", base = file("."))
  .enablePlugins(GitVersioning)
  .settings(
    name := "rtp-test-lib",
    organization := "uk.gov.homeoffice",
    scalaVersion := "2.12.12",
    crossScalaVersions := Seq("2.12.12"),
    libraryDependencies ++= Seq(
      "org.clapper" %% "grizzled-slf4j" % "1.3.4",
      "ch.qos.logback" % "logback-classic" % "1.4.0",
      "org.specs2" %% "specs2-core" % "4.10.6" withSources(),
      "org.specs2" %% "specs2-mock" % "4.10.6" withSources(),
      "org.specs2" %% "specs2-matcher-extra" % "4.10.6" withSources(),
      "org.specs2" %% "specs2-junit" % "4.10.6" withSources()
    )
  )

git.useGitDescribe := true
git.gitDescribePatterns := Seq("v*.*")
git.gitTagToVersionNumber := { tag :String =>

val branchTag = if (git.gitCurrentBranch.value == "master") "" else "-" + git.gitCurrentBranch.value
val uncommit = if (git.gitUncommittedChanges.value) "-U" else ""

tag match {
  case v if v.matches("v\\d+.\\d+") => Some(s"$v.0${uncommit}".drop(1))
  case v if v.matches("v\\d+.\\d+-.*") => Some(s"${v.replaceFirst("-",".")}${branchTag}${uncommit}".drop(1))
  case _ => None
}}

publishTo := {
  val artifactory = sys.env.get("ARTIFACTORY_SERVER").getOrElse("https://artifactory.digital.homeoffice.gov.uk")
  Some("release"  at artifactory + "artifactory/libs-release-local")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers ++= Seq(
  "ACPArtifactory Lib Snapshot" at "https://artifactory.digital.homeoffice.gov.uk/artifactory/libs-snapshot-local/",
  "ACPArtifactory Lib Release" at "https://artifactory.digital.homeoffice.gov.uk/artifactory/libs-release-local/",
  "ACPArtifactory Ext Release" at "https://artifactory.digital.homeoffice.gov.uk/artifactory/ext-release-local/"
)

publishArtifact in (Test, packageBin) := true
publishArtifact in (Test, packageDoc) := true
publishArtifact in (Test, packageSrc) := true
