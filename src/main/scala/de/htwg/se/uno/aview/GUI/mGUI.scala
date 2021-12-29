package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import scala.swing.MainFrame
import de.htwg.se.uno.util.Observer
import scala.swing.BoxPanel
import scala.swing.Orientation
import scala.swing.GridPanel
import scala.swing.Label

class mGUI(controller: controllerInterface) extends MainFrame with Observer {
  title = "BEST UNO EUW"
  controller.add(this)

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
    contents += output
  }

  pack()
  centerOnScreen()
  open()
}
