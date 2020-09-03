package com.example.uselessmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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

                switch_main_useless.isChecked = false
                // could also do compoundButton.toggle()
                // could lead to spam click problems
                // a thousand monkeys --> thousands of clicks all over your app to see if it breaks it
            }
        }

        // look up the CountDownTimer API and see what is needed to implement your own custom timer
        // How do you start it? How do you stop it? How do you do things when it's done?
        // How do you do things while counting down?

    }
}
