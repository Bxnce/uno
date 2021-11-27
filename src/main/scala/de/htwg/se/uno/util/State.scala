package de.htwg.se.uno
package util

import model.Game._
import model.Game

trait State {
  def handle(command: Command): Game
  def changeState(): Game
}
