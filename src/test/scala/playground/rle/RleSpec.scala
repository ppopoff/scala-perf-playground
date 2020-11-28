package playground.rle

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers._

class RleSpec extends AnyFunSpec {
  describe("RLE") {
    it ("should deal leave the compact sequence as it is"){
      RLE.runLengthEncode("AB") should equal ("AB")
    }

    it ("should handle a regular case") {
      RLE.runLengthEncode("AABBBC") should equal ("A2B3C")
    }

    it ("should handle an empty string") {
      RLE.runLengthEncode("") shouldBe empty
    }

    it ("should handle a single character string") {
      RLE.runLengthEncode("x") should equal ("x")
    }

    it ("should handle an abstract case") {
      RLE.runLengthEncode("isnwgsqDcobuzmcqshfYbwivahtFjldizBtonkwAvuUdojilfjusnkSWarZlybdsxdyGnqlqobnvcc") should
        equal("isnwgsqDcobuzmcqshfYbwivahtFjldizBtonkwAvuUdojilfjusnkSWarZlybdsxdyGnqlqobnvc2")
    }

    it ("should handle case with mutiple same characters at the end") {
      RLE.runLengthEncode("gqqIwgjj") should equal ("gq2Iwgj2")
      RLE.runLengthEncode("wgjjj") should equal ("wgj3")
    }
  }
}

class MutableRleSpec extends AnyFunSpec {
  describe("RLE") {
    it ("should deal leave the compact sequence as it is"){
      MutableRle.runLengthEncode("AB") should equal ("AB")
    }

    it ("should handle a regular case") {
      MutableRle.runLengthEncode("AABBBC") should equal ("A2B3C")
    }

    it ("should handle an empty string") {
      MutableRle.runLengthEncode("") shouldBe empty
    }

    it ("should handle a single character string") {
      MutableRle.runLengthEncode("x") should equal ("x")
    }

    it ("should handle an abstract case") {
      MutableRle.runLengthEncode("isnwgsqDcobuzmcqshfYbwivahtFjldizBtonkwAvuUdojilfjusnkSWarZlybdsxdyGnqlqobnvcc") should
        equal("isnwgsqDcobuzmcqshfYbwivahtFjldizBtonkwAvuUdojilfjusnkSWarZlybdsxdyGnqlqobnvc2")
    }

    it ("should handle case with mutiple same characters at the end") {
      MutableRle.runLengthEncode("gqqIwgjj") should equal ("gq2Iwgj2")
      MutableRle.runLengthEncode("wgjjj") should equal ("wgj3")
    }
  }
}

class MutableRleReference extends Properties("RLE Reference") {
  property("reference") = forAll (Gen.alphaStr) { (str: String) =>
    val imm = RLE.runLengthEncode(str)
    val mut = MutableRle.runLengthEncode(str)
    imm == mut
  }
}
