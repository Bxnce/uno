package de.htwg.se.uno

object Field {

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

}
