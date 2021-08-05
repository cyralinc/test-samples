# java-setconfig-sidecar-issue
This is a Java application to test a specific reported issue.

## Configure properties
To configure this project, create a config file named "config.properties" in the `src/resources` folder and put the database credentials in the following format:

```
#Database Config
url=urlDB
user=userDB
password=passwordDB
```

Where `urlDB` corresponds to the JDBC URL, and `user` and `password` are the credentials necessary to connect to the database.

## Run application
To run this application use the Java JDK version 11.