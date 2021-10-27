enum Stone:
  case X, O, Empty

  case class Matrix(rows: Vector[Vector[Stone]]):
    def cell(row: Int, col: Int) = rows(row)(col)
    def fill(filling: Stone): Matrix = copy(Vector.tabulate(3, 3) {
      (row, col) => filling
    })
