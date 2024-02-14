package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.basiccalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    // binding
    private lateinit var binding: ActivityMainBinding;

    var digit_on_screen = StringBuilder()
    var operation: Char = ' '

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeButtons()
        showResult()





        binding.buttonEqual.setOnClickListener {
            if (binding.evalText.text.isEmpty()) return@setOnClickListener;
            var expression = ExpressionBuilder(binding.evalText.text.toString()).build()
            var result = expression.evaluate();
            var longResult = result.toLong();

            if (result == longResult.toDouble()){
                binding.showAnsText.text = longResult.toString();
            } else {
                binding.showAnsText.text = result.toString();
            }
        }
    }

    private fun showResult(){
        var expression = ExpressionBuilder(binding.evalText.text.toString()).build()
        var result = expression.evaluate();
        var longResult = result.toLong();

        if (result == longResult.toDouble()){
            binding.showAnsText.text = longResult.toString();
        } else {
            binding.showAnsText.text = result.toString();
        }
    }

    private fun initializeButtons() {
        functionalButtons()
        operationalButtons()
        numericalButtons()
    }

    private fun numericalButtons(){
        // add numerical value to evaluate text
        binding.button0.setOnClickListener {
            appendToDigitOnScreen("0")
        }
        binding.button1.setOnClickListener {
            appendToDigitOnScreen("1")
        }
        binding.button2.setOnClickListener {
            appendToDigitOnScreen("2")
        }
        binding.button3.setOnClickListener {
            appendToDigitOnScreen("3")
        }
        binding.button4.setOnClickListener {
            appendToDigitOnScreen("4")
        }
        binding.button5.setOnClickListener {
            appendToDigitOnScreen("5")
        }
        binding.button6.setOnClickListener {
            appendToDigitOnScreen("6")
        }
        binding.button7.setOnClickListener {
            appendToDigitOnScreen("7")
        }
        binding.button8.setOnClickListener {
            appendToDigitOnScreen("8")
        }
        binding.button9.setOnClickListener {
            appendToDigitOnScreen("9")
        }
        binding.buttonDot.setOnClickListener {
            appendToDigitOnScreen(".")
        }
    }

    private fun appendToDigitOnScreen(digit: String) {

        // Add each digit to our string builder
        digit_on_screen.append(digit)
        // display it on the screen of our mobile app
        binding.evalText.text = digit_on_screen.toString()
    }

    private fun operationalButtons() {
        binding.buttonAdd.setOnClickListener {
            selectOperation('+')
        }
        binding.buttonMinus.setOnClickListener {
            selectOperation('-')
        }
        binding.buttonMultiply.setOnClickListener {
            selectOperation('*')
        }
        binding.divideBtn.setOnClickListener {
            selectOperation('/')
        }
    }

    private fun selectOperation(c: Char) {
        operation = c
        digit_on_screen.append("$operation")
        binding.evalText.text = digit_on_screen.toString();
    }

    private fun functionalButtons() {

        binding.acBtn.setOnClickListener {
            binding.evalText.text = "0"
            binding.showAnsText.text = ""
            digit_on_screen.clear()
        }
        binding.clearBtn.setOnClickListener {
            if (binding.evalText.text.isEmpty()) return@setOnClickListener;
            clearDigit()
        }

    }

    private fun clearDigit() {
        val length = digit_on_screen.length
        digit_on_screen.deleteCharAt(length - 1)
        binding.evalText.text = digit_on_screen.toString()

    }
}