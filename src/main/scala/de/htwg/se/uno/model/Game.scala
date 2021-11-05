package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import model.Player
import Field.table

class Game {
  val cs = CardStack()
  val p1 = Player(readLine("Name Spieler1: "))
  val p2 = Player(readLine("Name Spieler2: "))

  val cc = readLine("Anzahl an Karten: ").toInt
  print(table(p1.name, p2.name, cc, cc))
}
