package com.example.a399phase3calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateDisplay("")
    }

    val operationArray : MutableList<String> = arrayListOf()
    val numberList : MutableList<String> = arrayListOf()

    //Function that takes the list of values from the array list and reduces them to one value
    private fun extractString (items : List<String> , connect: String = "") : String
    {
        if( items.isEmpty() ) return ""
        return items.reduce{ acc , s -> acc + connect + s }
    }

    private fun updateDisplay (mainDisplayString : String) {
        val calcString = extractString(operationArray, "")

        var calcTextView = findViewById<View>(R.id.equationDisplay) as TextView

        calcTextView.text = calcString

        val answer = findViewById<Button>(R.id.answerDisplay) as TextView

        answer.text = mainDisplayString
    }

    fun numberClick ( view : View )
    {
        val button = view as Button

        val numberString = button.text

        numberList.add ( numberString.toString() )

        val text = extractString( numberList )

        updateDisplay( text )
    }

    fun operatorClick ( view : View )
    {
        val button = view as Button

        if (numberList.isEmpty()) return

        operationArray.add(extractString(numberList))
        numberList.clear()
        operationArray.add(button.text.toString())
        updateDisplay(button.text.toString())
    }

    private fun clearNumberList (  )
    {
        operationArray.clear()

        numberList.clear()
    }

    fun acButton ( view: View )
    {
        clearNumberList()

        updateDisplay("")
    }

    fun specialClick( view: View )
    {
        val button = view as Button

        if (numberList.isEmpty()) return

        operationArray.add(extractString(numberList))
        numberList.clear()
        operationArray.add(button.text.toString())
        updateDisplay(button.text.toString())

        operationArray.add( extractString( numberList ) )

        numberList.clear()

        val calculator = Calculation()

        val answer = calculator.calculate( operationArray )

        updateDisplay( "=" + answer.toString() )
        clearNumberList()

    }

    fun equalClick ( view : View )
    {
        operationArray.add( extractString( numberList ) )

        numberList.clear()

        val calculator = Calculation()

        val answer = calculator.calculate( operationArray )

        updateDisplay( "=" + answer.toString() )
        clearNumberList()
    }
}
