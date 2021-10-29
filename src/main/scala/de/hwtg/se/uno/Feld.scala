@main def Feld: Unit =
  print(finalCard())
  println("xxxxxxxxxxxxxxxxxxxxxx")
  println
  println
  println
  println
  println("xxxxxxxxxxxxxxxxxxxxxx")
  print(finalCard())
  println
  println
  println
  println("scalable output:")
  print(finalCard(7))
  println("xxxxxxxxxxxxxxxxxxxxxx")
  println
  println
  println
  println
  println("xxxxxxxxxxxxxxxxxxxxxx")
  print(finalCard(7))
  println("xxxxxxxxxxxxxxxxxxxxxx")
  println(Table(3, 7))

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
  finalCard(1) + "\n"

def Table(cP1: Int, cP2: Int): String =
  finalCard(cP1) + "\n" + stack() + finalCard(cP2) + "\n"
