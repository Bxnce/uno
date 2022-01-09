package de.htwg.se.uno
package aview.GUI

import model.gameComponent.gameBaseImpl._
import controller.controllerComponent.controllerBaseImpl._
import controller.controllerComponent.controllerInterface
import javax.swing.{ImageIcon, BorderFactory}
import java.awt.image.BufferedImage
import scala.util.{Try, Success, Failure}
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.ListBuffer
import scala.swing.{
  Alignment,
  Label,
  Orientation,
  GridPanel,
  FlowPanel,
  BoxPanel,
  Dimension
}
import java.awt.{GridLayout, Color}
import scala.swing.event.MouseClicked
import scala.swing.Font

case class displayCards(controller: controllerInterface) {

  def getImageIcon(player: Player, ind: Int): ImageIcon =

    val c = player.karten(ind)
    var color = ""
    var value = ""
    c.getColor match
      case CardColor.Red      => color = "red_"
      case CardColor.Blue     => color = "blue_"
      case CardColor.Green    => color = "green_"
      case CardColor.Yellow   => color = "yellow_"
      case CardColor.SpecialC => color = ""
      case CardColor.ErrorC   => color = ""
    c.getValue match
      case CardValue.Zero    => value = "0"
      case CardValue.One     => value = "1"
      case CardValue.Two     => value = "2"
      case CardValue.Three   => value = "3"
      case CardValue.Four    => value = "4"
      case CardValue.Five    => value = "5"
      case CardValue.Six     => value = "6"
      case CardValue.Seven   => value = "7"
      case CardValue.Eight   => value = "8"
      case CardValue.Nine    => value = "9"
      case CardValue.Special => value = ""
      case CardValue.Error   => value = ""

    val imagePath = "src/main/resources/cards/" + color + value + ".png"

    val image: BufferedImage = Try(ImageIO.read(new File(imagePath))) match {
      case s: Success[BufferedImage] => s.value
      case f: Failure[BufferedImage] =>
        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)
    }
    new ImageIcon(image)

  def getCardImagesAsList: ListBuffer[ImageIcon] =
    var currentPlayer: Player = controller.game.pList(0)
    val currentstate = controller.game.currentstate

    var imageList = new ListBuffer[ImageIcon];

    if (currentstate.equals(player1State)) {
      currentPlayer = controller.game.pList(0)
      for (i <- 0 to currentPlayer.karten.size - 1) {
        imageList += getImageIcon(currentPlayer, i);
      }
    } else if (currentstate.equals(player2State)) {
      currentPlayer = controller.game.pList(1)
      for (i <- 0 to currentPlayer.karten.size - 1) {
        imageList += getImageIcon(currentPlayer, i);
      }
    } else {}
    imageList

  def createBoxLayout: GridPanel =
    val imageList = getCardImagesAsList
    new GridPanel(2, imageList.size) {
      if (imageList.size != 0) then
        border = BorderFactory.createLineBorder(Color.BLACK, 3)
      for (i <- 0 to imageList.size - 1) {
        contents += new Label {
          icon = imageList(i)
          listenTo(mouse.clicks)
          reactions += { case e: MouseClicked =>
            controller.place(i)
            if (controller.game.ERROR != 0) {
              errorPop(
                "This card can not be placed!",
                imageList(i)
              ).ret.open()
              controller.game = controller.game.setError(0)
            }
          }
        }
      }
    }

  def getText: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    val currentstate = controller.game.currentstate
    contents += new Label() {
      font = new Font("Arial", 3, 22)
      if (currentstate.equals(player1State)) {
        text = controller.game
          .pList(0)
          .name + "'s turn; opponent has " + controller.game
          .pList(1)
          .karten
          .size
          .toString + " card(s)"
      } else if (currentstate.equals(player2State)) {
        text = controller.game
          .pList(1)
          .name + "'s turn; opponent has " + controller.game
          .pList(0)
          .karten
          .size
          .toString + " card(s)"
      } else if (currentstate.equals(between12State)) {
        foreground = Color.RED
        text = controller.game
          .pList(1)
          .name + " is next and has " + controller.game
          .pList(1)
          .karten
          .size
          .toString + " card(s)"
      } else if (currentstate.equals(between21State)) {
        foreground = Color.RED
        text = controller.game
          .pList(0)
          .name + " is next and has " + controller.game
          .pList(0)
          .karten
          .size
          .toString + " card(s)"
      }
    }
  }

  def getCardImageMid: GridPanel =
    new GridPanel(1, 1) {
      border = BorderFactory.createEmptyBorder(0, 50, 0, 0)
      contents += new Label {
        icon = getImageIcon(controller.game.midCard, 0)
      }
    }

  def getStackImage: GridPanel =
    new GridPanel(1, 1) {
      border = BorderFactory.createEmptyBorder(0, 0, 0, 50)
      contents += new Label {
        listenTo(mouse.clicks)
        reactions += { case e: MouseClicked =>
          controller.take()
        }
        icon = ImageIcon("src/main/resources/cards/uno_back.png")
      }
    }
}
