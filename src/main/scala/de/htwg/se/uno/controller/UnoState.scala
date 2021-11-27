package de.htwg.se.uno
package controller

import scala.io.StdIn.readLine
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
        game.place(e.ind, game.pList(0))
        game.ERROR = 0
        game
      case e: WinCommand =>
        if (game.checkWin(game.pList(0))) then
          println(game.pList(0).getName() + " hat gewonnen! GZ")
          readLine("ENTER fuer neues Spiel!")
          return new Game(
            readLine("Name Spieler1:                   "),
            readLine("Name Spieler2:                   "),
            readLine("Anzahl der Startkarten eingeben: ").toInt
          )
        else return game

  override def changeState(): Unit =
    game.pList(0).placed = false
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
        game.place(e.ind, game.pList(1))
        game.ERROR = 0
        game
      case e: WinCommand =>
        if (game.checkWin(game.pList(1))) then
          println(game.pList(1).getName() + " hat gewonnen! GZ")
          readLine("ENTER fuer neues Spiel!")
          return new Game(
            readLine("Name Spieler1:                   "),
            readLine("Name Spieler2:                   "),
            readLine("Anzahl der Startkarten eingeben: ").toInt
          )
        else return game

  override def changeState(): Unit =
    game.pList(1).placed = false
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
      case e: WinCommand =>
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
      case e: WinCommand =>
        game.ERROR = -1
        game
  override def changeState(): Unit =
    game.currentstate = game.p1s

}