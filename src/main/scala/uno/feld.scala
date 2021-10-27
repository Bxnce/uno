package uno
object Uno {
  def main(args: Array[String]): Unit = {
    val row = ("+" + "-" * 2)
    val rowEnd = "+"

    val mid = ("|" + " " * 2)
    val midEnd = "|"

    val unoKarte = row + "\n" + mid + "\n" + row
    val unoKarteEnd = rowEnd + "\n" + midEnd + "\n" + rowEnd

    def cardrow(cCount: Int) =
      row * cCount + rowEnd + "\n" + mid * cCount + midEnd + "\n" + row * cCount + rowEnd + "\n"

    def cardrow2(cCount: Int) =
      cardrow(cCount)

    print(cardrow(7))
    println("xxxxxxxxxxxxxxxxxxxxxx")
    println
    println
    println
    println
    println("xxxxxxxxxxxxxxxxxxxxxx")
    print(cardrow(7))

  }
}
