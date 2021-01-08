import java.io.{BufferedWriter, File, FileWriter}
import java.nio.file.{Files, Paths}

object MovieAns extends App {
  val file = new File("log.txt")
  val hasLogFile: Boolean = Files.exists(Paths.get("log.txt"))
  val bw = new BufferedWriter(new FileWriter(file, hasLogFile))

  val db = HiveJdbcClient()
  val ui = UI()
  var question = -1

  ui.welcomeMessage
  while ( {
    val (res, isValid) = ui.promptUsrQuestion
    question = res
    !isValid
  })()

  val sql = db.getQueryString(question)
  val dataResultSet = db.queryHive(sql)

while (dataResultSet.next()) {
  println(dataResultSet.getString("title"),  dataResultSet.getString("tag"), dataResultSet.getLong("relevance"))
}
}