package com.example.cats_tutorials

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    Cats_tutorialsServer.stream[IO].compile.drain.as(ExitCode.Success)
}