package pl.softech.learning

object Assertion {

  implicit class IdOps[A](val a: A) extends AnyVal {

    def ===(b: A): Unit = {
      if (a != b) {
        println(s"Required : $b but is $a")
      }
      assert(a == b)
    }

  }

}
