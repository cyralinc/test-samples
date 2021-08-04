package kt.setconfig.sidecar.issue

fun main() {
  try {
    val connection = Connection.connect()
    connection.use { tx ->
      val statement =
          tx.prepareStatement(
              """select
                    set_config('request.header.uiid', ?, true),
                    set_config('request.header.user-agent', ?, true),
                    set_config('request.header.X-Forwarded-For', ?, true),
                    set_config('request.header.X-Path', ?, true),
                    set_config('request.header.X-Request-Id', ?, true),
                    set_config('request.header.X-Verb', ?, true),
                    set_config('request.jwt.claim.role', ?, true),
                    set_config('request.jwt.claim.id', ?, true),
                    set_config('request.jwt.claim.exp', ?, true)
                """
          )

      statement.setString(1, "parameterA")
      statement.setString(2, "parameterB")
      statement.setString(3, "parameterC")
      statement.setString(4, "parameterD")
      statement.setString(5, "parameterE")
      statement.setString(6, "parameterF")
      statement.setString(7, "parameterG")
      statement.setString(8, "parameterH")
      statement.setString(9, "parameterI")

      val result = statement.executeQuery()

      result.next()
      for (i in 1..9) {
        println(result.getString(i))
      }

      result.close()
      statement.close()
    }
    connection.close()
  } catch (e: Exception) {
    println(e)
  }
}
