package mapmerge

import java.util.concurrent.TimeUnit

import scala.collection.immutable.HashMap
import org.openjdk.jmh.annotations._


@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Array(Mode.AverageTime, Mode.SampleTime))
@State(Scope.Benchmark)
class MapMergeBenchmark {
  private val arr0 = Array(0, 0, 0).distinct
  private val arr1 = Array(1, 1).distinct
  private val arr2 = Array(2, 2).distinct
  private val arrKek = Array(11, 11).distinct
  private val arrLol = Array(22, 22).distinct

  // immutable hashmaps
  private val map1r: HashMap[String, Array[Int]] = HashMap("one" -> arr1, "two" -> arr2)
  private val map2r: HashMap[String, Array[Int]] = HashMap("one" -> arr1, "two" -> arr2, "zero" -> arr0)
  private val map3r: HashMap[String, Array[Int]] = HashMap("kek" -> arrKek, "lol" -> arrLol)

  private val maps_reference: List[HashMap[String, Array[Int]]] = List(map1r, map2r, map3r)

  // small maps
  private val map1a: Map[String, Array[Int]] = Map("one" -> arr1, "two" -> arr2)
  private val map2a = Map("one" -> arr1, "two" -> arr2, "zero" -> arr0)
  private val map3a = Map("kek" -> arrKek, "lol" -> arrLol)

  private val maps_prop_A = List(map1a, map2a, map3a)


  @Benchmark
  def testReference(): Unit = {
    MapMergeReference.mergeMaps(maps_reference)
  }

  @Benchmark
  def testProposalA(): Unit = {
    MapMergeProposalA.mergeMaps(maps_prop_A)
  }

  @Benchmark
  def testProposalB(): Unit = {
    // Prop B relies on the existing input data without any modification
    MapMergeProposalB.mergeMaps(maps_reference)
  }
}



