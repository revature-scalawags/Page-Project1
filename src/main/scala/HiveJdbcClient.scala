import java.sql.{Connection, DriverManager, ResultSet, Statement}

case class HiveJdbcClient(connection: Connection) {

  def getQueryString(question: Int): String = question match {
    case 1 =>
      """
        |SELECT m.movieId, count(m.movieId) AS popularity, title
        |FROM movies m
        |JOIN ratings USING (movieId)
        |GROUP BY m.movieId, title
        |ORDER BY popularity
        |""".stripMargin
    case 2 =>
      """
        |SELECT count(r.movieId) AS popularity, rating, title
        |FROM ratings r
        |JOIN movies m ON r.movieId = m.movieId
        |GROUP BY rating, title
        |HAVING rating < 3.5
        |ORDER BY popularity
        |""".stripMargin
    case 3 =>
      """
        |SELECT count(r.movieId) AS popularity, rating, title
        |FROM ratings r
        |JOIN movies m ON r.movieId = m.movieId
        |GROUP BY rating, title
        |HAVING rating > 4 AND popularity > 5
        |ORDER BY popularity
        |DESC
        |""".stripMargin
    case 4 =>
      """
        |SELECT relevance, title, tag, rs.movieId
        |FROM relevance_scores rs
        |JOIN tags t ON rs.movieId = t.movieId
        |JOIN movies m ON t.movieId = m.movieId
        |GROUP BY rs.movieId, title, tag, relevance
        |ORDER BY relevance
        |""".stripMargin
    case _ => {
      connection.close()
      "close"
    }
  }

  def queryHive(sql: String): ResultSet = {
    val stmt: Statement = connection.createStatement()
    val res: ResultSet = stmt.executeQuery(sql)

    res
  }

  def closeHive = connection.close()
}


object HiveJdbcClient {
  private final val connectionString = "jdbc:hive2://localhost:10000/default"
  private final val driver = "org.apache.hive.jdbc.HiveDriver"
  private final val tableName = "ratings"
  var connection: Connection = _

  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(connectionString, "", "")
  }
  catch {
    case _: ClassNotFoundException => println("Failed to load the Hive driver.")
    case e: Exception => e.printStackTrace()
  }

  def apply(): HiveJdbcClient = new HiveJdbcClient(connection)
}
