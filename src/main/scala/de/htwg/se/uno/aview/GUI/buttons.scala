package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import scala.swing._
import java.awt.Color
import javax.swing.{ImageIcon, Icon, BorderFactory}

case class buttonsPanel(controller: controllerInterface) {

  val buttons =
    new Button("Next    ") {
      reactions += { case event.ButtonClicked(_) =>
        controller.next()
      }
    }

  def ret: BoxPanel =
    new BoxPanel(Orientation.Horizontal) {
      //border = BorderFactory.createLineBorder(Color.BLACK)
      contents += buttons
    }

}