package de.htwg.se.uno.model
package model
import Card._
import Field._

case class Player(n: String) {
  val name = n
  var karten = Vector[Card](R0, G1, B1, B2, B3)
  override def toString: String = name

  def print() =
    val midLine =
      karten.map(_.toString).map("" + _ + "").mkString("|", "|", "|") + eol
    printf(udRow(karten.size) + midLine + udRow(karten.size))

  def add() =
    karten = karten :+ Y9
    karten = karten :+ R7
}
