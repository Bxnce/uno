package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import scala.swing.MainFrame
import de.htwg.se.uno.util.Observer
import scala.swing.BoxPanel
import scala.swing.Orientation

class mGUI(controller: controllerInterface) extends MainFrame with Observer {
  title = "BEST UNO EUW"
  controller.add(this)

  val butts = buttonsPanel(controller)
  val output = stringout
  val preShow = createGame(controller).ret

  contents = preShow

  override def update: Unit =
    output.text = controller.toString
    contents = show

  val show = new BoxPanel(Orientation.Vertical) {
    contents += butts.ret
    contents += output
  }

  pack()
  centerOnScreen()
  open()
}
