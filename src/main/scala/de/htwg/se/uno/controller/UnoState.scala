package de.htwg.se.uno
package controller

import scala.io.StdIn.readLine
import util.State
import model.Game
import util.Command

object player1State extends State {

  //Errors aus State in Game
  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        e.controller.game = e.controller.game.take("P1")
        e.controller.game.setError(0)
      case e: PlaceCommand =>
        e.controller.game = e.controller.game.place(e.ind, 0)
        e.controller.game.setError(0)
      case e: WinCommand =>
        if (e.controller.game.checkWin(e.controller.game.pList(0))) {
          println(
            e.controller.game.pList(0).getName() + " hat gewonnen! GZ"
          )
          readLine("ENTER fuer neues Spiel!")
          return Game.newGame(
            readLine("Name Spieler1:                   "),
            readLine("Name Spieler2:                   ")
          )
        } else { e.controller.game }
      case e: NextCommand =>
        Game(
          e.controller.game.pList,
          between12State,
          0,
          e.controller.game.cardStack,
          e.controller.game.midCard
        )

}

object player2State extends State {
  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        e.controller.game = e.controller.game.take("P2")
        e.controller.game.setError(0)
      case e: PlaceCommand =>
        e.controller.game = e.controller.game.place(e.ind, 1)
        e.controller.game.setError(0)
      case e: WinCommand =>
        if (e.controller.game.checkWin(e.controller.game.pList(1))) {
          println(
            e.controller.game.pList(1).getName() + " hat gewonnen! GZ"
          )
          readLine("ENTER fuer neues Spiel!")
          return Game.newGame(
            readLine("Name Spieler1:                   "),
            readLine("Name Spieler2:                   ")
          )
        } else { e.controller.game }
      case e: NextCommand =>
        Game(
          e.controller.game.pList,
          between21State,
          0,
          e.controller.game.cardStack,
          e.controller.game.midCard
        )

}

object between12State extends State {

  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        e.controller.game.setError(-1)
      case e: PlaceCommand =>
        e.controller.game.setError(0)
      case e: WinCommand =>
        e.controller.game.setError(-1)
      case e: NextCommand =>
        Game(
          e.controller.game.pList,
          player2State,
          0,
          e.controller.game.cardStack,
          e.controller.game.midCard
        )

}

object between21State extends State {

  override def handle(command: Command): Game =
    command match
      case e: TakeCommand =>
        e.controller.game.setError(-1)
      case e: PlaceCommand =>
        e.controller.game.setError(0)
      case e: WinCommand =>
        e.controller.game.setError(-1)
      case e: NextCommand =>
        Game(
          e.controller.game.pList,
          player1State,
          0,
          e.controller.game.cardStack,
          e.controller.game.midCard
        )

}
