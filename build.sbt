// https://typelevel.org/sbt-typelevel/faq.html#what-is-a-base-version-anyway
ThisBuild / tlBaseVersion := "0.0" // your current series x.y

ThisBuild / organization := "pink.fff"
ThisBuild / organizationName := "fff"
ThisBuild / startYear := Some(2026)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  // your GitHub handle and name
  tlGitHubDev("valencik", "Andrew Valencik")
)

// publish website from this branch
ThisBuild / tlSitePublishBranch := Some("main")

ThisBuild / scalaVersion := "3.3.7" // the default Scala

lazy val root = tlCrossRootProject.aggregate(core, examples)

lazy val core = crossProject(JVMPlatform)
  .in(file("core"))
  .settings(
    name := "fs2-midi",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.13.0",
      "org.typelevel" %%% "cats-effect" % "3.7.0",
      "co.fs2" %%% "fs2-core" % "3.13.0",
      "org.scalameta" %%% "munit" % "1.3.0" % Test,
      "org.typelevel" %%% "munit-cats-effect" % "2.2.0" % Test
    )
  )

lazy val examples = crossProject(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("examples"))
  .settings(
    name := "fs2-midi-examples"
  )
  .dependsOn(core)

lazy val docs = project.in(file("site")).enablePlugins(TypelevelSitePlugin).dependsOn(core.jvm)
