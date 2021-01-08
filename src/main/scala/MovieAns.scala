import java.io.{BufferedWriter, File, FileWriter}
import java.nio.file.{Files, Paths}

object MovieAns extends App {
  val file = new File("log.txt")
  val hasLogFile: Boolean = Files.exists(Paths.get("log.txt"))
  val bw = new BufferedWriter(new FileWriter(file, hasLogFile))

  val ui = UI()
  var question: Int = -1

  ui.welcomeMessage
  while ( {
    val (res, isValid) = ui.promptUsrQuestion
    question = res
    !isValid
  })()

  match {

  }
}