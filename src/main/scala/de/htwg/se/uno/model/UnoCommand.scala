package de.htwg.se.uno
package model

import util.Command
import controller.Controller
import model._

case class TakeCommand(controller: Controller) extends Command(controller) {
  override def execute: Game =
    controller.game.currentstate.handle(this)
}

case class PlaceCommand(ind: Int, controller: Controller)
    extends Command(controller) {
  override def execute: Game =
    controller.game.currentstate.handle(this)
}

case class NextCommand(controller: Controller) extends Command(controller) {
  override def execute: Game =
    controller.game.changeState()
    return controller.game
}

object UnoCommand { //Factory
  def apply(controller: Controller, dec: String) =
    dec match
      case "take" =>
        new TakeCommand(controller)
      case "next" =>
        new NextCommand(controller)

  def apply(ind: Int, controller: Controller) =
    new PlaceCommand(ind, controller)
}
