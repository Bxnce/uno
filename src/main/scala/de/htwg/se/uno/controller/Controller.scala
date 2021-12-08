package de.htwg.se.uno
package controller

import scala.io.StdIn.readLine
import model.Game
import model.toCard._
import util.Observable
import util.Invoker
import model._
import Console.{RED, RESET}

case class Controller(var game: Game) extends Observable:
  private val invoker = new Invoker

  def take() =
    game = invoker.doStep(UnoCommand(this, "take"))
    notifyObservers

  def place(ind: Int) =
    game = invoker.doStep(UnoCommand(ind, this))
    game = invoker.doStep(UnoCommand(this, "win"))
    if (game.ERROR != -1) {
      game = invoker.doStep(UnoCommand(this, "next"))
    }
    notifyObservers

  def next() =
    game = invoker.doStep(UnoCommand(this, "next"))
    notifyObservers

  def undo() =
    game = invoker.undoStep.getOrElse(game)
    notifyObservers

  def redo() =
    game = invoker.redoStep.getOrElse(game)
    notifyObservers

  def newG() =
    game = Game.newGame(
      readLine("Name Spieler1:                   "),
      readLine("Name Spieler2:                   ")
    )
    notifyObservers

  override def toString: String =
    game.toString()
