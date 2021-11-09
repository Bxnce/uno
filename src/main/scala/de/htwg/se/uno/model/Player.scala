package de.htwg.se.uno
package model
import Card._
import Field._

case class Player(n: String) {
  val name = n
  var karten = Vector[Card]()
  override def toString: String = name

  def print(): String =
    if (karten.size == 0) {
      return udRow(1) + "|  |" + eol + udRow(1)
    } else {
      val midLine =
        karten.map(_.toString).map("" + _ + "").mkString("|", "|", "|") + eol
      return udRow(karten.size) + midLine + udRow(karten.size)
    }

  def getName(): String =
    return name

  def add(karte: Card) =
    karten = karten :+ karte
}
