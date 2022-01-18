package shaim

import kotlin.math.pow

class Funct {
    //fun f(x:Double)=Math.abs(x)/(Math.abs(x+1))
    fun f(x:Double)=x*x+(2.0).pow(x)

    fun x(t:Double)=Math.cos(t)
    fun y(t:Double)=t+Math.sin(t)
}