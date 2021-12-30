package de.htwg.se.uno.aview.GUI

import scala.swing.{Dialog, Label, Dimension}
import javax.swing.ImageIcon

case class errorPop(text: String, cardErr: ImageIcon) {

  def ret: Dialog = new Dialog() {
    centerOnScreen
    title = "ERROR"
    contents = new Label(text) {
      icon = cardErr
    }
    maximumSize = new Dimension(150, 75)
    minimumSize = new Dimension(150, 75)
    preferredSize = new Dimension(150, 75)
  }
}
