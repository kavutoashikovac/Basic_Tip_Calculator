package com.example.basic_tip_calculator

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1_tip.R

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var tipPercentageSeekBar: SeekBar
    private lateinit var tipPercentageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Remove title bar and status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        tipPercentageSeekBar = findViewById(R.id.tipPercentageSeekBar)
        tipPercentageTextView = findViewById(R.id.tipPercentageTextView)

        val textView = findViewById<TextView>(R.id.blinkingTextView)
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        textView.startAnimation(anim)

        val spinner = findViewById<Spinner>(R.id.customSpinner)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateAndDisplayTip()
        }

        val warningButton = findViewById<Button>(R.id.warningButton)
        warningButton.setOnClickListener {
            Toast.makeText(this, "Waiter is coming", Toast.LENGTH_SHORT).show()
        }

        val showDialogButton = findViewById<Button>(R.id.showDialogButton)
        showDialogButton.setOnClickListener {
            showCustomDialog()
        }



        val dataTransferButton = findViewById<Button>(R.id.dataTransferButton)
        dataTransferButton.setOnClickListener {
            val intent = Intent(this, MyObject::class.java)
            val amount = amountEditText.text.toString().toDouble()
            val tipPercentage = tipPercentageSeekBar.progress

            intent.putExtra("amount", amount)
            intent.putExtra("tipPercentage", tipPercentage)

            startActivity(intent)
        }

        val myClassObject = MyObject("Hello, Object!")
        MyObject.staticMethod()
        myClassObject.memberMethod()

        val myList = mutableListOf(myClassObject)
        for (item in myList) {
            println("Logcat: ${item.message}")
        }

        tipPercentageSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tipPercentageTextView.text = "Tip Percentage: $progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing on start tracking touch
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing on stop tracking touch
            }
        })
    }

    private fun showCustomDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Custom Dialog")

        // Set up the input
        val inputEditText = EditText(this)
        inputEditText.hint = "Enter your text here"
        builder.setView(inputEditText)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, which ->
            val userInput = inputEditText.text.toString()
            // Do something with the user input
            Toast.makeText(this, "User input: $userInput", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        // Show the dialog
        val dialog = builder.create()
        dialog.show()

    }

    private fun calculateAndDisplayTip() {
        val amount = amountEditText.text.toString().toDouble()
        val tipPercentage = tipPercentageSeekBar.progress

        val tipAmount = (amount * tipPercentage) / 100
        val totalAmount = amount + tipAmount

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Tip Calculation Result")
        alertDialog.setMessage("Amount: $$amount\nTip Percentage: $tipPercentage%\nTip Amount: $$tipAmount\nTotal Amount: $$totalAmount")
        alertDialog.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }
}
