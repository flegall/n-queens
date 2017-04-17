package com.example

import scala.util.Sorting

case class Queen(x: Int, y: Int) extends Ordered[Queen] {
  def canSee(queen: Queen): Boolean = {
    queen.x == this.x || queen.y == this.y ||
    queen.x - queen.y == this.x - this.y ||
    queen.x + queen.y == this.x + this.y
  }

  def canInsertInto(queens: Seq[Queen]): Boolean = {
    !queens.exists(q => q.canSee(this))
  }

  import scala.math.Ordered.orderingToOrdered
  def compare(that: Queen): Int =
    (this.x, this.y) compare (that.x, that.y)
}

object NQueens {
  def main(args: Array[String]): Unit = {
    val N = 8
    var solutions: Set[Seq[Queen]] = Set()

    solve(N, (solution: Seq[Queen]) => {
      if (!solutions.contains(solution)) {
        solutions = solutions + solution
        printSolution(N, solution)
      }
    })

    println(s"${solutions.size} solutions found")
  }

  private def solve(n: Int,
                    onFound: (Seq[Queen]) => Unit,
                    level: Int = 0,
                    queens: Seq[Queen] = Seq()): Unit = {
    if (level < n) {
      for (x <- 0 until n) {
        for (y <- 0 until n) {
          val queen = Queen(x, y)
          if (queen.canInsertInto(queens)) {
            val updatedQueens = queens :+ queen
            solve(n, onFound, level + 1, updatedQueens)
          }
        }
      }
    } else {
      onFound(Sorting.stableSort(queens))
    }
  }

  private def printSolution(n: Int, solution: Seq[Queen]) = {
    val grid = solution.to[Set]

    println("----------")

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

    println("----------")
  }
}
