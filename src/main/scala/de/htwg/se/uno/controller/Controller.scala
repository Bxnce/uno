package de.htwg.se.uno
package controller

import model.Game
import util.Observable

case class Controller(var game: Game) extends Observable:
  def add() =
    game.add()
    notifyObservers
  override def toString: String =
    return game.toString()
