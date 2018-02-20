organization := "io.github.isuzuki"
name := "scio-sample"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:_",
  "-target:jvm-1.8",
  "-Ypartial-unification"
)

val beamVersion = "2.2.0"
val scioVersion = "0.4.7"

libraryDependencies ++= Seq(
  "com.spotify"     %% "scio-core"                               % scioVersion,
  "com.spotify"     %% "scio-extra"                              % scioVersion,
  "org.apache.beam" %  "beam-runners-direct-java"                % beamVersion,
  "org.apache.beam" %  "beam-runners-google-cloud-dataflow-java" % beamVersion,
  "com.spotify"     %% "scio-test"                               % scioVersion  % "test"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
