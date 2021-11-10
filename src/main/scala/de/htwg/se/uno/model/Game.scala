package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import Player._
import toCard._
import CardLayout._
import Card._
import scala.io.StdIn

case class Game():
  var cs = CardStack()
  val r = scala.util.Random
  val p1 = Player(readLine("Name Spieler1:                   "))
  val p2 = Player(readLine("Name Spieler2:                   "))
  val startC = readLine("Anzahl der Startkarten eingeben: ").toInt
  for (i <- 1 to startC) {
    take("p1")
    take("p2")
  }

  def add(player: String, karte: String): Int =
    val c = getCard(karte)
    if (c.toString == "XX") {
      return -1;
    } else if (cs.c(c) == 0) {
      return -2;
    } else if (
      player.equalsIgnoreCase("P1") || player.equalsIgnoreCase(p1.getName())
    ) {
      p1.add(c)
      cs.c = cs.c + (c -> (cs.c(c) - 1))
      return 0;
    } else if (
      player.equalsIgnoreCase("P2") || player.equalsIgnoreCase(p2.getName())
    ) {
      p2.add(c)
      cs.c = cs.c + (c -> (cs.c(c) - 1))
      return 0;
    } else {
      return -3;
    }

  def add(player: String, c: Card): Int =
    if (c.toString == "XX") {
      take(player)
    } else if (cs.c(c) == 0) {
      take(
        player
      ) //Problem, falls der ganze Stack leer sein sollte, da dauerschleife.
    } else if (
      player.equalsIgnoreCase("P1") || player.equalsIgnoreCase(p1.getName())
    ) {
      p1.add(c)
      cs.c = cs.c + (c -> (cs.c(c) - 1))
      return 0;
    } else if (
      player.equalsIgnoreCase("P2") || player.equalsIgnoreCase(p2.getName())
    ) {
      p2.add(c)
      cs.c = cs.c + (c -> (cs.c(c) - 1))
      return 0;
    } else {
      return -3;
    }
  def take(player: String) =
    val rnd = r.nextInt(39)
    add(player, Card.values(rnd))

  override def toString: String =
    return p1.getName() + eol + p1.print() + eol + p2.print() + p2
      .getName() + eol
