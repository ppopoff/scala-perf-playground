package playground.rle

/**
 * Functional implementation
 */
object RLE {
  final def runLengthEncode(in: String): String = {
    def format: (Option[Char], Int) => String = {
      case (None, _) => ""
      case (Some(char), 1) => "" + char
      case (Some(char), count) => "" + char + count.toString
    }

    val (lastCharOpt, lastCharCount, res) = in.toCharArray
      .foldLeft((None: Option[Char], 0, "")) {
        case ((oc@Some(prev), cnt, res), curr) if prev == curr =>
          (oc, cnt + 1, res)
        case ((oc@Some(prev), cnt, res), curr) =>
          (Some(curr), 1, res + format(oc, cnt))
        case ((None, _, _), curr) =>
          (Some(curr), 1, "")
      }
    res + format(lastCharOpt, lastCharCount)
  }
}

/**
 * Mutable implementation
 */
object MutableRle {
  private val CharNull: Char = Char.MinValue

  def runLengthEncode(in: String): String =
    if (in.isEmpty)                 ""
    else if (in.length == 1)        in
    else regularLengthEncode(in)

  @inline private final
  def regularLengthEncode(in: String): String = {
    var currChar: Char = CharNull
    var nextChar: Char = CharNull

    val buff = in.toCharArray.toBuffer
    val lastCountableIndex = in.toCharArray.length - 1
    var currentBuffIndex = 0

    var lastCharCount: Int = 1
    val resultingString = new StringBuilder()

    while (currentBuffIndex < lastCountableIndex) {
      currChar = buff(currentBuffIndex)
      nextChar = buff(currentBuffIndex + 1)

      if (currChar == nextChar) // duplicate case
        lastCharCount += 1
      else {
        if (lastCharCount == 1)
          resultingString.append(currChar)
        else
          resultingString.append(currChar).append(lastCharCount.toString)

        // reset char count
        lastCharCount = 1
      }

      // dump last character if there's a case for it
      if (currentBuffIndex == lastCountableIndex-1) {
        resultingString.append(nextChar)
        if (lastCharCount != 1)
          resultingString.append(lastCharCount)
      }

      // inc the counter
      currentBuffIndex += 1
    }

    resultingString.toString
  }
}
