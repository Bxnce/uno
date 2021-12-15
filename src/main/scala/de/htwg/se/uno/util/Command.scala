package de.htwg.se.uno
package util

import controller.controllerComponent.controllerInterface
import model.Game

trait Command(controller: controllerInterface) { //template Pattern eingebaut
  val oldgame = controller.game
  var newgame = controller.game

  def execute: Game =
    oldgame
  def undoStep: Game =
    oldgame
  def redoStep: Game =
    newgame
}
