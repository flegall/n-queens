package com.example

case class Queen(x: Int, y: Int)

object NQueens {
  def main(args: Array[String]): Unit = {
    println(solve(8))
  }

  def solve(n: Int): Seq[Queen] = {
    Seq()
  }
}
