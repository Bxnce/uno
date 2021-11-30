package de.htwg.se.uno
package controller

import model.Game
import model.toCard._
import util.Observable
import util.Invoker
import model._
import Console.{RED, RESET}

case class Controller(var g: Game) extends Observable:
  private val invoker = new Invoker
  var game = g

  def take() =
    game = invoker.doStep(UnoCommand(this, "take"))
    notifyObservers

  def place(ind: Int) =
    game = invoker.doStep(UnoCommand(ind, this))
    game = invoker.doStep(UnoCommand(this, "win"))
    notifyObservers

  def next() =
    game = invoker.doStep(UnoCommand(this, "next"))
    notifyObservers

  def redo() =
    game = invoker.redoStep.getOrElse(game)
    notifyObservers

  def undo() =
    game = invoker.undoStep.getOrElse(game)
    notifyObservers

  override def toString: String =
    game.toString()
