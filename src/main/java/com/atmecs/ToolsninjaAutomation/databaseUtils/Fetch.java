package com.atmecs.ToolsninjaAutomation.databaseUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class fetch the data from the database*/
public class Fetch {
	ResultSet rs = null;
	Statement stmt = null;
	Connection con = null;

	public Fetch(String databaseName) throws SQLException {
		con = new LoadDriver().loadingDriver(databaseName);
		stmt = con.createStatement();
	}

	/**
	 * Method fetch the data by the given parameters
	 * 
	 * @param tableName
	 * @param id
	 * @param columnName
	 * 
	 * @return specific cell data
	 * @throws SQLException
	 */
	public String fetchData(String tableName, int id, String columnName) throws SQLException {

		try {
			System.out.println("Id of the Products is " + id);

			System.out.println("select " + columnName + " from " + tableName + " where "
					+ getPrimaryKeyColumnName(tableName) + " = " + id);

			rs = stmt.executeQuery("select " + columnName + " from " + tableName + " where "
					+ getPrimaryKeyColumnName(tableName) + " = " + id);

			while (rs.next()) {
				System.out.println(rs.getString(1));
				return rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("Sorry! wrong Input");
			e.printStackTrace();
		}
		return rs.getString(1);
	}

	/**
	 * Method get the row count
	 * 
	 * @param tableName
	 * @return rowCount
	 * @throws SQLException
	 */
	public int getRowCount(String tableName) throws SQLException {
		String rows = null;
		try {
			if (stmt == null || con == null) {
				System.out.println("Null value found for statement or connection");
			}
			rs = stmt.executeQuery("SELECT count(*) from " + tableName + ";");
			while (rs.next()) {
				rows = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception ");
			e.printStackTrace();
		}
		System.out.println(rows);
		int rowCount = Integer.parseInt(rows);
		return rowCount;
	}

	/**
	 * Method gets the primary key column name to get the unique id
	 * 
	 * @param tableName
	 * 
	 * @return primaryKeyColumnName
	 * @throws SQLException
	 */
	public String getPrimaryKeyColumnName(String tableName) throws SQLException {
		String primaryKeyColumnName = null;
		DatabaseMetaData meta = con.getMetaData();

		// The Oracle database stores its table names as Upper-Case,
		// if you pass a table name in lowercase characters, it will not work.
		// MySQL database does not care if table name is uppercase/lowercase.

		rs = meta.getPrimaryKeys(null, null, tableName);
		while (rs.next()) {
			primaryKeyColumnName = rs.getString("COLUMN_NAME");
			System.out.println("getPrimaryKeys(): columnName=" + primaryKeyColumnName);
		}
		return primaryKeyColumnName;
	}
}
