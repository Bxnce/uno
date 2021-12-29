package de.htwg.se.uno
package aview.GUI

import model.gameComponent.gameBaseImpl._
import javax.swing.ImageIcon
import controller.controllerComponent.controllerInterface
import scala.util.Success
import java.awt.image.BufferedImage
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import java.io.File
import javax.imageio.ImageIO
import scala.swing.GridPanel
import scala.collection.mutable.ListBuffer
import controller._
import scala.swing.Orientation
import de.htwg.se.uno.util.State
import scala.swing.Label
import scala.swing.Alignment
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.player1State
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.player2State
import java.awt.GridLayout

case class displayCards(controller: controllerInterface) {

  def getImageIcon(player: Player, ind: Int): ImageIcon =

    val c = player.karten(ind)
    var color = ""
    var value = ""
    c.getColor match
      case CardColor.Red =>
        color = "red_"
        println("rot")
      case CardColor.Blue =>
        color = "blue_"
        println("blau")
      case CardColor.Green =>
        color = "green_"
        println("gruen")
      case CardColor.Yellow =>
        color = "yellow_"
        println("gelb")
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
      for (i <- 0 to imageList.size - 1) {
        contents += new Label {
          icon = imageList(i)
        }
      }
    }

  def getCardImageMid: GridPanel =
    new GridPanel(1, 1) {
      contents += new Label {
        icon = getImageIcon(controller.game.midCard, 0)
      }
    }
}
