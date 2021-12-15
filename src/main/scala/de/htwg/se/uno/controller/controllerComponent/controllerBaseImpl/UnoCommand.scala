package de.htwg.se.uno
package controller.controllerComponent

import util.Command
import controller.controllerComponent.controllerInterface
import model._

case class TakeCommand(controller: controllerInterface)
    extends Command(controller) {
  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}

case class PlaceCommand(ind: Int, controller: controllerInterface)
    extends Command(controller) {

  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}
case class WinCommand(controller: controllerInterface)
    extends Command(controller) {

  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}

case class NextCommand(controller: controllerInterface)
    extends Command(controller) {

  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}

object UnoCommand { //Factory
  def apply(controller: controllerInterface, dec: String) =
    dec match
      case "take" =>
        new TakeCommand(controller)
      case "next" =>
        new NextCommand(controller)
      case "win" =>
        new WinCommand(controller)

  def apply(ind: Int, controller: controllerInterface) =
    new PlaceCommand(ind, controller)
}
