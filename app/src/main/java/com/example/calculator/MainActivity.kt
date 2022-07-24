package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import net.objecthunter.exp4j.ExpressionBuilder



class MainActivity : AppCompatActivity(){
    lateinit var outputText: TextView
    var numericlast : Boolean = false
    var dotlast : Boolean=false
    var stateError : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        outputText=findViewById(R.id.textView)
    }

    fun onDigit(view: View) {

        if(stateError)
        {
            outputText.text=(view as Button).text
            stateError=false

        }
        else
        {
            outputText.append((view as Button).text)
        }
        numericlast=true
    }
    fun onEqual(view: View) {
        if(numericlast && !stateError)
        {
            val text=outputText.text.toString()
            val exp=ExpressionBuilder(text).build()
            try
            {
                val result= exp.evaluate()
                outputText.text= result.toString()
                dotlast=true
            }catch (ex:Exception)
            {
                outputText.text="Error"
                stateError=true
                numericlast=false
            }
        }
    }
    fun onOperator(view: View) {
        if (numericlast && !stateError)
        {
            outputText.append((view as Button).text)
            numericlast=false
            dotlast=false
        }
    }
    fun onClear(view: View) {
        this.outputText.text=""
        numericlast=false
        dotlast=false
    }
    fun onDecimalPoint(view: View) {
        if(numericlast && !dotlast && !stateError)
        {
            outputText.append(".")
            numericlast=false
            dotlast=true
        }
    }
}