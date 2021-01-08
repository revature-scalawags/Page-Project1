
import scala.Console.{BLUE => bu, CYAN => cy, GREEN => gr, MAGENTA => mg, RED => rd, RESET => rt, YELLOW => yl}
import java.io.BufferedWriter
import scala.io.StdIn.{readInt, readLine}
import scala.util.control.Breaks._

case class UI() {
  def welcomeMessage: Unit = {
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

  def logger(message: String, bw: BufferedWriter): Unit = {
    val now = getTimeStamp
    try {
      bw.newLine()
      bw.write(now + ": " + message)
      bw.newLine()
    } catch {
      case _: Throwable => println(s"$rt${rd}Logging Failure$rt")
    }
    println(message)
  }

  def getTimeStamp: String = java.time.LocalDateTime.now().toString

  def promptUsrQuestion: (Int, Boolean) = {
    println(s"\nChoose a question for the answers you seek: \n")
    println(s"${gr}1$rt.) What are the most popular movies ever?")
    println(s"${gr}2$rt.) What are the worst popular movies?")
    println(s"${gr}3$rt.) What are some good however, unpopular movies?")
    println(s"${gr}4$rt.) What movies correlate closely to tag descriptions?")
    println(s"${gr}5$rt.) Quit.\n")
    print("Select a Number: ")

    var input: Int = -1
    try {
      breakable(
        while (true) {
          input = readInt()
          if (input >= 1 && input <= 5) {
            break
          }
          print(s"Please select a valid choice from ${rd}1$rt to ${rd}5$rt: ")
        }
      )
    } catch {
      case _: NumberFormatException | _: ArrayIndexOutOfBoundsException =>
        println(s"$rd\nYou MUST select an integer value between 1 and 5$rt.")
        return (-1, false)
    }
    (input, true)
  }
}
