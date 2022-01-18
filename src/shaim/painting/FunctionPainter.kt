package shaim.painting


import Plane
import shaim.Funct
import java.awt.*


class FunctionPainter(
    private val plane: Plane,
    var function: Funct
//делегат
) : Painter{

    var funColor: Color = Color.GREEN
    var parOrNot:Boolean=true
    var fOrNot:Boolean=true
    override fun paint(g: Graphics){
        with (g as Graphics2D){
            color = funColor//выбор цвета
            stroke = BasicStroke(4F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)//стиль линии

            //
            val rh = mapOf(
                RenderingHints.KEY_ANTIALIASING to RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_INTERPOLATION to RenderingHints.VALUE_INTERPOLATION_BICUBIC,
                RenderingHints.KEY_RENDERING to RenderingHints.VALUE_RENDER_QUALITY,
                RenderingHints.KEY_DITHERING to RenderingHints.VALUE_DITHER_ENABLE
            )

            setRenderingHints(rh)//для сглаживания
            with (plane) {
                if(fOrNot==true) {
                    color= Color.GREEN
                    for (x in 0 until width) {
                        if (x != xCrt2Scr(-2.0) && x != xCrt2Scr(2.0))//выкалывание точек
                        {
                            drawLine(
                                x,
                                yCrt2Scr(function.f(xScr2Crt(x))),
                                x + 1,
                                yCrt2Scr(function.f(xScr2Crt(x + 1)))
                            )
                        }
                    }
                }
                if(parOrNot==true){
                    color= Color.BLUE
                    var t=plane.tMin
                    while(t<plane.tMax){
                        if(xCrt2Scr(function.x(t))<width-1)
                            drawLine(
                                xCrt2Scr(function.x(t)),
                                yCrt2Scr(function.y(t)),
                                xCrt2Scr(function.x(t + 0.1)),
                                yCrt2Scr(function.y(t + 0.1)),
                            )
                        t = t + 0.1
                        //,,
                    }
                }
            }
        }
    }
}
