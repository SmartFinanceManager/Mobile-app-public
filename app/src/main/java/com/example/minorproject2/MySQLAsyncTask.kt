package com.example.minorproject2

import android.os.AsyncTask
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException


class MySQLAsyncTask: AsyncTask<String, Void, String>()
{
    private val jdbcUrl = "jdbc:mysql://minorproject2.cxcy0guqwtrx.eu-north-1.rds.amazonaws.com:3306/db1"
    private val username = "admin"
    private val password = "13A&23k#"

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String
    {
        val message = params[0] ?: ""
        val sender=params[1]?:""

        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null

        try {
            Class.forName("com.mysql.jdbc.Driver")
            connection = DriverManager.getConnection(jdbcUrl, username, password)

            val sql = "INSERT INTO queue (message,sender,read_status) VALUES (?,?,?)"
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, message)
            preparedStatement.setString(2, sender)
            preparedStatement.setBoolean(3,false)
            preparedStatement.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } finally {
            try {
                preparedStatement?.close()
                connection?.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        return "Executed Successfully"
    }

}