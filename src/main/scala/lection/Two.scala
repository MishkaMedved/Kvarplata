package lection

import Magic.ImplicitForPrint

object UnapplyTuple extends App with ImplicitForPrint {
  val (x, y, z) = (1, 2, 3)
  x.print // 1
  y.print // 2
  z.print // 3
}

