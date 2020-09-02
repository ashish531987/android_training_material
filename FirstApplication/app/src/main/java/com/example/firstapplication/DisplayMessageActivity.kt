package com.example.firstapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val textView = findViewById<TextView>(R.id.message_tv)
        val message = intent.getStringExtra(MainActivityKotlin.MESSAGE_ID)
        textView.text = message
    }
}