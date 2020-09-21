package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

// MainActivity extends AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_main_look_busy.setVisibility(View.GONE)
        text_view_main_look_busy.setVisibility(View.GONE)

        button_main_look_busy.setOnClickListener {
            progress_main_look_busy.setVisibility(View.VISIBLE)
            button_main_self_destruct.setVisibility(View.INVISIBLE)
            switch_main_useless.setVisibility(View.INVISIBLE)
            text_view_main_look_busy.setVisibility(View.VISIBLE)
            button_main_look_busy.setVisibility(View.INVISIBLE)

            val uncheckTimer = object: CountDownTimer(10000, 100) {
                private var progress = 1

                override fun onFinish() {
                    progress_main_look_busy.setVisibility(View.GONE)
                    text_view_main_look_busy.setVisibility(View.GONE)
                    button_main_self_destruct.setVisibility(View.VISIBLE)
                    switch_main_useless.setVisibility(View.VISIBLE)
                    button_main_look_busy.setVisibility(View.VISIBLE)
                }

                override fun onTick(millisRemaining: Long) {
                    progress_main_look_busy.setProgress(progress)
                    text_view_main_look_busy.setText("Loading files $progress/100")
                    progress += 1
                }
            }
            uncheckTimer.start()
        }

        // compoundButton and isChecked --> parameters the lambda class is using
        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                val uncheckTimer = object: CountDownTimer(10000, 1000) {
                    override fun onFinish() {
                        switch_main_useless.toggle()
                    }

                    override fun onTick(millisRemaining: Long) {
                        // HAVE TO USE !
                        // Want it to switch off if switch is pressed AGAIN
                        if(!switch_main_useless.isChecked) {
                            cancel()
                        }
                    }
                }
                uncheckTimer.start()
            }
        }

        button_main_self_destruct.setOnClickListener {
            button_main_self_destruct.isClickable = false
            button_main_self_destruct.isEnabled = false

            // parameter numbers have to be LONG type
            val uncheckTimer = object: CountDownTimer((10000).toLong(), 250) {
                private var isRed = false
                private var lastTime = 10000L // last time it flashed
                private var duration = 500 // time between flashes
                var rate = 0

                override fun onFinish() {
                    finish()
                }

                override fun onTick(millisRemaining: Long) {
                    button_main_self_destruct.text = ((millisRemaining / 1000.0).toInt()).toString()
                    rate++
                    if (millisRemaining > 3000 && rate % 3 == 0) {
                        if (isRed) {
                            layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                            isRed = !isRed
                        }
                        else {
                            layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                            isRed = !isRed
                        }
                    }

                    else if (millisRemaining <= 3500) {
                        if (isRed) {
                            layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                            isRed = !isRed
                        }
                        else {
                            layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                            isRed = !isRed
                        }
                    }
                }
            }
            uncheckTimer.start()
        }
    }
}
