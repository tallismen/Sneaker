package com.irozon.sneakersample

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.irozon.sneaker.Sneaker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the click listener for the error button
        findViewById<View>(R.id.btShowError).setOnClickListener {
            Sneaker.with(this)
                .setTitle("Error!!")
                .setMessage("This is the error message")
                .setTypeface(Typeface.createFromAsset(this.assets, "font/Slabo27px-Regular.ttf"))
                .sneakError()
        }

        // Set up the click listener for the success button
        findViewById<View>(R.id.btShowSuccess).setOnClickListener {
            val sneaker = Sneaker.with(findViewById(R.id.viewGroup))
            val view = LayoutInflater.from(this).inflate(R.layout.custom_view, sneaker.getView(), false)
            view.findViewById<TextView>(R.id.tvInstall).setOnClickListener {
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            }
            sneaker.sneakCustom(view)
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        with(supportFragmentManager.beginTransaction()) {
            this.add(R.id.fragment, MainFragment())
            this.commit()
        }
    }
}