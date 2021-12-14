package de.htwg.se.uno
package aview

import scala.util.Random
import scala.swing._
import controller.Controller
import de.htwg.se.uno.util.Observer
import scala.swing._
import java.awt.Color

import model.CardColor
import model.CardValue
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import javax.swing.ImageIcon
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File

class GUI(controller: Controller) extends MainFrame with Observer {
  controller.add(this)
  override def update: Unit =
    out.text = controller.toString
    out.repaint
    unofield(controller)
    test.repaint
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

  def newCard(player: Int, index: Int): ImageIcon = {
    val c = controller.game.pList(player).karten(index)
    var color: String = ""

    c.getColor match {
      case CardColor.Red    => color = "red_"
      case CardColor.Blue   => color = "blue_"
      case CardColor.Green  => color = "green_"
      case CardColor.Yellow => color = "yellow_"
    }

    var number: String = ""
    c.getValue match {
      case CardValue.Zero  => number = "0"
      case CardValue.One   => number = "1"
      case CardValue.Two   => number = "2"
      case CardValue.Three => number = "3"
      case CardValue.Four  => number = "4"
      case CardValue.Five  => number = "5"
      case CardValue.Six   => number = "6"
      case CardValue.Seven => number = "7"
      case CardValue.Eight => number = "8"
      case CardValue.Nine  => number = "9"
    }
    val imagePath = "src/main/resources/cards/" + color + number + ".png"
    val image: BufferedImage =
      Try(ImageIO.read(new File(imagePath))) match {
        case s: Success[BufferedImage] => s.value
        case f: Failure[BufferedImage] =>
          new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)
      }
    new ImageIcon(image)
  }

  def maxCards: Int = {
    val a = controller.game.pList(0).karten.size
    val b = controller.game.pList(1).karten.size
    if (a > b) {
      a
    } else {
      b
    }
  }

  val maxTest = new Button("Max") {
    reactions += { case event.ButtonClicked(_) =>
      val a = maxCards
      inTa.text = a.toString
    }
  }

  def unofield(controller: Controller) = new GridPanel(2, maxCards) {
    val max = maxCards
    for {
      row <- 0 to 2 - 1
      col <- 0 until max
    } {
      contents += (col match {
        case -1 =>
          new Label((row + 1).toString) {
            preferredSize = new Dimension(30, 100)
          }
        case _ => {
          new Label("", newCard(row, col), Alignment.Center)
        }
      })
    }
  }

  val test = new BorderPanel {
    add(unofield(controller), BorderPanel.Position.Center)
    //contents += maxTest
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
    contents += test
  }
  contents = all
  pack()
  centerOnScreen()
  open()
}
