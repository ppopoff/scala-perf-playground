package playground.rle

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._


@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Array(Mode.AverageTime, Mode.SampleTime))
@State(Scope.Benchmark)
class RLEBenchmark {
  private val string_1 = "gqqIwgjj"
  private val string_2 = "AAAABBBCCDDDEEFFFEEEEEEEEEEEEEEHHHHXXXRRRRRRRRR"

  @Benchmark
  def testImmutable(): Unit = {
    RLE.runLengthEncode(string_1)
    RLE.runLengthEncode(string_2)
  }

  @Benchmark
  def testMutable(): Unit = {
    MutableRle.runLengthEncode(string_1)
    MutableRle.runLengthEncode(string_2)
  }
}



