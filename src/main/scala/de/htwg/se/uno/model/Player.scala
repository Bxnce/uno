package de.htwg.se.uno.model
package model

case class Player(n: String) {
  val name = n
  override def toString: String = name
}
