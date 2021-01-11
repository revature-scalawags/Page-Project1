object MovieAns extends App {
  val db = HiveJdbcClient()
  val ui = UI()
  var question = -1

  ui.welcomeMessage
  while ( {
    val (res, isValid) = ui.promptUsrQuestion
    question = res
    !isValid
  }) ()

  ui.logger("\nQuerying. Please stand by...")

  val sql = db.getQueryString(question)

  if (sql == "close") {
    println("\nHave a nice day.")
    ui.logger("user exit")
    db.closeHive()
    System.exit(-1)
  }
  val dataResultSet = db.queryHive(sql)

  ui.printResults(dataResultSet, question)
}