package com.example.simplesharingdemo

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_share.*

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        if(intent.type.equals("text/plain")){
            textView.text = intent.getStringExtra(Intent.EXTRA_TEXT)
        } else if (intent.type?.startsWith("image")!!){
            val parcelableExtra = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri
            ( parcelableExtra)?.let {
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}