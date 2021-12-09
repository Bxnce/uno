package de.htwg.se.uno
package aview

import scala.util.Random
import scala.swing._
import controller.Controller
import de.htwg.se.uno.util.Observer
import scala.swing._
import java.awt.Color

class GUI(controller: Controller) extends MainFrame with Observer {
  controller.add(this)
  override def update: Unit =
    out.text = controller.toString
    out.repaint
    this.repaint
  val inTa = new TextArea("Input here") {
    maximumSize = new Dimension(100, 25)
    minimumSize = new Dimension(100, 25)
    preferredSize = new Dimension(100, 25)
    background = Color.BLUE.brighter
    foreground = Color.WHITE
  }

  val popErr = new Dialog() {
    centerOnScreen
    title = "ERROR"
    contents = new Label("Diese Karte kann nicht gelegt werden!")
    maximumSize = new Dimension(150, 75)
    minimumSize = new Dimension(150, 75)
    preferredSize = new Dimension(150, 75)
  }
  val popIn = new Dialog() {
    centerOnScreen
    title = "Input"
    contents = new FlowPanel() {
      contents += new Label("Input: ")
      contents += inTa
    }
  }

  val ngBut = new Button("New Game") {
    reactions += { case event.ButtonClicked(_) =>
      val tmp = inTa.text.split(" ")
      inTa.text = ""
      controller.newG(tmp(0), tmp(1))

    }
  }
  val nBut = new Button("Next    ") {
    reactions += { case event.ButtonClicked(_) =>
      controller.next()
    }
  }
  val tBut = new Button("Take    ") {
    reactions += { case event.ButtonClicked(_) =>
      controller.take()
    }
  }
  val pBut = new Button("Place   ") {
    reactions += { case event.ButtonClicked(_) =>
      printf("%d", inTa.text.toInt)
      controller.place(inTa.text.toInt - 1)
      if (controller.game.ERROR < 0) {
        popErr.open()
      }
      inTa.text = ""
    }
  }

  val out = new TextPane() {
    font = new Font("Monaco", 14, 14)
    maximumSize = new Dimension(415, 250)
    minimumSize = new Dimension(415, 250)
    preferredSize = new Dimension(415, 250)
  }
  title = "Uno"
  val buttons = new BoxPanel(Orientation.Horizontal) {

    contents += ngBut
    contents += nBut
    contents += tBut
    contents += pBut
  }
  val butandin = new BoxPanel(Orientation.Horizontal) {
    contents += inTa
    contents += buttons
  }
  val all = new BoxPanel(Orientation.Vertical) {
    contents += butandin
    contents += out
  }
  contents = all
  pack()
  centerOnScreen()
  open()
}
