package de.htwg.se.uno
package model

import util.Command
import controller.Controller

case class TakeCommand(controller: Controller) extends Command(controller) {
  override def execute: Game =
    if (controller.game.currentstate == controller.game.p1s) {
      controller.game.take("P1")
      controller.game.ERROR = 0
      controller.game
    } else if (controller.game.currentstate == controller.game.p2s) {
      controller.game.take("P2")
      controller.game.ERROR = 0
      controller.game
    } else {
      controller.game.ERROR = -1
      controller.game
    }
}

case class PlaceCommand(ind: Int, controller: Controller)
    extends Command(controller) {
  override def execute: Game =
    if (controller.game.currentstate == controller.game.p1s) {
      controller.game.place(ind, controller.game.p1)
      controller.game.ERROR = 0
      controller.game
    } else if (controller.game.currentstate == controller.game.p2s) {
      controller.game.place(ind, controller.game.p2)
      controller.game.ERROR = 0
      controller.game
    } else {
      controller.game.ERROR = -1
      controller.game
    }
}

case class NextCommand(controller: Controller) extends Command(controller) {
  override def execute: Game =
    controller.game.changeState()
    return controller.game
}
