val scala3Version = "3.0.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "uno",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    crossScalaVersions ++= Seq("2.13.5", "3.0.2"),
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0")
      .cross(CrossVersion.for3Use2_13)
  )
  .enablePlugins(JacocoCoverallsPlugin)
//falls es zu Problemem mit Parallelität kommt
//parallelExecution in Test := false
