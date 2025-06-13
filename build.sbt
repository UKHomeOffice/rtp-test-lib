
lazy val root = Project(id = "rtp-test-lib", base = file("."))
  .enablePlugins(GitVersioning)
  .settings(
    name := "rtp-test-lib",
    organization := "uk.gov.homeoffice",
    scalaVersion := "3.3.5",
    crossScalaVersions := Seq("2.13.16", "3.3.5"),
    libraryDependencies ++= Seq(
      ("org.clapper" %% "grizzled-slf4j" % "1.3.4").cross(CrossVersion.for3Use2_13),
      "ch.qos.logback" % "logback-classic" % "1.5.18",
      "org.specs2" %% "specs2-core" % "4.21.0" withSources(),
      "org.specs2" %% "specs2-matcher-extra" % "4.21.0" withSources(),
      "org.specs2" %% "specs2-junit" % "4.21.0" withSources(),
      "org.mockito" % "mockito-core" % "5.18.0"
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

Test / packageBin / publishArtifact := true
Test / packageDoc / publishArtifact := true
Test / packageSrc / publishArtifact := true
