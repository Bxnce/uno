package de.htwg.se.uno
package model.gameComponent
import Card._
import CardLayout._

case class Player(name: String, karten: Vector[Card], placed: Boolean) {

  def print(): String = //printet bei einem leeren Vektor trotzdem noch eine Karte aber ohne Wert
    if (karten.size == 0) {
      return udRow(1) + "|  |" + eol + udRow(1)
    } else {
      val midLine =
        karten.map(_.toString).map("" + _ + "").mkString("|", "|", "|") + eol

      return udRow(karten.size) + midLine + udRow(karten.size)
    }

  def printFiller(): String =
    if (karten.size < 10) {
      return udRow(1) + "| " + karten.size + "|" + eol + udRow(1)

    } else {
      return udRow(1) + "|" + karten.size + "|" + eol + udRow(1)
    }

  //entfernt den Eintrag in dem Vector an der Index Stelle
  def removeInd(ind: Int): Player =
    val (tmp1, tmp2) = karten.splitAt(ind)
    copy(name, tmp1.toVector ++ tmp2.toVector.drop(1), true)

  def setFalse(): Player =
    copy(name, karten, false)

  def add(karte: Card): Player =
    copy(name, karten :+ karte)
}