package de.htwg.se.uno
package model

import util.State

case class player1State(var game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p2n
}

case class player2State(game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p1n
}

case class between12State(game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p2s
}

case class between21State(game: Game) extends State {
  override def changeState(): Unit =
    game.currentstate = game.p1s
}
