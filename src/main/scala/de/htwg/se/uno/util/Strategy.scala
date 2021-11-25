package de.htwg.se.uno
package util

import model.Game._
import model.Game
import model.CardLayout._

object Strategy {}

object DeathToStrategy extends App {

  def add(a: Int, b: Int) = a + b
  def subtract(a: Int, b: Int) = a - b
  def multiply(a: Int, b: Int) = a * b

  def execute(callback: (Int, Int) => Int, x: Int, y: Int) = callback(x, y)

  println("Add:      " + execute(add, 3, 4))
  println("Subtract: " + execute(subtract, 3, 4))
  println("Multiply: " + execute(multiply, 3, 4))

}

/*
case class Strat(var game: Game) extends Strategy {

  override def execute(): Unit =
    if (game.currentstate == game.p1s)
      return game.p1.getName() + eol + game.p1.print() + eol + game.midCard
        .print() + eol + game.p2
        .printFiller() + game.p2
        .getName() + eol
    else if (game.currentstate == game.p2s)
      print("p2")
    else if (game.currentstate == game.p1n || game.currentstate == game.p2n)
      print("zwi")
}
 */
