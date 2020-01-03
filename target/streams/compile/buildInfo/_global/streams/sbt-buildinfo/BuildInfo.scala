package buildinfo

import scala.Predef._

/** This object was generated by sbt-buildinfo. */
case object BuildInfo {
  /** The value is "cats_tutorials". */
  val name: String = "cats_tutorials"
  /** The value is "0.0.1-SNAPSHOT". */
  val version: String = "0.0.1-SNAPSHOT"
  /** The value is "2.13.1". */
  val scalaVersion: String = "2.13.1"
  /** The value is "1.3.6". */
  val sbtVersion: String = "1.3.6"
  override val toString: String = {
    "name: %s, version: %s, scalaVersion: %s, sbtVersion: %s".format(
      name, version, scalaVersion, sbtVersion
    )
  }
}
