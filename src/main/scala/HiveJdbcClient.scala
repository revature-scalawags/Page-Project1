import java.sql.{Connection, DriverManager, Statement}

case class HiveJdbcClient() {
  private final val connectionString = "jdbc:hive2://localhost:10000/default"
  private final val driver = "org.apache.hive.jdbc.HiveDriver"
  private final val tableName = "ratings"
  var connection: Connection =_

  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(connectionString, "", "")
  }
  catch {
    case _: ClassNotFoundException => println("Failed to load the Hive driver.")
    case e: Exception => e.printStackTrace()
  }

  val stmt: Statement = connection.createStatement()
  val res = stmt.executeQuery(s"SELECT * FROM $tableName WHERE userId < 50")

  while (res.next()) {
    println(res.getString("userId"))
  }
  connection.close()
}