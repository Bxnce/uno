package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import scala.swing._

case class menubar(controller: controllerInterface) {
  val menubar = new MenuBar {
    contents ++= Seq(
      new Menu("File") {
        contents ++= Seq(
          MenuItem(Action("Load")(controller.load)),
          MenuItem(Action("Save")(controller.save))
        )
      }
    )
  }
}
