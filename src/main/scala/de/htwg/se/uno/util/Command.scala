package de.htwg.se.uno
package util

import controller.Controller
import model.Game

trait Command(controller: Controller) { //template Pattern eingebaut
  val oldgame = controller.game
  var newgame = controller.game

  def execute: Game =
    oldgame
  def undoStep: Game =
    oldgame
  def redoStep: Game =
    newgame
}

/*
trait CommandTemplate(controller: Controller) extends Command {
  val oldgame = controller.game
  var newgame = controller.game

  override def execute =
    oldgame
  override def undoStep =
    oldgame
  override def redoStep =
    newgame

}*/
