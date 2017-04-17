package com.example.NQueensSpec

import com.example.Queen
import org.scalatest.{FunSpec, Matchers}

class NQueensSpec extends FunSpec with Matchers {
  describe("A Queen") {
    it("can see another queen at the same position") {
      val aQueen = Queen(1, 2)
      val anotherQueen = Queen(1, 2)

      aQueen.canAttack(anotherQueen) shouldBe true
    }

    it("can see another queen on the same column") {
      val aQueen = Queen(1, 2)
      val anotherQueen = Queen(1, 3)

      aQueen.canAttack(anotherQueen) shouldBe true
    }

    it("can see another queen on the same row") {
      val aQueen = Queen(1, 2)
      val anotherQueen = Queen(3, 2)

      aQueen.canAttack(anotherQueen) shouldBe true
    }

    it("cannot see another queen on a different position") {
      val aQueen = Queen(1, 1)
      val anotherQueen = Queen(3, 2)

      aQueen.canAttack(anotherQueen) shouldBe false
    }

    it("can see another queen on the same growing diagonal") {
      val aQueen = Queen(1, 2)
      val anotherQueen = Queen(2, 3)

      aQueen.canAttack(anotherQueen) shouldBe true
    }

    it("can see another queen on the same ungrowing diagonal") {
      val aQueen = Queen(1, 0)
      val anotherQueen = Queen(0, 1)

      aQueen.canAttack(anotherQueen) shouldBe true
    }
  }
}
