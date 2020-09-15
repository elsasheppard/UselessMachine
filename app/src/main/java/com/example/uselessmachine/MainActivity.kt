package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

// MainActivity extends AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View.OnClickListener
        // lambda --> anonymous function
        // you can use a lambda to implement a one-function interface
        // onClick(view: View) is the function being implemented by the lambda

        // when there is one parameter in the function, "it" is used to refer to that parameter
        // ${} is a string concatenation template

        main_progress_look_busy.setVisibility(View.GONE)

        button_main_look_busy.setOnClickListener {
            Toast.makeText(this, "Hello, this is the text on the button ${(it as Button).text.toString()}", Toast.LENGTH_SHORT).show()
            main_progress_look_busy.setVisibility(View.VISIBLE)
        }

        // to listen to a switch, you can use the OnCheckedChangeListener
        // rely on documentation a lot in this class


        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {

                val uncheckTimer = object: CountDownTimer(1000, 1000) {
                    // TODO
                    // 1. Switch
                        // a. randomize the time so it doesn't always turn off at a fixed interval
                        // b. if the switch is manually turned off early, we cancel the timer
                                // so if it gets turned back on we don't gave multiple timers running simultaneously
                    // 3. Add a progress bar that's hidden initially (visibility attribute)


                    override fun onFinish() {
                        switch_main_useless.toggle()
                    }

                    // p0 --> millisRemaining
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
