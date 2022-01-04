package de.htwg.se.uno
package controller.controllerComponent.controllerBaseImpl

//import controller.controllerComponent

import scala.io.StdIn.readLine
import util.State
import model.gameComponent.gameInterface
import model.gameComponent.gameBaseImpl.Game
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
        if (e.controller.game.checkWin(e.controller.game.pList(0))) {
          e.controller.game.getNext(e.controller.game, 0, winState)
        } else {
          if (e.controller.game.ERROR != -1) {
            e.controller.game.getNext(e.controller.game, 0, between12State)
          } else {
            e.controller.game.setError(0)
            e.controller.game
          }
        }
      case e: WinCommand =>
        e.controller.game.setError(-1)
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
        if (e.controller.game.checkWin(e.controller.game.pList(1))) {
          e.controller.game.getNext(e.controller.game, 1, winState)
        } else {
          if (e.controller.game.ERROR != -1) {
            e.controller.game.getNext(e.controller.game, 0, between21State)
          } else {
            e.controller.game.setError(0)
            e.controller.game
          }
        }
      case e: WinCommand =>
        e.controller.game.setError(-1)
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

object winState extends State {
  override def handle(command: Command): gameInterface =
    command match
      case e: TakeCommand =>
        e.controller.game.setError(-1)
      case e: PlaceCommand =>
        e.controller.game.setError(-1)
      case e: WinCommand =>
        e.controller.game.setError(-1)
      case e: NextCommand =>
        e.controller.WinG(
          e.controller.game.pList(0).name,
          e.controller.game.pList(1).name
        )
        e.controller.game
}
