name := """petstore"""

version := "1.5.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "io.swagger"        %% "swagger-play2"              % "1.5.1",
  "io.swagger"         % "swagger-parser"             % "1.0.16",
  "org.specs2"        %% "specs2-core"                % "3.6"              % "test",
  "org.specs2"        %% "specs2-mock"                % "3.6"              % "test",
  "org.specs2"        %% "specs2-junit"               % "3.6"              % "test",
  "org.mockito"        % "mockito-core"               % "1.9.5"            % "test"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
