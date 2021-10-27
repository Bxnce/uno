package uno
object Uno {
  def main(args: Array[String]): Unit = {

    val row = ("+" + "-" * 2)
    val rowEnd = "+"

    val mid = ("|" + " " * 2)
    val midEnd = "|"

    //creates the top or bottom row
    def udrow(cCount: Int) =
      row * cCount + rowEnd + "\n"
    //creates the mid row of a card
    def midrow(cCount: Int) =
      mid * cCount + midEnd + "\n"

    //creates the card row
    def finalCard(cCount: Int) =
      udrow(cCount) + midrow(cCount) + udrow(cCount)

    print(finalCard(7))
    println("xxxxxxxxxxxxxxxxxxxxxx")
    println
    println
    println
    println
    println("xxxxxxxxxxxxxxxxxxxxxx")
    print(finalCard(7))

    //HI Timo hat nen kleinen

  }
}
