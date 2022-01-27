package de.htwg.se.uno
package model.fileIOComponent.JSONImpl

import model.gameComponent.gameInterface
import model.fileIOComponent.FileIOInterface
import model.gameComponent.gameBaseImpl.{CardStack, Card, Game, Player}
import model.gameComponent.gameBaseImpl.toCard._
import controller.controllerComponent.controllerBaseImpl.{
  player1State,
  player2State,
  between12State,
  between21State,
  winState
}
import util.State
import java.io._
import play.api.libs.json._
import scala.io.Source

class fileIO extends FileIOInterface {

  override def load: gameInterface =
    val source: String = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    val p1 = (json \ "game" \ "player1").get
    val p1n = (p1 \ "name").get.as[String]
    val p1ktmp = (p1 \ "kartenzahl").get.as[Int]
    var p1k: Vector[Card] =
      (for (i <- 0 until p1ktmp) yield {
        getCard((p1 \\ "cardv")(i).as[String])
      }).toVector
    val p1p = (p1 \ "placed").get.as[Boolean]
    val player1 = new Player(p1n, p1k, p1p)

    val p2 = (json \ "game" \ "player2").get
    val p2n = (p2 \ "name").get.as[String]
    val p2ktmp =
      (p2 \ "kartenzahl").get.as[Int]
    var p2k: Vector[Card] =
      (for (i <- 0 until p2ktmp) yield {
        getCard((p2 \\ "cardv")(i).as[String])
      }).toVector
    val p2p = (p2 \ "placed").get.as[Boolean]
    val player2 = new Player(p2n, p2k, p2p)

    val cs = (json \ "game" \ "currentstate").get.toString
    val csf = cs.replaceAll("\"", "")
    var currentstate: State = between21State
    csf match
      case "between12State" => currentstate = between12State
      case "between21State" => currentstate = between21State
      case "player1State"   => currentstate = player1State
      case "player2State"   => currentstate = player2State
      case "winState"       => currentstate = winState

    val ERROR = (json \ "game" \ "ERROR").get.as[Int]

    val mc = (json \ "game" \ "midCard").get
    val mcn = (mc \ "name").get.as[String]

    var mck: Vector[Card] = Vector(getCard((mc \\ "cardv")(0).as[String]))
    val mcp = (mc \ "placed").get.as[Boolean]
    val midcard = new Player(mcn, mck, mcp)

    val winner = (json \ "game" \ "winner").get.as[Int]

    val game = new Game(
      List(player1, player2),
      currentstate,
      ERROR,
      new CardStack(
        Card.values.map(x => (x, 2)).toMap
      ),
      midcard,
      winner
    )
    game

  override def save(game: gameInterface): Unit =
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJson(game)))
    pw.close

  def gameToJson(game: gameInterface) = {
    Json.obj(
      "game" -> Json.obj(
        "player1" -> Json.obj(
          "name" -> game.pList(0).name,
          "karten" -> vectorToJson(game.pList(0).karten),
          "kartenzahl" -> game.pList(0).karten.size,
          "placed" -> game.pList(0).placed
        ),
        "player2" -> Json.obj(
          "name" -> game.pList(1).name,
          "karten" -> vectorToJson(game.pList(1).karten),
          "kartenzahl" -> game.pList(1).karten.size,
          "placed" -> game.pList(1).placed
        ),
        "currentstate" -> game.currentstate.toString,
        "ERROR" -> game.ERROR,
        "cardstack" -> mapToJson(game.cardStack.cards),
        "midCard" -> Json.obj(
          "name" -> game.midCard.name,
          "karten" -> vectorToJson(game.midCard.karten),
          "placed" -> game.midCard.placed
        ),
        "winner" -> game.winner
      )
    )
  }

  def vectorToJson(vec: Vector[Card]) =
    Json.toJson(
      for {
        i <- vec
      } yield {
        Json.obj(
          "cardv" -> i.toString
        )
      }
    )

  def mapToJson(m: Map[Card, Int]) =
    Json.toJson(
      for { i <- m } yield {
        Json.obj(
          "cardv" -> i(0).toString,
          "value" -> i(1)
        )
      }
    )

}
