package com.example.cats_tutorials

import cats.Applicative
import cats.implicits._
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe._
import sttp.client._
import io.circe._, io.circe.parser._
import io.circe.generic.JsonCodec, io.circe.syntax._


trait HelloWorld[F[Z]]{
  def hello(n: HelloWorld.Name): F[String]//HelloWorld.Greeting]
}

object HelloWorld {
  implicit def apply[F[X]](implicit ev: HelloWorld[F]): HelloWorld[F] = ev

  final case class Name(name: String) extends AnyVal
  /**
    * More generally you will want to decouple your edge representations from
    * your internal data structures, however this shows how you can
    * create encoders for your data.
    **/
    final case class Greeting(greeting: String) extends AnyVal
  object Greeting {
    implicit val greetingEncoder: Encoder[Greeting] = new Encoder[Greeting] {
      final def apply(a: Greeting): Json = Json.obj(
        ("message", Json.fromString(a.greeting)),
      ).deepMerge(
        Json.obj(
          ("body", parse(TestClient.apply()).fold(l => Json.fromString(l.toString), r => r))
        )
      ).deepMerge(
        Json.obj(
          ("arr", Json.fromValues(List(
            Json.obj(("test", Json.fromString("something"))),
            Json.obj(("test2", Json.fromString("something")))
          )))
        )
      )
    }
    implicit def greetingEntityEncoder[F[X]: Applicative]: EntityEncoder[F, Greeting] =
      jsonEncoderOf[F, Greeting]
  }

  def impl[F[X]: Applicative]: HelloWorld[F] = new HelloWorld[F]{
    def hello(n: HelloWorld.Name): F[String] = {
      (Greeting("Hello, " + n.name).asJson.spaces4).pure[F]
    }
  }
}

object TestClient {
  def apply(): String = {
    import sttp.client._
    val sort: Option[String] = None
    val query = "http language:scala"
    // the `query` parameter is automatically url-encoded
    // `sort` is removed, as the value is not defined
    val request = basicRequest.get(uri"https://api.github.com/search/repositories?q=$query&sort=$sort")
    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()
    // response.header(...): Option[String]
    // println(response.header("Content-Length")) 
    // response.body: by default read into an Either[String, String] to indicate failure or success 
    response.body.fold(l => l, r => r)
  }
}