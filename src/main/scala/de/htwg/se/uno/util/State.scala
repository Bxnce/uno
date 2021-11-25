package de.htwg.se.uno
package util

import model.Game._

trait State {
  def changeState(): Unit
  def displayState(): Unit
}

case class player1State(var game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p2n
  override def displayState(): Unit = return game.currentstate
}

case class player2State(game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p1n
  override def displayState(): Unit = return game.currentstate
}

case class between12State(game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p2s
  override def displayState(): Unit = return game.currentstate
}

case class between21State(game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p1s
  override def displayState(): Unit = return game.currentstate
}
