package shaim.painting


import Plane
import java.awt.*


class FunctionPainter(
    private val plane: Plane,
    var function: (Double)->Double = Math::cos
//делегат
) : Painter{

    var funColor: Color = Color.GREEN

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

                for (x in 0 until width){
                    if(x!=xCrt2Scr(-2.0) && x!=xCrt2Scr(2.0) )//выкалывание точек
                    {
                        drawLine(
                            x,
                            yCrt2Scr(function(xScr2Crt(x))),
                            x + 1,
                            yCrt2Scr(function(xScr2Crt(x + 1)))
                        )
                    }
                }
            }
        }
    }
}
