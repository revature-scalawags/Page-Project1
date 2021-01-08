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
  }) ()

  val sql = db.getQueryString(question)

  if (sql == "close") {
    println("\nHave a nice day.")
    System.exit(-1)
  }
  val dataResultSet = db.queryHive(sql)

  ui.printResults
}