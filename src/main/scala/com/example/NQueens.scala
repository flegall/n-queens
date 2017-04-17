package com.example

case class Queen(x: Int, y: Int) {
  def canAttack(anotherQueen: Queen): Boolean = {
    anotherQueen.x == this.x || anotherQueen.y == this.y ||
    anotherQueen.x - anotherQueen.y == this.x - this.y ||
    anotherQueen.x + anotherQueen.y == this.x + this.y
  }

  def canInsertInto(queens: Seq[Queen]): Boolean = {
    !queens.exists(q => q.canAttack(this))
  }
}

object NQueens {
  def main(args: Array[String]): Unit = {
    val N = 8
    var solutions: Seq[Seq[Queen]] = Seq()

    solve(N, (solution: Seq[Queen]) => {
      solutions = solutions :+ solution
    })

    solutions.foreach(solution => printSolution(N, solution))
    println(s"${solutions.size} solutions found")
  }

  private def solve(n: Int,
                    onFound: (Seq[Queen]) => Unit,
                    level: Int = 0,
                    queens: Seq[Queen] = Seq()): Unit = {
    if (level < n) {
      for (x <- 0 until n) {
        val queen = Queen(x, level)
        if (queen.canInsertInto(queens)) {
          solve(n, onFound, level + 1, queens :+ queen)
        }
      }
    } else {
      onFound(queens)
    }
  }

  private def printSolution(n: Int, solution: Seq[Queen]) = {
    val grid = solution.to[Set]

    println("-" * (n + 2))

    for (y <- 0 until n) {
      print('|')

      for (x <- 0 until n) {
        if (grid.contains(Queen(x, y))) {
          print('x')
        } else {
          print(' ')
        }
      }

      println('|')
    }

    println("-" * (n + 2))
  }
}
