package de.htwg.se.uno
import scala.io.StdIn.readLine
import Card._
import CardStack._
import scala.collection.immutable.HashMap
import Field._

@main def Main: Unit =
  var stack = fill()

  println("Player1:")
  val p1 = readLine()
  println("Player2:")
  val p2 = readLine
  println("Kartenanzahl")
  val cards = readLine().toInt

  print(table(p1, p2, cards, cards))
