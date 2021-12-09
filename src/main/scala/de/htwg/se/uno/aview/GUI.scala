package de.htwg.se.uno
package aview

import scala.util.Random
import scala.swing._
import controller.Controller
import de.htwg.se.uno.util.Observer

class GUI(controller: Controller) extends MainFrame with Observer:

  val inTa = new TextArea("Input here") {
    maximumSize = new Dimension(100, 20)
    minimumSize = new Dimension(100, 20)
    preferredSize = new Dimension(100, 20)
  }
  val ngBut = new Button("New Game") {
    reactions += { case event.ButtonClicked(_) =>
      val tmp = inTa.text.split(" ")
      controller.newG(tmp(0), tmp(1))
    }
  }
  val nBut = new Button("Next") {
    reactions += { case event.ButtonClicked(_) =>
      controller.next()
    }
  }
  val tBut = new Button("Take") {
    reactions += { case event.ButtonClicked(_) =>
      controller.take()
    }
  }
  val pBut = new Button("Place") {
    reactions += { case event.ButtonClicked(_) =>
      printf("%d", inTa.text.toInt)
      controller.place(inTa.text.toInt - 1)
    }
  }

  val out = new TextPane() {
    maximumSize = new Dimension(100, 200)
    minimumSize = new Dimension(100, 200)
    preferredSize = new Dimension(100, 200)
  }
  title = "Uno"
  contents = new FlowPanel {
    contents += inTa
    contents += ngBut
    contents += nBut
    contents += tBut
    contents += pBut
    contents += out
  }
  pack()
  centerOnScreen()
  open()
  override def update: Unit =
    out.text = "Bonjour"
    out.repaint
    this.repaint
/*val r = scala.util.Random
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
 */
