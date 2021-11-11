package de.htwg.se.uno
package model
import Card._
import CardLayout._

case class Player(n: String) {
  val name = n
  var karten = Vector[Card]()

  def print(): String = //printet bei einen leeren Vektor trotzdem noch eine Karte aber ohne Wert
    if (karten.size == 0) {
      return udRow(1) + "|  |" + eol + udRow(1)
    } else {
      val midLine =
        karten.map(_.toString).map("" + _ + "").mkString("|", "|", "|") + eol
      return udRow(karten.size) + midLine + udRow(karten.size)
    }
  //entfernt den Eintrag in dem Vector an der Index Stelle
  def removeInd(ind: Int) =
    if (ind == 0) {
      karten.drop(1)
    } else if (ind == karten.size - 1) {
      karten.dropRight(1)
    }
    val (tmp1, tmp2) = karten.splitAt(ind)
    karten = tmp1.toVector ++ tmp2.toVector.drop(1)

  def getName(): String =
    return name

  //Was passiert wenn man eine falsche Karte einfügen will
  def add(karte: Card) =
    karten = karten :+ karte
}
