package de.htwg.se.uno
package aview

import scala.util.Random
import scala.swing._
import controller.Controller
import de.htwg.se.uno.util.Observer

class GUI(controller: Controller) extends Observer:
  override def update: Unit =
    print(controller.toString())
  val r = scala.util.Random
  var ind = "Hier"
  val test: FlowPanel = new FlowPanel()
  test.contents += new Label("Test")
  new Frame {
    title = "Welcome to worst UNO euw"

    contents = new FlowPanel {
      contents += new Button("Take Card") {
        reactions += { case event.ButtonClicked(_) =>
          controller.take()
        }
      }
      var tf = new TextField(ind: String)
      contents += tf
      contents += new Button("Place Card") {
        reactions += { case event.ButtonClicked(_) =>
          controller.place(
            r.nextInt(
              controller.game
                .pList(0)
                .karten
                .size
                .max(controller.game.pList(1).karten.size)
            )
          )
        }
      }
      contents += new Button("Next") {
        reactions += { case event.ButtonClicked(_) =>
          controller.next()
        }
      }
      contents += test
      def printGUI() =
        for (i <- 1 to controller.game.pList(0).karten.size) {
          contents += new Button(
            controller.game.pList(0).karten(i - 1).toString
          )
        }
    }
    pack()
    centerOnScreen()
    open()
  }
