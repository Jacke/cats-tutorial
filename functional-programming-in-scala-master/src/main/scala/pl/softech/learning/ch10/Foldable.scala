package pl.softech.learning.ch10

trait Foldable[F[_]] {

  def foldRight[A, B](xs: F[A])(z: B)(f: (A, B) => B): B = {
    val fbb: B => B = foldMap(xs)(f.curried)(Monoid.endoMonoid[B])
    fbb(z)
  }

  def foldLeft[A, B](xs: F[A])(z: B)(f: (B, A) => B): B =
    foldMap(xs)(a => f(_, a))(Monoid.endoMonoid[B].dual)(z)

  def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B =
    foldLeft(as)(mb.zero) { (acc, a) =>
      mb.op(acc, f(a))
    }

  def concatenate[A](as: F[A])(m: Monoid[A]): A =
    foldLeft(as)(m.zero)(m.op)

}

object Foldable {

  def apply[F[_] : Foldable]: Foldable[F] = implicitly[Foldable[F]]

}

object FoldableInstances extends Ex12.FoldableInstances with Ex13.FoldableInstances
  with Ex14.FoldableInstances
