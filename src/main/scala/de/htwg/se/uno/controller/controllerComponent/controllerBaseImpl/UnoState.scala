package de.htwg.se.uno
package controller.controllerComponent

import scala.io.StdIn.readLine
import util.State
import model.gameComponent.gameInterface
import model.gameComponent.Game
import util.Command

object player1State extends State {
  //Errors aus State in Game

  override def handle(command: Command): gameInterface =
    command match
      case e: TakeCommand =>
        e.controller.game = e.controller.game.take("P1")
        e.controller.game.setError(0)
      case e: PlaceCommand =>
        e.controller.game = e.controller.game.place(e.ind, 0)
        e.controller.game
      case e: WinCommand =>
        if (e.controller.game.checkWin(e.controller.game.pList(0))) {
          println(
            e.controller.game.pList(0).name + " hat gewonnen! GZ"
          )
          readLine("ENTER fuer neues Spiel!")
          return new Game(
            readLine("Name Spieler1:                   "),
            readLine("Name Spieler2:                   "),
            between21State
          )
        } else { e.controller.game }
      case e: NextCommand =>
        e.controller.game.getNext(e.controller.game, 0, between12State)
}

object player2State extends State {
  override def handle(command: Command): gameInterface =
    command match
      case e: TakeCommand =>
        e.controller.game = e.controller.game.take("P2")
        e.controller.game.setError(0)
      case e: PlaceCommand =>
        e.controller.game = e.controller.game.place(e.ind, 1)
        e.controller.game
      case e: WinCommand =>
        if (e.controller.game.checkWin(e.controller.game.pList(1))) {
          println(
            e.controller.game.pList(1).name + " hat gewonnen! GZ"
          )
          readLine("ENTER fuer neues Spiel!")
          return new Game(
            readLine("Name Spieler1:                   "),
            readLine("Name Spieler2:                   "),
            between21State
          )
        } else { e.controller.game }
      case e: NextCommand =>
        e.controller.game.getNext(e.controller.game, 1, between21State)
}

object between12State extends State {
  override def handle(command: Command): gameInterface =
    command match
      case e: TakeCommand =>
        e.controller.game.setError(-1)
      case e: PlaceCommand =>
        e.controller.game.setError(-1)
      case e: WinCommand =>
        e.controller.game.setError(-1)
      case e: NextCommand =>
        e.controller.game.getNext(e.controller.game, -1, player2State)
}

object between21State extends State {
  override def handle(command: Command): gameInterface =
    command match
      case e: TakeCommand =>
        e.controller.game.setError(-1)
      case e: PlaceCommand =>
        e.controller.game.setError(-1)
      case e: WinCommand =>
        e.controller.game.setError(-1)
      case e: NextCommand =>
        e.controller.game.getNext(e.controller.game, -1, player1State)
}
