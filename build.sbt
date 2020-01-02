val Http4sVersion = "0.21.0-M6"
val CirceVersion = "0.12.3"
val Specs2Version = "4.8.1"
val LogbackVersion = "1.2.3"


lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "cats_tutorials",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies += "io.github.timwspence" %% "cats-stm" % "0.5.0",


    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core" % "2.0.0-RC5",

      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "io.circe"        %% "circe-parser" % CirceVersion,

      "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
    ),
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  ).enablePlugins(BuildInfoPlugin)
  buildNumber := sys.props.getOrElse("BUILD_NUMBER", "1")
  version := s"${projectVersion.value}.${buildNumber.value}"
  buildBranch := sys.props.getOrElse("BRANCH", "unknown")
   buildInfoKeys := Seq[BuildInfoKey](
     name,
     version,
     buildBranch,
     buildTime,
     "title" ->"Example admin service.",
     "description" -> "Example admin service."
   )
   buildInfoOptions += BuildInfoOption.ToJson
   buildInfoPackage := "buildInfo"
   buildTime := ZonedDateTime.now()
  

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
)
