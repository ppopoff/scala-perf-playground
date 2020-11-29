package mapmerge

import scala.collection.mutable

/**
 * https://scastie.scala-lang.org/g19IJ0AxQIafuFOb77O1Tg
 */
object MapMergeReference {
  import scala.collection.immutable.HashMap

  def mergeMaps(maps: List[HashMap[String, Array[Int]]]) {
    maps.reduce((left, right) => left.merged(right)((l, r) => l._1 -> (l._2 ++ r._2).distinct))
  }
}

/**
 * https://scastie.scala-lang.org/Odomontois/Vl3WPQjGQx68oGJidMnbbw/2
 */
object MapMergeProposalA {
  def mergeMaps(maps: List[Map[String, Array[Int]]]) {
    val items = for {
      m <- maps.iterator
      (k, a) <- m.iterator
      v <- a.iterator
    } yield k -> v

    items.distinct.toArray.groupMap(_._1)(_._2)
  }
}

/**
 * https://scastie.scala-lang.org/lQIuwMDcSwSXWtAXugBIYA
 */
object MapMergeProposalB {
  def mergeMaps(maps: List[Map[String, Array[Int]]]): mutable.HashMap[String, mutable.HashSet[Int]] = {
    val mm = new mutable.HashMap[String, mutable.HashSet[Int]]

    val mapsIterator = maps.iterator
    while (mapsIterator.hasNext) {
      val listIt = mapsIterator.next().iterator
      while (listIt.hasNext) {
        val item = listIt.next
        val key  = item._1
        val values = item._2
        val arrayItems = values.iterator
        while (arrayItems.hasNext) {
          // pm-impl
          val value = arrayItems.next
          mm.get(key) match {
            case None =>
              val set = new mutable.HashSet[Int]()
              set += value
              mm(key) = set
            case Some(set) =>
              set += value
          }
        }
      }
    }

    mm
  }

//  def lessIneffectiveButMoreElegantMergeMaps(maps: List[Map[String, Array[Int]]]) = {
//    val mm = new mutable.HashMap[String, mutable.Set[Int]] with mutable.MultiMap[String, Int]
//    for (map <- maps; (key, values) <- map; value <- values) {
//      mm.addBinding(key, value)
//    }
//    mm
//  }
}