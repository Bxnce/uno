package de.htwg.se.uno
package controller

import util.Command
import controller.Controller
import model._

case class TakeCommand(controller: Controller) extends Command(controller) {
  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}

case class PlaceCommand(ind: Int, controller: Controller)
    extends Command(controller) {

  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}
case class WinCommand(controller: Controller) extends Command(controller) {

  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
}

case class NextCommand(controller: Controller) extends Command(controller) {

  override def execute =
    newgame = controller.game.currentstate.handle(this)
    newgame
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
