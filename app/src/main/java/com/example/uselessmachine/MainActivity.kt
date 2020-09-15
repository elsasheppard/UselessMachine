package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
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

        button_main_look_busy.setOnClickListener {
            Toast.makeText(this, "Hello, this is the text on the button ${(it as Button).text.toString()}", Toast.LENGTH_SHORT).show()

        }

        // to listen to a switch, you can use the OnCheckedChangeListener
        // rely on documentation a lot in this class


        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            val message = if(isChecked) "Switch is ON" else "Switch is OFF"

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            if(isChecked) {

                val uncheckTimer = object: CountDownTimer(5000, 1000) {

                    // use for changing bg color
                    private var isRed = false
                    private var difference = 1L // L makes it a long var type instead of int

                    // TODO
                    // 1. Switch
                        // a. randomize the time so it doesn't always turn off at a fixed interval
                        // b. if the switch is manually turned off early, we cancel the timer
                                // so if it gets turned back on we don't gave multiple timers running simultaneously
                    // 3. Add a progress bar that's hidden initially (visibility attribute)


                    override fun onFinish() {
                        // change ConstraintLayout name to layout_main
                        layout_main.setBackgroundColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
                        switch_main_useless.isChecked = false
                        // when done, turn off
                    }

                    // p0 --> millisRemaining
                    override fun onTick(millisRemaining: Long) {
                        if(switch_main_useless.isChecked) {
                            cancel()
                        }
                        if(isRed) {
                            layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                        }
                        isRed = !isRed
                    }
                }
                uncheckTimer.start()
            }
        }

        button_main_self_destruct.setOnClickListener {
            // 2. Self-Destruct Button
            // a. 10 second countdown timer to display on the button
            // b. when the timer is up, call the finish() method to close activity
            // c. lock out the button by setting its enabled attribute to false
            // make the button no longer clickable
            // d. get the screen to flash a different color at every instance of an interval
            // e. get the screen to flash faster the less time is remaining in the countdown
            button_main_self_destruct.isClickable = false

            val uncheckTimer = object: CountDownTimer(5000, 500) {

                // use for changing bg color
                private var isRed = false
                private var count = 10

                override fun onFinish() {
                    finish()
                }

                // p0 --> millisRemaining
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
