package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import de.htwg.se.uno.util.Observer
import scala.swing.{
  BoxPanel,
  Orientation,
  GridPanel,
  Label,
  MainFrame,
  BorderPanel,
  Frame,
  FlowPanel
}
import java.awt.{Image, Toolkit, Color, Dimension}
import javax.swing.BorderFactory
import java.awt.FlowLayout
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.winState

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
  val cardStack = displayCards(controller).getStackImage

  contents = preShow

  override def update: Unit =
    output.text = controller.toString

    if (controller.game.currentstate == winState) {
      winPop(controller).ret.open()
    }
    show.contents -= midCardandStack
    midCardandStack.contents -= cardMid
    show.contents -= cardsPlayer

    cardMid = displayCards(controller).getCardImageMid
    cardsPlayer = displayCards(controller).createBoxLayout

    midCardandStack.contents += cardMid
    show.contents += midCardandStack
    show.contents += cardsPlayer

    contents = show

  val midCardandStack = new FlowPanel() {
    contents += cardStack
    contents += cardMid
  }

  val upperLine = new BoxPanel(Orientation.Horizontal) {
    contents += midCardandStack
    contents += butts.ret
  }

  val show = new BoxPanel(Orientation.Vertical) {
    contents += upperLine
    contents += cardsPlayer
  }

  pack()
  resizable = false
  minimumSize = new Dimension(550, 300)
  maximumSize = new Dimension(550, 300)
  preferredSize = new Dimension(300, 500)
  centerOnScreen()
  open()
}
