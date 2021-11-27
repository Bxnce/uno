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
    invoker.doStep(UnoCommand(this, "take"))
    notifyObservers

  def place(ind: Int) =
    invoker.doStep(UnoCommand(ind, this))
    invoker.doStep(UnoCommand(this, "win"))
    notifyObservers

  def next() =
    invoker.doStep(UnoCommand(this, "next"))
    notifyObservers

  def redo() =
    invoker.redoStep
    notifyObservers

  def undo() =
    invoker.undoStep
    notifyObservers

  override def toString: String =
    game.toString()
