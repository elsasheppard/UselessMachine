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

        // to listen to a switch, you can use the OnCheckedChangeListener
        // rely on documentation a lot in this class


        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {

                val uncheckTimer = object: CountDownTimer(3500, 1000) {
                    // TODO
                    // 1. Switch
                        // a. randomize the time so it doesn't always turn off at a fixed interval
                        // b. if the switch is manually turned off early, we cancel the timer
                                // so if it gets turned back on we don't gave multiple timers running simultaneously

                    override fun onFinish() {
                        switch_main_useless.toggle()
                    }

                    override fun onTick(millisRemaining: Long) {
                        if(switch_main_useless.isChecked) {
                            cancel()
                        }
                    }
                }
                uncheckTimer.start()
            }
        }

        button_main_self_destruct.setOnClickListener {
            // 2. Self-Destruct Button

            // TODO e. get the screen to flash faster the less time is remaining in the countdown
            button_main_self_destruct.isClickable = false
            button_main_self_destruct.isEnabled = false

            val uncheckTimer = object: CountDownTimer(5000, 500) {
                // use for changing bg color
                private var isRed = false
                private var count = 10

                override fun onFinish() {
                    finish()
                }

                override fun onTick(millisRemaining: Long) {
                    if(isRed) {
                        layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                    }
                    else {
                        layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                    }
                    isRed = !isRed
                    button_main_self_destruct.setText(count.toString())
                    count--
                }
            }
            uncheckTimer.start()
        }
    }
}
