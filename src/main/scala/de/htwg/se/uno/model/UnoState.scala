package de.htwg.se.uno
package model

import util.State
import model.Game
import util.Command

case class player1State(var game: Game) extends State {

  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        game.take("P1")
        game.ERROR = 0
        game
      case e: PlaceCommand =>
        game.place(e.ind, game.p1)
        game.ERROR = 0
        game

  override def changeState(): Unit =
    game.currentstate = game.p2n
}

case class player2State(game: Game) extends State {
  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        game.take("P2")
        game.ERROR = 0
        game
      case e: PlaceCommand =>
        game.place(e.ind, game.p2)
        game.ERROR = 0
        game

  override def changeState(): Unit =
    game.currentstate = game.p1n
}

case class between12State(game: Game) extends State {

  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        game.ERROR = -1
        game
      case e: PlaceCommand =>
        game.ERROR = -1
        game

  override def changeState(): Unit =
    game.currentstate = game.p2s
}

case class between21State(game: Game) extends State {

  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        game.ERROR = -1
        game
      case e: PlaceCommand =>
        game.ERROR = -1
        game
  override def changeState(): Unit =
    game.currentstate = game.p1s
}
