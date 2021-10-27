val uno = """
    +-------------------------------------+
    |  +---+---+---+---+                                   |
    |                                     |
    |                                     |
    |               +-----+               |
    |               | ROT |               |
    |               |  9  |               |
    |               |     |               |  
    |               +-----+               |
    |                                     |
    |                                     |
    |                                     |
    |                                     |
    +-------------------------------------+

    """

val row = ("+" + "-" * 2)
val rowEnd = "+"

val mid = ("|" + " " * 2)
val midEnd = "|"

val unoKarte = row + "\n" + mid + "\n" + row
val unoKarteEnd = rowEnd + "\n" + midEnd + "\n" + rowEnd

def p1(cCount: Int) =
  row * cCount + rowEnd + "\n" + mid * cCount + midEnd + "\n" + row * cCount + rowEnd

def p2(cCount: Int) =
  unoKarte * cCount + unoKarteEnd

p1(2)

p2(2)
