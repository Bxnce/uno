val scala3Version = "3.0.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "uno",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    //crossScalaVersions ++= Seq("2.13.5", "3.0.2"),
    libraryDependencies += "com.google.inject" % "guice" % "4.2.3",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    //libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5",
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.3")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test"
    ))
assemblyMergeStrategy in assembly := {
 case PathList("META-INF", _*) => MergeStrategy.discard
 case _                        => MergeStrategy.first
}