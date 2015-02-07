import com.twitter.scrooge.ScroogeSBT

val utilVersion = "6.23.0"
val finagleVersion = "6.24.0"

def util(which: String) = "com.twitter" %% ("util-"+which) % utilVersion
def finagle(which: String) = "com.twitter" %% ("finagle-"+which) % finagleVersion

val dependencies = Seq(
  "com.twitter" %% "cassovary-core" % "5.0.0",
  "org.apache.thrift" % "libthrift" % "0.5.0",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  util("core"),
  finagle("core"),
  finagle("thrift")
)

// scalacOptions in ThisBuild ++= Seq("-feature", "-unchecked", "-deprecation")

lazy val commonSettings = Seq(
  organization := "com.github.pankajgupta",
  version := "0.1.0",
  scalaVersion := "2.11.5",
  resolvers += "twitter" at "http://maven.twttr.com"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(ScroogeSBT.newSettings: _*).
  settings(
    name := "dummygraphserver",
    libraryDependencies ++= dependencies
)

