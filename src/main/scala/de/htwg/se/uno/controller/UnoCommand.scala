package de.htwg.se.uno
package controller

import util.Command
import controller.Controller
import model._

case class TakeCommand(controller: Controller) extends Command(controller) {
  val oldgame = controller.game
  var newgame = controller.game

  override def execute =
    controller.game.currentstate.handle(this)
    newgame = controller.game
  override def undoStep =
    controller.game = oldgame
    println("Hello Kenobi")
  override def redoStep =
    controller.game = newgame
}

case class PlaceCommand(ind: Int, controller: Controller)
    extends Command(controller) {

  val oldgame = controller.game
  var newgame = controller.game
  override def execute =
    controller.game.currentstate.handle(this)
    newgame = controller.game
  override def undoStep =
    controller.game = oldgame
  override def redoStep =
    controller.game = newgame
}

case class NextCommand(controller: Controller) extends Command(controller) {
  val oldgame = controller.game
  var newgame = controller.game
  override def execute =
    controller.game.changeState()
    newgame = controller.game
  override def undoStep =
    controller.game = oldgame
  override def redoStep =
    controller.game = newgame
}

case class WinCommand(controller: Controller) extends Command(controller) {
  val oldgame = controller.game
  var newgame = controller.game
  override def execute =
    controller.game.currentstate.handle(this)
    newgame = controller.game
  override def undoStep =
    controller.game = oldgame
  override def redoStep =
    controller.game = newgame

}

object UnoCommand { //Factory
  def apply(controller: Controller, dec: String) =
    dec match
      case "take" =>
        new TakeCommand(controller)
      case "next" =>
        new NextCommand(controller)
      case "win" =>
        new WinCommand(controller)

  def apply(ind: Int, controller: Controller) =
    new PlaceCommand(ind, controller)
}
