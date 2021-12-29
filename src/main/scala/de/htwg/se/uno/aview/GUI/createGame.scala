package de.htwg.se.uno
package aview.GUI

import controller.controllerComponent.controllerInterface
import scala.swing._
import java.awt.Color

case class createGame(controller: controllerInterface) {

  val name1 = new TextArea("Name 1") {
    maximumSize = new Dimension(100, 25)
    minimumSize = new Dimension(100, 25)
    preferredSize = new Dimension(100, 25)
    background = Color.GRAY.brighter
    foreground = Color.BLACK
  }

  val name2 = new TextArea("Name 2") {
    maximumSize = new Dimension(100, 25)
    minimumSize = new Dimension(100, 25)
    preferredSize = new Dimension(100, 25)
    background = Color.GRAY.brighter
    foreground = Color.BLACK
  }

  val button = new Button("create game") {
    reactions += { case event.ButtonClicked(_) =>
      controller.newG(name1.text, name2.text)
    }
  }

  def ret: BoxPanel =
    new BoxPanel(Orientation.Horizontal) {
      contents += name1
      contents += name2
      contents += button
    }
}
