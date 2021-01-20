import scala.annotation.tailrec

import java.sql.ResultSet
import au.com.bytecode.opencsv.CSVWriter
import java.io.FileWriter

class CSVHandler {

  def buildNewCSVTable(rs: ResultSet): Unit = {
    val csvWriter = new CSVWriter(new FileWriter("movie-results.csv"), ',')
    csvWriter.writeAll(rs, false)
  }

  @tailrec
  final def concatenateTailrec(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenateTailrec(aString, n-1, accumulator + aString)
}
