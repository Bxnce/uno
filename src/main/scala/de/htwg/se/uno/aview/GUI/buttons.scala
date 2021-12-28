package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import scala.swing._
import java.awt.Color

case class buttonsPanel(controller: controllerInterface) {

  val inTa = new TextArea {
    maximumSize = new Dimension(100, 25)
    minimumSize = new Dimension(100, 25)
    preferredSize = new Dimension(100, 25)
    background = Color.GRAY.brighter
    foreground = Color.BLACK
  }

  val buttons = List(
    new Button("Next    ") {
      reactions += { case event.ButtonClicked(_) =>
        controller.next()
      }
    },
    new Button("Take    ") {
      reactions += { case event.ButtonClicked(_) =>
        controller.take()
      }
    },
    new Button("Place   ") {
      reactions += { case event.ButtonClicked(_) =>
        printf("%d", inTa.text.toInt)
        controller.place(inTa.text.toInt - 1)
        if (controller.game.ERROR < 0) {
          errorPop("Diese Karte kann nicht gelegt werden").ret.open()
        }
        inTa.text = ""
      }
    }
  )

  def ret: BoxPanel =
    new BoxPanel(Orientation.Horizontal) {
      contents += inTa
      contents += buttons(0)
      contents += buttons(1)
      contents += buttons(2)
    }

}
