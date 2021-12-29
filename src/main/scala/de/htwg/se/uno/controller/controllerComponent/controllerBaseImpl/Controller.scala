package de.htwg.se.uno
package controller.controllerComponent.controllerBaseImpl

import controller.controllerComponent._

import scala.io.StdIn.readLine
import model.gameComponent.gameBaseImpl.Game
import model.gameComponent.gameBaseImpl.toCard._
import util.Observable
import util.Invoker
import model.gameComponent._
import Console.{RED, RESET}

case class Controller(var game: gameInterface) extends controllerInterface:
  val invoker = new Invoker

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
    game = new Game(
      readLine("Name Spieler1:                   "),
      readLine("Name Spieler2:                   "),
      between21State
    ).init()
    notifyObservers
  def newG(p1: String, p2: String) =
    game = new Game(p1, p2, between21State).init()
    notifyObservers
  override def toString: String =
    UnoCommand(this, "print").toString
