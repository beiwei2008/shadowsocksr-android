name := "shadowsocksr"

applicationId  := "net.htcp.shadowsocksr"

scalaVersion := "2.11.8"
dexMaxHeap := "4g"

enablePlugins(AndroidApp)
useSupportVectors

name := "shadowsocks"
version := "3.2.6"
versionCode := Some(166)

platformTarget := "android-24"

compileOrder := CompileOrder.JavaThenScala
javacOptions ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil
scalacOptions ++= "-target:jvm-1.7" :: "-Xexperimental" :: Nil
ndkJavah := Seq()
ndkBuild := Seq()

proguardVersion := "5.3"
proguardCache := Seq()
proguardOptions ++=
  "-keep class com.github.shadowsocks.System { *; }" ::
    "-dontwarn com.google.android.gms.internal.**" ::
    "-dontwarn com.j256.ormlite.**" ::
    "-dontwarn org.xbill.**" ::
    Nil

shrinkResources := true
typedResources := false
resConfigs := Seq("ru", "zh-rCN", "zh-rTW")

resolvers += Resolver.jcenterRepo
libraryDependencies ++=
  "com.android.support" % "design" % "24.2.1" ::
  "com.android.support" % "gridlayout-v7" % "24.2.1" ::
  "com.android.support" % "preference-v14" % "24.2.1" ::
  "com.evernote" % "android-job" % "1.1.0" ::
  "com.github.clans" % "fab" % "1.6.4" ::
  "com.github.jorgecastilloprz" % "fabprogresscircle" % "1.01" ::
  "com.github.kevinsawicki" % "http-request" % "6.0" ::
  "com.google.android.gms" % "play-services-analytics" % "9.6.1" ::
  "com.google.android.gms" % "play-services-gcm" % "9.6.1" ::
  "com.j256.ormlite" % "ormlite-android" % "5.0" ::
  "com.twofortyfouram" % "android-plugin-api-for-locale" % "1.0.2" ::
  "dnsjava" % "dnsjava" % "2.1.7" ::
  "eu.chainfire" % "libsuperuser" % "1.0.0.201608240809" ::
  "me.dm7.barcodescanner" % "zxing" % "1.9" ::
  "net.glxn.qrgen" % "android" % "2.0" ::
  Nil

lazy val nativeBuild = TaskKey[Unit]("native-build", "Build native executables")
nativeBuild := {
  val logger = streams.value.log
  Process("./build.sh") ! logger match {
    case 0 => // Success!
    case n => sys.error(s"Native build script exit code: $n")
  }
}
