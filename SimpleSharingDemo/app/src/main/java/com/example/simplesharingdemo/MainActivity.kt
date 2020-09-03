package com.example.simplesharingdemo

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_share.*
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set bitmap from asset folder
        shareImageView.setImageBitmap( BitmapFactory.decodeStream(assets.open("2.jpeg")))

        // Share Text Button OnClickListener
        shareTextBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, editTextTextPersonName.text.toString())
            intent.putExtra(Intent.EXTRA_TITLE, getString(R.string.app_name))

            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)

        }

        // Share Image Button Listener
        shareImageBtn.setOnClickListener {
            val imageFolder = File(filesDir, "images")
            val imageFile = File(imageFolder, "2.jpeg")
            if(!imageFile.exists()){
                imageFolder.mkdirs()
                imageFile.writeBytes(assets.open("2.jpeg").readBytes())
            }
            val contentUri =
                FileProvider.getUriForFile(this, "com.example.helloworld.fileprovider", imageFile)
            val shareIntent = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/jpg"
                putExtra(Intent.EXTRA_STREAM, contentUri)
            }, "Share Image Using")
            val resolveInfo = packageManager.queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY )

            resolveInfo.stream().forEach {
                Log.d("MainActivity", it.activityInfo.packageName)
                grantUriPermission(it.activityInfo.packageName, contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            startActivity(shareIntent)
        }
    }
}