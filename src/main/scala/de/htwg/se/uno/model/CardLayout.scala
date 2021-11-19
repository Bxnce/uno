package de.htwg.se.uno
package model

object CardLayout {
  val eol = "\n"
  val row = "+" + "-" * 2
  val rowEnd = "+" + eol
//scalable settings
//creates the top or bottom row
  def udRow(cCount: Int): String =
    row * cCount + rowEnd
}
