name := "play-classloader-issue"

version := "1.0" 

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb, LauncherJarPlugin)

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  javaWs,

  "org.jsondoc" % "jsondoc-core"      % "1.2.10",
  "org.jsondoc" % "jsondoc-ui-webjar" % "1.2.10" exclude("org.webjars", "bootstrap") exclude("org.webjars", "jquery")
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
