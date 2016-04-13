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

version := "1.5.2-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "io.swagger"        %% "swagger-play2"              % "1.5.2",
  "io.swagger"         % "swagger-parser"             % "1.0.16",
  "org.specs2"        %% "specs2-core"                % "3.6.6"            % "test",
  "org.specs2"        %% "specs2-mock"                % "3.6.6"            % "test",
  "org.specs2"        %% "specs2-junit"               % "3.6.6"            % "test",
  "org.mockito"        % "mockito-core"               % "1.9.5"            % "test"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
