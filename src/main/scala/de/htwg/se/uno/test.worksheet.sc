1 + 2

val name = "Bence"

//Listen
val int_liste = List(3, 2, 1)
val string_liste = List("Hallo", "World", "A")
val double_liste = List(3.14149, 2.7, 1.0)

//Methode
def f(x: Int) = x + 1
f(8)

int_liste.sorted
string_liste.sorted
double_liste.sorted

class Mensch(Name: String, Alter: Int)

//Vorlesungsbeispiel
case class Cell(value: Int) {

  def isSet: Boolean = value != 0 // ---> Methode

}

val cell1 = Cell(1)
cell1.isSet

val cell0 = Cell(0)
cell0.isSet

val cell2 = Cell(2)
cell2.isSet

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))

field1.cells(0) = cell1

case class House(cells: Vector[Cell])

val house = House(Vector(cell1, cell2))

house.cells(0).value
house.cells(0).isSet

import de.hwtg.se.uno.Card
import Card._

import scala.collection.immutable.HashMap

val map = HashMap(R0 -> 2, B0 -> 2, G0 -> 2, Y0 -> 2)

val y = map + (R0 -> 1)

y(R0)

R0.getColor