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
import scala.swing.BoxPanel
import scala.collection.mutable.ListBuffer
import controller._
import scala.swing.Orientation

final case class displayCards(controller: controllerInterface) {

  def getImageIcon(player: Player, ind: Int): ImageIcon =

    val play = player.karten
    var color = ""
    var value = ""

    play(ind).getColor match
      case CardColor.Red      => val color = "red_"
      case CardColor.Blue     => val color = "blue_"
      case CardColor.Green    => val color = "green_"
      case CardColor.Yellow   => val color = "yellow_"
      case CardColor.SpecialC => val color = ""
      case CardColor.ErrorC   => val color = ""

    play(ind).getValue match
      case CardValue.Zero    => val value = "0"
      case CardValue.One     => val value = "1"
      case CardValue.Two     => val value = "2"
      case CardValue.Three   => val value = "3"
      case CardValue.Four    => val value = "4"
      case CardValue.Five    => val value = "5"
      case CardValue.Six     => val value = "6"
      case CardValue.Seven   => val value = "7"
      case CardValue.Eight   => val value = "8"
      case CardValue.Nine    => val value = "9"
      case CardValue.Special => val value = ""
      case CardValue.Error   => val value = ""

    val imagePath = "src/main/resources/cards/" + color + value + ".png"

    val image: BufferedImage = Try(ImageIO.read(new File(imagePath))) match {
      case s: Success[BufferedImage] => s.value
      case f: Failure[BufferedImage] =>
        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)
    }
    new ImageIcon(image)

  def getCardImagesAsList: ListBuffer[ImageIcon] =
    var currentPlayer: Player = controller.game.pList(0)

    controller.game.currentstate match
      case player1state => currentPlayer = controller.game.pList(0)
      case player2state => currentPlayer = controller.game.pList(1)
      case _            =>
    if(controller.gamn)
    val xx = new ListBuffer[ImageIcon]
    xx

  def createBoxLayout(): BoxPanel =
    new BoxPanel(Orientation.Horizontal)

}
