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

        // When you see View with a capital V, what kind of Kotlin entity does that indicate? --> class
        // View.OnClickListener
        // a class inside of a class?
        // What else could be capital in Java? --> classes and interfaces
        // can you construct and Interface? --> no
        // val listener = View.OnClickListener() --> doesn't work
        // you can make a class that implements and interface
        // we could make a class that implements View.OnClickListener and defines how onClick(v: View!) behaves

        // Kotlin has a feature called a lambda which lets you make a function without a name that can actually stand in for
        // an interface with only one function in it

        // the one function that View.OnClickListener has is onClick(v: View!)
        // this lambda below is implementing that one function onClick without really mentioning it explicitly
        // the one parameter is referenced by "it". So to access that view, I can use "it"
        button_main_look_busy.setOnClickListener {
            Toast.makeText(this, "Hello, this is the text on the button ${(it as Button).text.toString()}", Toast.LENGTH_SHORT).show()
            // ${} is the same as + concatenation + in java
            // it as Button --> casting the variable it into a Button
            // the it:View isn't typed, it's a tooltip that tells you that it's available
            // when you hit enter between {} moving them to different lines, the tooltip showed up
        }

    }
}
