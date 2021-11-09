package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import Player._
import Field.table

case class Game():
  val cs = CardStack()
  val p1 = Player(readLine("Name Spieler1: "))
  val p2 = Player(readLine("Name Spieler2: "))

  val cc = readLine("Anzahl an Karten: ").toInt

  def add() = p1.add()
  override def toString: String =
    p1.print()
