package me.jonathan.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.jonathan.Gen;

public class Database {
    /**
     * the database types
     */
    public enum DBType {MySQL, SQLite}

    /**
     * SQLite Data
     * Set this data if you use DBType#SQLite
     * <p>
     * field filePath - This can either be a relative or absolute path.
     * ex: sokobot.db
     * or: C:/sqlite/db/sokobot.db
     */
    private final String filePath = "DONT-TOUCH-THIS/database.db";

    /**
     * MySQL Data
     * Set this data if you use DBType#MySQL
     */
    private final String mysql_hostname = "";
    /**
     * MySQL Port
     * Set this data if you use DBType#MySQL
     */
    private final int mysql_port = 3306;
    /**
     * MySQL database
     * Set this data if you use DBType#MySQL
     */
    private final String mysql_database = "";
    /**
     * MySQL username
     * Set this data if you use DBType#MySQL
     */
    private final String mysql_username = "";
    /**
     * MySQL password
     * Set this data if you use DBType#MySQL
     */
    private final String mysql_password = "";
    /**
     * the connection
     */
    public Connection con = null;
    /**
     * the database type
     *
     * @apiNote the dbType set in Bot.java
     */
    public Database(DBType dbType) {
        try {
            if (dbType == DBType.MySQL) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + mysql_hostname + ":" + mysql_port + "/" + mysql_database
                                + "?autoReconnect=true", mysql_username, mysql_password);
                Gen.getInstance().getLogger().info("[INFO] Successfully initialized database connection.");
            } else if (dbType == DBType.SQLite) {
                File sqliteFile = new File(Gen.getInstance().getDataFolder(), filePath);
                if (!sqliteFile.exists()) {
                	Gen.getInstance().getLogger().info("[INFO] SQLite file \"" + filePath + "\" not found, creating file...");
                    sqliteFile.getParentFile().mkdirs();
                    Gen.getInstance().saveResource(filePath, false);
                }
                con = DriverManager.getConnection("jdbc:sqlite:" + Gen.getInstance().getDataFolder() + "/" + filePath);
                Gen.getInstance().getLogger().info("[INFO] Successfully initialized database connection.");
            }
        } catch (Exception ex) {
        	Gen.getInstance().getLogger().severe("[ERROR] Error at creating database connection: " + ex.getMessage());
        }
    }
    /**
     * to disconnect from the current database
     */
    public void disconnect() {
        try {
            con.clearWarnings();
            con.close();
            con = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets the connection from
     */
    public Connection getCon() {
        return con;
    }
    /**
     * the resultset
     *
     * @param sql the string
     * @param preparedParameters the object
     *
     * @return the query or null
     */
    public ResultSet query(String sql, Object... preparedParameters) {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            int id = 1;
            for (Object preparedParameter : preparedParameters) {
                ps.setObject(id, preparedParameter);
                id++;
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * the resultset
     *
     * @param sql the string
     *
     * @return sql or null
     */
    public ResultSet query(String sql) {
        try {
            return con.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * the resultset
     *
     * @param sql the string
     * @param preparedParameters the object
     *
     */
    public void update(String sql, Object... preparedParameters) {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int id = 1;
            for (Object preparedParameter : preparedParameters) {
                ps.setObject(id, preparedParameter);
                id++;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * the resultset
     *
     * @param sql the string
     *
     */
    public void update(String sql) {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * if the database is connected
     */
    public boolean isConnected() {
        return con != null;
    }
}