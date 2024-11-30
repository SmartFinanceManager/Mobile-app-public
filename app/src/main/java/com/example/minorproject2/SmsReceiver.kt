package com.example.minorproject2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle: Bundle? = intent.extras
        try {
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<*>
                for (pdu in pdus) {
                    val message = SmsMessage.createFromPdu(pdu as ByteArray)
                    val sender:String = message.displayOriginatingAddress
                    val array:List<String> = sender.split("-")
                    var messageBody = message.messageBody
                    Log.d("Sms", "Sender: $sender, Message: $messageBody")
                    messageBody=messageBody.replace("\\r?\\n".toRegex(), " ")
                    Log.d("SmsReceiver", "Sender: $sender, Message: $messageBody")
                    if(array.last().lowercase() == "axisbk")
                    {

                        Log.d("BankSmsReceiver", "Sender: $sender, Message: $messageBody")
                        MySQLAsyncTask().execute(messageBody, array.last().uppercase())
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("SmsReceiver", "Error in receiving message", e)
        }
    }
}
