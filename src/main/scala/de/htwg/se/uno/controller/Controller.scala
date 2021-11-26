package de.htwg.se.uno
package controller

import model.Game
import model.toCard._
import util.Observable
import util.Invoker
import model._
import Console.{RED, RESET}

case class Controller(var game: Game) extends Observable:
  private val invoker = new Invoker

  def take() =
    invoker.doStep(UnoCommand(this, "take"))
    notifyObservers

  def place(ind: Int) =
    invoker.doStep(UnoCommand(ind, this))
    notifyObservers

  def next() =
    invoker.doStep(UnoCommand(this, "next"))
    notifyObservers

  override def toString: String =
    game.toString()
