package de.htwg.se.uno

enum CardValue:
  case Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Special

enum CardColor:
  case Red, Blue, Green, Yellow, Special

enum Card(color: CardColor, value: CardValue, id: String):
  case R0 extends Card(CardColor.Red, CardValue.Zero, "R0")
  case R1 extends Card(CardColor.Red, CardValue.One, "R1")
  case R2 extends Card(CardColor.Red, CardValue.Two, "R2")
  case R3 extends Card(CardColor.Red, CardValue.Three, "R3")
  case R4 extends Card(CardColor.Red, CardValue.Four, "R4")
  case R5 extends Card(CardColor.Red, CardValue.Five, "R5")
  case R6 extends Card(CardColor.Red, CardValue.Six, "R6")
  case R7 extends Card(CardColor.Red, CardValue.Seven, "R7")
  case R8 extends Card(CardColor.Red, CardValue.Eight, "R8")
  case R9 extends Card(CardColor.Red, CardValue.Nine, "R9")

  case B0 extends Card(CardColor.Blue, CardValue.Zero, "B0")
  case B1 extends Card(CardColor.Blue, CardValue.One, "B1")
  case B2 extends Card(CardColor.Blue, CardValue.Two, "B2")
  case B3 extends Card(CardColor.Blue, CardValue.Three, "B3")
  case B4 extends Card(CardColor.Blue, CardValue.Four, "B4")
  case B5 extends Card(CardColor.Blue, CardValue.Five, "B5")
  case B6 extends Card(CardColor.Blue, CardValue.Six, "B6")
  case B7 extends Card(CardColor.Blue, CardValue.Seven, "B7")
  case B8 extends Card(CardColor.Blue, CardValue.Eight, "B8")
  case B9 extends Card(CardColor.Blue, CardValue.Nine, "B9")

  case G0 extends Card(CardColor.Green, CardValue.Zero, "G0")
  case G1 extends Card(CardColor.Green, CardValue.One, "G1")
  case G2 extends Card(CardColor.Green, CardValue.Two, "G2")
  case G3 extends Card(CardColor.Green, CardValue.Three, "G3")
  case G4 extends Card(CardColor.Green, CardValue.Four, "G4")
  case G5 extends Card(CardColor.Green, CardValue.Five, "G5")
  case G6 extends Card(CardColor.Green, CardValue.Six, "G6")
  case G7 extends Card(CardColor.Green, CardValue.Seven, "G7")
  case G8 extends Card(CardColor.Green, CardValue.Eight, "G8")
  case g9 extends Card(CardColor.Green, CardValue.Nine, "G9")

  case Y0 extends Card(CardColor.Yellow, CardValue.Zero, "Y0")
  case Y1 extends Card(CardColor.Yellow, CardValue.One, "Y1")
  case Y2 extends Card(CardColor.Yellow, CardValue.Two, "Y2")
  case Y3 extends Card(CardColor.Yellow, CardValue.Three, "Y3")
  case Y4 extends Card(CardColor.Yellow, CardValue.Four, "Y4")
  case Y5 extends Card(CardColor.Yellow, CardValue.Five, "Y5")
  case Y6 extends Card(CardColor.Yellow, CardValue.Six, "Y6")
  case Y7 extends Card(CardColor.Yellow, CardValue.Seven, "Y7")
  case Y8 extends Card(CardColor.Yellow, CardValue.Eight, "Y8")
  case Y9 extends Card(CardColor.Yellow, CardValue.Nine, "Y9")

  def getColor: CardColor = color
  def getValue: CardValue = value
  override def toString: String = id
