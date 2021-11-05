package de.htwg.se.uno
import scala.io.StdIn.readLine
import Card._
import CardStack._
import scala.collection.immutable.HashMap
import Field._
import de.htwg.se.uno._

@main def Main: Unit =
  val game1 = new TUI()
  game1.TUI()
/*
  var stack = fill()
  val p1 = readLine("Player1: ")
  val p2 = readLine("Player2: ")
  val cards = readLine("Kartenanzahl: ").toInt
  print(table(p1, p2, cards, cards))
 */
