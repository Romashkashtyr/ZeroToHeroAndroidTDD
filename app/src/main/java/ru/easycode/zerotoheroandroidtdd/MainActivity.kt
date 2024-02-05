package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE

            java.util.Timer().schedule(object : TimerTask() {
                override fun run() = runOnUiThread {
                    textView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    button.isEnabled = true
                }

            }, 3000)


        }
    }
}