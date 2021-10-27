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

def row() = //tested
  "+" + "-" * 2
def rowEnd() = //tested
  "+\n"

def mid() = //tested
  "|" + " " * 2
def midEnd() = //tested
  "|\n"

//default settings
//creates the top or bottom row
def udRow() = //tested
  row() * 1 + rowEnd()
//creates the mid row of a card
def midRow() = //tested
  mid() * 1 + midEnd()
//creates the card row
def finalCard() = //tested
  udRow() + midRow() + udRow()

//scalable settings
//creates the top or bottom row
def udRow(cCount: Int) = //tested
  row() * cCount + rowEnd()
//creates the mid row of a card
def midRow(cCount: Int) = //tested
  mid() * cCount + midEnd()
//creates the card row
def finalCard(cCount: Int) = //tested
  udRow(cCount) + midRow(cCount) + udRow(cCount)
