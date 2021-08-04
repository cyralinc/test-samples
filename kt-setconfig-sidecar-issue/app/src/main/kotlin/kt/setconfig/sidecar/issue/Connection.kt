package kt.setconfig.sidecar.issue

import java.io.Closeable
import java.sql.DriverManager
import java.util.Properties

class Connection(private val coreConnection: java.sql.Connection) :
        java.sql.Connection by coreConnection, Closeable {

    companion object {
        fun connect(): Connection {
            val properties = Properties()
            val propertiesFile = Connection::class.java.getResourceAsStream("/config.properties")
            properties.load(propertiesFile)

            val url = properties.getProperty("url")
            val user = properties.getProperty("user")
            val password = properties.getProperty("password")

            val c = DriverManager.getConnection(url, user, password)
            c.autoCommit = false
            return Connection(c)
        }
    }

    override fun close() {
        coreConnection.close()
    }
}
