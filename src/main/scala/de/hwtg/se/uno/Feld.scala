import scala.io.StdIn.readLine

@main def Feld: Unit =
  println("Player1:")
  val p1 = readLine()
  println("Player2:")
  val p2 = readLine
  println("Kartenanzahl")
  val cards = readLine().toInt

  print(table(p1, p2, cards, cards))

def lb(): String = "\n\n" //tested

def row(): String = //tested
  "+" + "-" * 2
def rowEnd(): String = //tested
  "+\n"

def mid(): String = //tested
  "|" + " " * 2
def midEnd(): String = //tested
  "|\n"

//default settings
//creates the top or bottom row
def udRow(): String = //tested
  row() * 1 + rowEnd()
//creates the mid row of a card
def midRow(): String = //tested
  mid() * 1 + midEnd()
//creates the card row
def finalCard(): String = //tested
  udRow() + midRow() + udRow()

//scalable settings
//creates the top or bottom row
def udRow(cCount: Int): String = //tested
  row() * cCount + rowEnd()
//creates the mid row of a card
def midRow(cCount: Int): String = //tested
  mid() * cCount + midEnd()
//creates the card row
def finalCard(cCount: Int): String = //tested
  udRow(cCount) + midRow(cCount) + udRow(cCount)

def stack(): String =
  finalCard()

def table(n1: String, n2: String, cp1: Int, cp2: Int): String = //tested
  n1 + "\n" + finalCard(cp1) + lb() + stack() + lb() + finalCard(
    cp2
  ) + n2 + "\n"

enum test(colour: String, value: String, number: Int) {
  private final val col = colour
  private final val valu = value
  private final val num = number

  case R1 extends test("R", "1", 2)
  case B1 extends test("B", "1", 2)

}
