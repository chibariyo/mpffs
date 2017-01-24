name := """sampleApp"""

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalacOptions ++= Seq("-feature", "-language:reflectiveCalls")