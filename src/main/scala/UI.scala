
import java.io.{BufferedWriter, File, FileWriter}
import java.nio.file.{Files, Paths}
import java.sql.ResultSet

import scala.Console.{BLUE => bu, CYAN => cy, GREEN => gr, MAGENTA => mg, RED => rd, RESET => rt}
import scala.io.StdIn.readInt
import scala.util.control.Breaks._

case class UI(bw: BufferedWriter) {
  def welcomeMessage(): Unit = {
    println(
      s"""
        ##########################################################################################################
        #--------------------------------------------${cy}Welcome Movie Answers$rt---------------------------------------#
        #      Your program to query a Hadoop cluster using Hive to answer any one of ${mg}four$rt great                 #
        $bu#$rt                                          questions about movies..                                      $bu#
        #                                                                                                        #
        ##########################################################################################################
    """.stripMargin
    )
  }

  def promptUsrQuestion: (Int, Boolean) = {
    println(s"\nChoose a question for the answers you seek: \n")
    println(s"${gr}1$rt.) What are the most popular movies ever?")
    println(s"${gr}2$rt.) What are the worst popular movies?")
    println(s"${gr}3$rt.) What are some good however, unpopular movies?")
    println(s"${gr}4$rt.) What movies correlate closely to their tag descriptions?")
    println(s"${gr}5$rt.) Quit.\n")
    print("Select a Number: ")

    var input: Int = -1
    try {
      breakable(
        while (true) {
          input = readInt()
          if (input >= 1 && input <= 5) {
            this.logger(s"question $input selected")
            break
          }
          print(s"Please select a valid choice from ${rd}1$rt to ${rd}5$rt: ")
        }
      )
    } catch {
      case e: NumberFormatException | _: ArrayIndexOutOfBoundsException =>
        println(s"$rd\nYou MUST select an integer value between 1 and 5$rt.")
        logger(e.getMessage)
        return (-1, false)
    }
    (input, true)
  }

  def printResults(rs: ResultSet, question: Int): Unit = question match {
    case 1 =>
      while (rs.next())
        println(s"Title: ${rs.getString("title")}",
          s"Popularity: ${rs.getString("popularity")}")
    case 2 | 3 =>
      while (rs.next())
        println(s"Title: ${rs.getString("title")}",
          s"Rating: ${rs.getString("popularity")}")
    case 4 =>
      while (rs.next()) {
        println(s"Title: ${rs.getString("title")}",
          s"Tag: ${rs.getString("popularity")}",
          s"Relevance: ${rs.getString("popularity")}")
      }
  }

  def logger(message: String): Unit = {
    val now = getTimeStamp
    try {
      bw.newLine()
      bw.write(now + ": " + message)
      bw.newLine()
    } catch {
      case _: Throwable => println(s"$rt${rd}Logging Failure$rt")
    }
    if (message == "user exit") {
      bw.close()
    }
    println(message)
  }

  def getTimeStamp: String = java.time.LocalDateTime.now().toString
}

object UI {
  def apply(): UI = {
    val file = new File("log.txt")
    val hasLogFile: Boolean = Files.exists(Paths.get("log.txt"))
    val bw = new BufferedWriter(new FileWriter(file, hasLogFile))

    new UI(bw)
  }
}