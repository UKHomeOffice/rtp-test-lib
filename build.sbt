
lazy val root = Project(id = "rtp-test-lib", base = file("."))
  //.enablePlugins(GitVersioning)
  .settings(
    name := "rtp-test-lib",
    organization := "uk.gov.homeoffice",
    scalaVersion := "2.12.6",
    crossScalaVersions := Seq("2.11.8", "2.12.6"),
    libraryDependencies ++= Seq(
      "org.clapper" %% "grizzled-slf4j" % "1.3.1",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.specs2" %% "specs2-core" % "3.8.9" withSources(),
      "org.specs2" %% "specs2-mock" % "3.8.9" withSources(),
      "org.specs2" %% "specs2-matcher-extra" % "3.8.9" withSources(),
      "org.specs2" %% "specs2-junit" % "3.8.9" withSources()
    )
  )

git.useGitDescribe := true
git.gitDescribePatterns := Seq("v*.*")
git.gitTagToVersionNumber := { tag :String =>

val branchTag = if (git.gitCurrentBranch.value == "master") "" else "-" + git.gitCurrentBranch.value
val uncommit = if (git.gitUncommittedChanges.value) "-U" else ""

tag match {
  case v if v.matches("v\\d+.\\d+") => Some(s"$v.0" + branchTag + uncommit)
  case v if v.matches("v\\d+.\\d+-.*") => Some(v.replaceFirst("-",".") + branchTag + uncommit)
  case _ => None
}}

publishTo := {
  val artifactory = sys.env.get("ARTIFACTORY_SERVER").getOrElse("http://artifactory.registered-traveller.homeoffice.gov.uk/")

  if (isSnapshot.value)
    Some("snapshot" at artifactory + "artifactory/libs-snapshot-local")
  else
    Some("release"  at artifactory + "artifactory/libs-release-local")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishArtifact in (Test, packageBin) := true
publishArtifact in (Test, packageDoc) := true
publishArtifact in (Test, packageSrc) := true
