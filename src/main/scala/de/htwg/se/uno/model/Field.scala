package de.htwg.se.uno
package model

object Field {

  val eol = "\n"
  val row = "+" + "-" * 2
  val rowEnd = "+" + eol
  val mid = "|" + " " * 2
  val midEnd = "|" + eol

//default settings
//creates the top or bottom row
  def udRow(): String =
    row * 1 + rowEnd
//creates the mid row of a card
  def midRow(): String =
    mid * 1 + midEnd
//creates the card row
  def finalCard(): String =
    udRow() + midRow() + udRow()
//creates the cardstack in the middle of the table
  def stack(): String =
    finalCard()

//scalable settings
//creates the top or bottom row
  def udRow(cCount: Int): String =
    row * cCount + rowEnd
//creates the mid row of a card
  def midRow(cCount: Int): String =
    mid * cCount + midEnd
//creates the card row
  def finalCard(cCount: Int): String =
    udRow(cCount) + midRow(cCount) + udRow(cCount)
//creates a table with 2 players hands and a Stack in the middle
  def table(n1: String, n2: String, cp1: Int, cp2: Int): String =
    n1 + eol + finalCard(cp1) + eol * 2 + stack() + eol * 2 + finalCard(
      cp2
    ) + n2 + eol
}
