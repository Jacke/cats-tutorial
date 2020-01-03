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
      "org.typelevel" %% "cats-laws" % "2.0.0", //or `cats-testkit` if you are using ScalaTest
      "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.3",
      "com.google.guava"         % "guava"  % "28.0-jre",
      "com.google.code.findbugs" % "jsr305" % "3.0.2"
    ),

    libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.1" % Test,
    
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core" % "2.0.0-RC5",
      "io.chrisdavenport" %% "log4cats-slf4j" % "1.0.1", // Direct Slf4j Support - Recommended
      "com.typesafe.akka" %% "akka-http"   % "10.1.11", 
      "com.typesafe.akka" %% "akka-stream" % "2.5.26", // or whatever the latest version is
      "com.typesafe.akka" %% "akka-http-testkit" % "10.1.11",
      "ch.megard" %% "akka-http-cors" % "0.4.2",
      "de.heikoseeberger" %% "akka-http-circe" % "1.30.0",
      "org.scalatest" %% "scalatest" % "3.1.0",
      "com.googlecode.libphonenumber" % "libphonenumber" % "8.5.0",
      "io.chrisdavenport" %% "cats-par" % "1.0.0-RC2",
      "com.nimbusds" % "nimbus-jose-jwt" % "5.1",
      "com.nimbusds" % "oauth2-oidc-sdk" % "5.36",
    
    
      "org.typelevel" %% "cats-mtl-core" % "0.7.0",
      "com.github.pureconfig" %% "pureconfig" % "0.12.2",
      "com.github.pureconfig" %% "pureconfig-cats-effect" % "0.12.2",
    
      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "io.circe"        %% "circe-parser" % CirceVersion,

      "org.flywaydb" % "flyway-core" % "5.1.4",

      "org.tpolecat" %% "doobie-core" % "0.8.8",
      "org.tpolecat" %% "doobie-postgres" % "0.8.8",
      "org.tpolecat" %% "doobie-hikari" % "0.8.8",
    
      "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
    ),
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  ).enablePlugins(BuildInfoPlugin)
  //buildNumber := sys.props.getOrElse("BUILD_NUMBER", "1")
  //version := s"${projectVersion.value}.${buildNumber.value}"
  //buildBranch := sys.props.getOrElse("BRANCH", "unknown")
  // buildInfoKeys := Seq[BuildInfoKey](
  //   name,
  //   version,
  //   buildBranch,
  //   buildTime,
  //   "title" ->"Example admin service.",
  //   "description" -> "Example admin service."
  // )
  // buildInfoOptions += BuildInfoOption.ToJson
  // buildInfoPackage := "buildInfo"
  // buildTime := ZonedDateTime.now()
  

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)
