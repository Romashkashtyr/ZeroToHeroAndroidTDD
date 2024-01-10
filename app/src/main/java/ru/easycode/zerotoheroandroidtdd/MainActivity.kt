package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var uiState: UiState = UiState.Base("0")
    private val count = Count.Base(2,4)

    private lateinit var textView: TextView
    private lateinit var button: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)

        button.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(textView, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if(Build.VERSION.SDK_INT >= TIRAMISU)
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        else savedInstanceState.getSerializable(KEY) as UiState
        uiState.apply(textView,button)
    }


    companion object{
        private const val KEY = "uiStateKey"
    }
}