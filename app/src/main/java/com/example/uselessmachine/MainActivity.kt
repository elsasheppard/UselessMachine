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
            // 1. toast the status of the button (checked, or not checked)

            // can assign the result of an if statement as a val
            // only in Kotlin
            val message = if(isChecked) "Switch is ON" else "Switch is OFF"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            // could also do:
            // Toast.makeText(this, "The button is: ${isChecked.toString()}", Toast.LENGTH_SHORT).show()

            // 2. if the button is checked, uncheck it
            if(isChecked) {
                // ideally, we wait a little bit of time, and then have this code execute
                // but Thread.sleep is illegal on the main.UI thread
                // every 15 milliseconds the android screen refreshes

                // janky app --> when it doesn't really respond to your clicks and taps immediately
                // if you do a Thread.sleep without specifying which thread, it assumes UI thread, and stops EVERYTHING on the app
                // people would think their phone is freezing and bad
                // have to keep the UI running smoothly

                // CountDownTimer is effectively making a separate thread to keep track of the time
                // we're going to make an anon inner class using CountDownTimer
                // anon --> we're not naming the class... just implementing the methods
                // it's a one-time use thing used right here

                // class w/o a name --> anonymous inner class

                // making an anon inner class saying this object extends CountDownTimer
                val uncheckTimer = object: CountDownTimer(5000, 1000) {
                    // red underline under object
                        // implement members
                    // members --> things that belong to a class

                    // use for changing bg color
                    private var currentColor = 0
                    private var difference = 1L // L makes it a long var type instead of int

                    // TODO
                    // 1. Switch
                        // a. randomize the time so it doesn't always turn off at a fixed interval
                        // b. if the switch is manually turned off early, we cancel the timer
                                // so if it gets turned back on we don't gave multiple timers running simultaneously
                    // 2. Self-Destruct Button
                        // a. 10 second countdown timer to display on the button
                        // b. when the timer is up, call the finish() method to close activity
                        // c. lock out the button by setting its wnabled attribute to false
                            // make the button no longer clickable
                        // d. get the screen to flash a different color at every instance of an interval
                        // e. get the screen to flash faster the less time is remaining in the countdown


                    override fun onFinish() {
                        // change ConstraintLayout name to layout_main
                        layout_main.setBackgroundColor(Color.rgb((0...255).random(), (0...255).random(), (0...255).random()))

                        switch_main_useless.isChecked = false
                        // when done, turn off
                    }

                    // p0 --> millisRemaining
                    override fun onTick(millisRemaining: Long) {
                        // you can have an empty function if you don't need to use it
                    }
                }
                uncheckTimer.start()
            }
        }
    }
}
