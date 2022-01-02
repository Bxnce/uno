package de.htwg.se.uno.aview.GUI

import scala.swing.{
  Dialog,
  Label,
  Dimension,
  Orientation,
  BoxPanel,
  Font,
  Button,
  event
}
import javax.swing.ImageIcon
import de.htwg.se.uno.controller.controllerComponent.controllerInterface
import scala.swing.event.MouseClicked

case class winPop(controller: controllerInterface) {

  def ret: Dialog = new Dialog() {
    centerOnScreen
    title = "winner: " + controller.game.pList(controller.game.winner).name

    contents = new BoxPanel(Orientation.Vertical) {
      contents += new Label(
        controller.game.pList(controller.game.winner).name + " won"
      ) {
        font = new Font("Arial", 0, 80)
      }
      contents += new Label("restart game") {
        listenTo(mouse.clicks)
        font = new Font("Arial", 10, 80)
        reactions += { case e: MouseClicked =>
          controller.next()
          dispose()
        }
      }
    }
    maximumSize = new Dimension(150, 75)
    minimumSize = new Dimension(150, 75)
    preferredSize = new Dimension(150, 75)
  }
}
