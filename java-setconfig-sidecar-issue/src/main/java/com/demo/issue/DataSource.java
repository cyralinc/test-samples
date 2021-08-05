package com.demo.issue;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;
  private static InputStream inputStream;

  static {
    try {
      Properties properties = new Properties();
      String propertiesFile = "config.properties";

      inputStream = DataSource.class.getClassLoader().getResourceAsStream(propertiesFile);

      if (inputStream != null) {
        properties.load(inputStream);
      } else {
        throw new FileNotFoundException("Property file '" + propertiesFile + "' not found in the classpath");
      }

      String url = properties.getProperty("url");
      String user = properties.getProperty("user");
      String password = properties.getProperty("password");

      config.setJdbcUrl(url);
      config.setUsername(user);
      config.setPassword(password);
      config.addDataSourceProperty("cachePrepStmts", "true");
      config.addDataSourceProperty("prepStmtCacheSize", "250");
      config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      ds = new HikariDataSource(config);

    } catch (Exception e) {
      System.out.println("Exception: " + e);
    }
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
