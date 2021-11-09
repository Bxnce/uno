package de.htwg.se.uno
package controller

import model.Player
import model.Game
import util.Observable

case class Controller(var game: Game) extends Observable:
  def add() =
    game = game.add()
    notifyObservers
