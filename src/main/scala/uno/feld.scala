package uno
object Uno {
  def main(args: Array[String]): Unit = {
    val row = ("+" + "-" * 2)
    val rowEnd = "+"

    val mid = ("|" + " " * 2)
    val midEnd = "|"

    val unoKarte = row + "\n" + mid + "\n" + row
    val unoKarteEnd = rowEnd + "\n" + midEnd + "\n" + rowEnd

    def p1(cCount: Int) =
      row * cCount + rowEnd + "\n" + mid * cCount + midEnd + "\n" + row * cCount + rowEnd + "\n"

    def p2(cCount: Int) =
      unoKarte * cCount + unoKarteEnd

    print(p1(5))
    println("xxxxxxxxxxxxxxxx\n")
    print(p2(1))

  }
}
