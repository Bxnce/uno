package de.htwg.se.uno
package util

import model.Game._
import model.Game
import model.CardLayout._

trait Strategy {
  def execute():Unit
}


case class pick(g: Game) extends Strategy {
  g.currentstate match
    


}


case class stratP1(g: Game) extends Strategy {



}





