package de.htwg.se.uno.aview.GUI

import scala.swing.Dialog
import scala.swing.Label
import scala.swing.Dimension

case class errorPop(text: String) {

  def ret: Dialog = new Dialog() {
    centerOnScreen
    title = "ERROR"
    contents = new Label(text)
    maximumSize = new Dimension(150, 75)
    minimumSize = new Dimension(150, 75)
    preferredSize = new Dimension(150, 75)
  }
}
