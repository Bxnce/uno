package de.htwg.se.uno
package model
import Card._
import CardLayout._

case class Player(n: String) {
  val name = n
  var karten = Vector[Card]()
  override def toString: String = name //wofür brauchen wir die ToString ?

  def print(): String = //printet bei einen leeren Vektor trotzdem noch eine Karte aber ohne Wert
    if (karten.size == 0) {
      return udRow(1) + "|  |" + eol + udRow(1)
    } else {
      val midLine =
        karten.map(_.toString).map("" + _ + "").mkString("|", "|", "|") + eol
      return udRow(karten.size) + midLine + udRow(karten.size)
    }

  def getName(): String =
    return name

  //Was passiert wenn man eine falsche Karte einfügen will
  def add(karte: Card) =
    karten = karten :+ karte
}
