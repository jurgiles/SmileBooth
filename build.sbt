name := "SmileBooth"

version := "0.1"

scalaVersion := "2.11.7"

classpathTypes += "maven-plugin"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-optimize", "-Xlint")

val platform = {
  val jvmName = System.getProperty("java.vm.name").toLowerCase
  var osName = System.getProperty("os.name").toLowerCase
  var osArch = System.getProperty("os.arch").toLowerCase
  if (jvmName.startsWith("dalvik") && osName.startsWith("linux")) {
    osName = "android"
  } else if (jvmName.startsWith("robovm") && osName.startsWith("darwin")) {
    osName = "ios"
    osArch = "arm"
  } else if (osName.startsWith("mac os x")) {
    osName = "macosx"
  } else {
    val spaceIndex = osName.indexOf(' ')
    if (spaceIndex > 0) {
      osName = osName.substring(0, spaceIndex)
    }
  }
  if (osArch.equals("i386") || osArch.equals("i486") || osArch.equals("i586") || osArch.equals("i686")) {
    osArch = "x86"
  } else if (osArch.equals("amd64") || osArch.equals("x86-64") || osArch.equals("x64")) {
    osArch = "x86_64"
  } else if (osArch.startsWith("arm")) {
    osArch = "arm"
  }
  val platformName = osName + "-" + osArch
  platformName
}

val javacvVersion = "1.0"
val javacppVersion = "1.0"

libraryDependencies ++= Seq(
  "org.bytedeco" % "javacv" % javacvVersion excludeAll(
    ExclusionRule(organization = "org.bytedeco.javacpp-presets"),
    ExclusionRule(organization = "org.bytedeco.javacpp")
    ),
  "org.bytedeco.javacpp-presets" % "opencv"  % ("3.0.0-" + javacppVersion) classifier "",
  "org.bytedeco.javacpp-presets" % "opencv"  % ("3.0.0-" + javacppVersion) classifier platform,
  "org.bytedeco"                 % "javacpp" % javacppVersion,
  "org.scala-lang"               % "scala-swing"     % "2.10.5",
  "org.scalatest"                % "scalatest_2.11"  % "2.2.4" % "test"
)



// Create a default Scala style task to run with tests
lazy val testScalastyle = taskKey[Unit]("testScalastyle")

testScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Test).toTask("").value

(test in Test) <<= (test in Test) dependsOn testScalastyle


lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

(compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle

