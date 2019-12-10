package com.example.a399phase3calculator

class Calculation
{
    fun calculate( calculatorList : List< String > ) : Double
    {
        var workingOperation = ""

        var workingNumber: Double = 0.0

        calculatorList.forEach{ token ->

            when
            {       token.equals( "+" )
                    || token.equals( "%" )
                    || token.equals( "+/-" )
                    || token.equals( "-" )
                    || token.equals( "*" )
                    || token.equals( "/" ) -> workingOperation = token

                workingOperation.equals( "-" ) -> workingNumber -= token.toDouble()

                workingOperation.equals( "/" ) -> workingNumber /= token.toDouble()

                workingOperation.equals( "*" ) -> workingNumber *= token.toDouble()

                workingOperation.equals( "%" ) -> workingNumber *= (.01)

                workingOperation.equals( "+/-" ) -> workingNumber *= (-1)

                else -> workingNumber += token.toDouble()
            }

        }

        return workingNumber
    }
}