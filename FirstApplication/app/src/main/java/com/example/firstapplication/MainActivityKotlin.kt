package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivityKotlin : AppCompatActivity(){

    companion object{
        const val MESSAGE_ID = "com.example.firstapplication.MESSAGE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivitykotlin_layout)
    }

    fun sendClicked(v: View){
        val messageEditText = findViewById<EditText>(R.id.message_et)
        val message : String = messageEditText.text.toString()

        val intent = Intent(this, DisplayMessageActivity::class.java)

        intent.putExtra(MESSAGE_ID, message)

        startActivity(intent)
    }
}