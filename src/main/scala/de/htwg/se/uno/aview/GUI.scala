package de.htwg.se.uno.aview

import scala.swing._
/*
object Helloworld extends SimpleSweingApplication {
  def top = new MainFrame {
    title = "Hello, World!"
    contents = new Button {
      text = "Click Me!"
    }
  }
}
 */

class GUI:
  new Frame {
    title = "Hello world"

    contents = new FlowPanel {
      contents += new Label("Launch rainbows:")
      contents += new Button("Click me") {
        reactions += { case event.ButtonClicked(_) =>
          println("All the colours!")
        }
      }
    }
    pack()
    centerOnScreen()
    open()
  }
