package shaim.painting


import Plane
import java.awt.*
import kotlin.math.abs

import kotlin.math.max


class CartesianPainter(private val plane: Plane) : Painter{
    // реализует интерфейс паинтер
    var mainFont: Font = Font("Cambria", Font.BOLD, 16)
    var maxTickColor: Color = Color.RED
    var k=1
    fun DefineMarc():Int
    {
        with(plane) {
            return abs(xCrt2Scr(xMin))-abs(xCrt2Scr(xMin+1*k))
        }
    }

    override fun paint(g: Graphics) {
        with(plane) {
            (g as Graphics2D).apply {
                stroke = BasicStroke(2F)
                if ((xMin > 0) or (xMax < 0)) {
                    drawLine(width, 0, width, height)
                    drawLine(0, 0, 0, height)
                    DrawYL(g)
                } else drawLine(xCrt2Scr(0.0), 0, xCrt2Scr(0.0), height)
                if ((yMin > 0) or (yMax < 0)) {
                    drawLine(0, height, width, height)
                    drawLine(0, 0, width, 0)
                    DrawXL(g)
                    //Нарисовать метки без цифр?
                } else drawLine(0, yCrt2Scr(0.0), width, yCrt2Scr(0.0))

                val c=(width)/16
                drawXLabels(g)
                drawYLabels(g)
            }
        }
    }

    private fun DrawXL(g:Graphics){
        with (g as Graphics2D){
            stroke = BasicStroke(1F)
            color = maxTickColor
            font = mainFont

            with(plane) {
                for (tickValue in xMin.toInt()..xMax.toInt()) {
                    var tSize = 8
                    drawLine(xCrt2Scr(tickValue.toDouble()), 0, xCrt2Scr(tickValue.toDouble()), tSize)
                    for(l in 1..9){
                        if(l==5)drawLine(xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0), 0, xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0   ), tSize-3)
                        else drawLine(xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0), 0, xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0   ),tSize-5)
                    }
                }
            }
        }
    }
    private fun DrawYL(g:Graphics) {
        with(g as Graphics2D) {
            stroke = BasicStroke(1F)
            color = maxTickColor
            font = mainFont

            with(plane) {
                for (tickValue in yMin.toInt()..yMax.toInt()) {
                    var tSize = 8
                    //отрисовка черточкм
                    drawLine(0, yCrt2Scr(tickValue.toDouble()), tSize, yCrt2Scr(tickValue.toDouble()))
                    for (l in 1..9) {
                        if (l == 5) drawLine(
                            0,
                            yCrt2Scr(tickValue.toDouble() + l.toDouble() / 10.0),
                            tSize - 3,
                            yCrt2Scr(tickValue.toDouble() + l.toDouble() / 10.0)
                        )
                        else drawLine(
                            0,
                            yCrt2Scr(tickValue.toDouble() + l.toDouble() / 10.0),
                            tSize - 5,
                            yCrt2Scr(tickValue.toDouble() + l.toDouble() / 10.0)
                        )
                    }

                }
            }
        }
    }

    private fun drawXLabels(g: Graphics) {//отрисовка меток
        //для динамической чтобы метки не налезали друг на друга

        with (g as Graphics2D){
            stroke = BasicStroke(1F)
            color = maxTickColor
            font = mainFont

            with(plane) {

                //if(DefineMarc()<8*3)k+=5
                //if(DefineMarc()>=57)if(k>5)k-=5

                for (tickValue in xMin.toInt()..xMax.toInt() ) {

                    var tSize = 8

                    //отрисовка черточкм

                    drawLine(xCrt2Scr(tickValue.toDouble())*k, yCrt2Scr(0.0) - tSize, xCrt2Scr(tickValue.toDouble())*k, yCrt2Scr(0.0) + tSize)

                    for(l in 1..9){
                        if(l==5)drawLine(xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0), yCrt2Scr(0.0) - tSize+3, xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0   ), yCrt2Scr(0.0) + tSize-3)
                        else drawLine(xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0), yCrt2Scr(0.0) - tSize+5, xCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0   ), yCrt2Scr(0.0) + tSize-5)
                    }

                    val (tW, tH) = with(fontMetrics.getStringBounds((tickValue).toString(), g)) {
                        Pair(width.toInt(), height.toInt())
                    }//узнаем высоту и ширину.результат последнего выражения в лямда выражении

                    //класс пара
                    //отрисовка значений
                    if(tickValue!=0)drawString((tickValue*k).toDouble().toString(), xCrt2Scr(tickValue.toDouble()) - tW / 2, yCrt2Scr(0.0) + tSize + tH)
                    // for(k:Double in tickValue..tickValue+1 )
                }
            }
        }
    }

    private fun drawYLabels(g: Graphics) {//отрисовка меток
        //для динамической чтобы метки не налезали друг на друга
        with (g as Graphics2D){
            stroke = BasicStroke(1F)
            color = maxTickColor
            font = mainFont
            with(plane) {
                for (tickValue in yMin.toInt()..yMax.toInt()) {
                    var tSize = 8
                    //отрисовка черточкм
                    drawLine(xCrt2Scr(0.0)-tSize, yCrt2Scr(tickValue.toDouble()), xCrt2Scr(0.0)+tSize, yCrt2Scr(tickValue.toDouble()))
                    for(l in 1..9){
                        if(l==5)drawLine(xCrt2Scr(0.0)-tSize+3, yCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0), xCrt2Scr(0.0)+tSize-3, yCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0))
                        else drawLine(xCrt2Scr(0.0)-tSize+5, yCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0), xCrt2Scr(0.0)+tSize-5, yCrt2Scr(tickValue.toDouble()+l.toDouble()/10.0))
                    }
                    val (tW, tH) = with(fontMetrics.getStringBounds(tickValue.toString(), g)) {
                        Pair(width.toInt(), height.toInt())
                    }//узнаем высоту и ширину.результат последнего выражения в лямда выражении
                    //класс пара
                    //отрисовка значений
                    if(tickValue!=0)drawString(tickValue.toDouble().toString(), xCrt2Scr(0.0)+tSize+tW/2, yCrt2Scr(tickValue.toDouble())+tH/2)
                    else drawString(tickValue.toDouble().toString(), xCrt2Scr(0.0)+tSize+tW/2, yCrt2Scr(tickValue.toDouble())+tH)
                    // drawString(tickValue.toString(), xCrt2Scr(tickValue.toDouble()) - tW / 2, yCrt2Scr(0.0) + tSize + tH)
                }
            }
        }
    }
}