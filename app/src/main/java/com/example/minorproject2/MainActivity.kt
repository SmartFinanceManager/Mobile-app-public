package com.example.minorproject2

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val SMS_PERMISSION_CODE = 1

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECEIVE_SMS), SMS_PERMISSION_CODE)
        }
    }
}



/*
import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.Telephony
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_READ_SMS = 100


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanButton: Button = findViewById(R.id.scanButton)
        scanButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                scanAndSendMessages()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), PERMISSION_REQUEST_READ_SMS)
            }
            }
        }


    private fun scanAndSendMessages() {
        val contentResolver: ContentResolver = contentResolver
        var i=0
        val cursor: Cursor? = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, null, null, null, null)
        cursor?.let {
            while (it.moveToNext()) {
                val body = it.getString(it.getColumnIndexOrThrow(Telephony.Sms.BODY))
                body.lines().joinToString(" ")
                i+=1
                MySQLAsyncTask().execute(body)
                if(i==1)
                    break
            }
            it.close()
        }
    }
}

    private fun saveMessageToDatabase(message: String)
    {
            Thread {
                var connection: Connection? = null
                var preparedStatement: PreparedStatement? = null

                try {
                    Class.forName("com.mysql.jdbc.Driver")
                    connection = DriverManager.getConnection(jdbcUrl, username, password)

                    val sql = "INSERT INTO queue (message,read_status) VALUES (?)"
                    preparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setString(1, message)
                    preparedStatement.setBoolean(2, false)
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
            }

    }

     */



/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinorProject2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinorProject2Theme {
        Greeting("Android")
    }
}*/