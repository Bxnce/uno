package de.htwg.se.uno
package model.fileIOComponent

import model.gameComponent.gameInterface

trait FileIOInterface {
  def load: gameInterface
  def save(game: gameInterface): Unit
}

object FileIOInterface {
  def apply(): FileIOInterface =
    new JSONImpl.fileIO()
}
