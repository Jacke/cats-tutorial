package com.example.cats_tutorials

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import cats._
import cats.effect._

trait Foo[A] {
  //type Aux[M[_], F0[_]] = Foo[M] { type B = F0[_] }
  type B
  def value: B
}

case class T() extends Foo[String] {
  type B = Int
  def value = 1
}


trait Foo2[A] {
  type B
  def value: B
}



import cats.implicits._

import scala.util.Try

object KindProjectorExample extends App {

//  {type λ[α] = Either[String, α]})#W
// type z = Either[*[_], Int]

private def errorResource[F[_]: MonadError[?[_], Throwable]](num: Int)(
    implicit E: MonadError[F, Throwable],
    F: Effect[F]): Resource[F, Int] = ???  


//  type E = ({type λ[α] = Either[String, α]})#λ

  def foldIt[F[_]: Foldable](input: F[String]) =
    input.foldM[({ type λ[α] = Either[Int, α] })#λ, Throwable](new Throwable) {
      case (_, s) => Try(s.toInt).toEither.swap
    }

  println(foldIt(List("d", "u", "p", "7", "g")))
  println(foldIt(Vector("d", "u", "p", "a")))
  println(foldIt(List.empty[String]))

}

object Main extends IOApp {
  implicit def fi = new Foo[Int] {
    type B = String
    val value = "Foo"
  }
  implicit def fs = new Foo[String] {
    type B = Boolean
    val value = false
  }
  type F = Functor[Map[Int, ?]] // now works!

  def run(args: List[String]) =
    IO(println("hello friend")).as(ExitCode.Success)
    //Cats_tutorialsServer.stream[IO].compile.drain.as(ExitCode.Success)
}