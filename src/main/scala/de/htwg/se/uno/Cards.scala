package de.htwg.se.uno

enum CardValue:
  case Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Special

enum CardColor:
  case Red, Blue, Green, Yellow, Special

enum Card(color: CardColor, value: CardValue, id: String):
  case R0 extends Card(CardColor.Red, CardValue.Zero, "R0")
  case B0 extends Card(CardColor.Blue, CardValue.Zero, "B0")
  case G0 extends Card(CardColor.Green, CardValue.Zero, "G0")
  case Y0 extends Card(CardColor.Yellow, CardValue.Zero, "Y0")

  def getColor: CardColor = color
  def getValue: CardValue = value
  override def toString: String = id
