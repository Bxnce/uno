package de.hwtg.se.uno

@main def Feld: Unit =
  print(finalCard(7))
  println("xxxxxxxxxxxxxxxxxxxxxx")
  println
  println
  println
  println
  println("xxxxxxxxxxxxxxxxxxxxxx")
  print(finalCard(7))

def row() =
  "+" + "-" * 2
def rowEnd() =
  "+"

//val row = ("+" + "-" * 2)
//val rowEnd = "+"

def mid() =
  "|" + " " * 2
def midEnd() =
  "|"

//val mid = ("|" + " " * 2)
//val midEnd = "|"

//creates the top or bottom row
def udrow(cCount: Int) =
  row() * cCount + rowEnd() + "\n"
//creates the mid row of a card
def midrow(cCount: Int) =
  mid() * cCount + midEnd() + "\n"

//creates the card row
def finalCard(cCount: Int) =
  udrow(cCount) + midrow(cCount) + udrow(cCount)
