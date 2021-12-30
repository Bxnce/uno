package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import de.htwg.se.uno.util.Observer
import scala.swing.{BoxPanel, Orientation, GridPanel, Label, MainFrame}
import java.awt.{Color, Dimension}
import javax.swing.BorderFactory
import java.awt.Image
import java.awt.Toolkit

class mGUI(controller: controllerInterface) extends MainFrame with Observer {
  title = "BEST UNO EUW"
  controller.add(this)
  iconImage =
    Toolkit.getDefaultToolkit.getImage("src/main/resources/cards/unknown.png")
  val butts = buttonsPanel(controller)
  val output = stringout
  val preShow = createGame(controller).ret
  var cardsPlayer = displayCards(controller).createBoxLayout
  var cardMid = displayCards(controller).getCardImageMid

  contents = preShow

  override def update: Unit =
    output.text = controller.toString

    show.contents -= cardMid
    show.contents -= cardsPlayer
    cardMid = displayCards(controller).getCardImageMid
    cardsPlayer = displayCards(controller).createBoxLayout
    show.contents += cardMid
    show.contents += cardsPlayer

    contents = show

  val show = new BoxPanel(Orientation.Vertical) {
    contents += butts.ret
    contents += cardMid
    contents += cardsPlayer
  }

  pack()
  minimumSize = new Dimension(550, 300)
  maximumSize = new Dimension(550, 300)
  preferredSize = new Dimension(300, 500)
  centerOnScreen()
  open()
}
