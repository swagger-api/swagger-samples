mainClass in assembly := Some("play.core.server.ProdServerStart")

fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList("org", "apache", "commons", "logging", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

test in assembly := {}

name := """petstore"""

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "io.swagger"        %% "swagger-play2"              % "1.6.1",
  "io.swagger"         % "swagger-parser"             % "1.0.44",
  "org.specs2"        %% "specs2-core"                % "3.8.7"            % "test",
  "org.specs2"        %% "specs2-mock"                % "3.8.7"            % "test",
  "org.specs2"        %% "specs2-junit"               % "3.8.7"            % "test",
  "org.mockito"        % "mockito-core"               % "1.9.5"            % "test")

libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"
libraryDependencies += "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1"
libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
