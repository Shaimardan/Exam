package shaim.painting


import Plane
import java.awt.*
import kotlin.math.PI


class FunctionParPainter(
    private val plane: Plane,
    var funX: (Double)->Double = Math::cos,
    var funY: (Double)->Double = Math::sin
//делегат
//делегат
) : Painter{

    var funColor: Color = Color.blue



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


                var t=plane.tMin
                while(t<plane.tMax){
                        if(xCrt2Scr(funX(t))<width-1)
                        drawLine(
                            xCrt2Scr(funX(t)),
                            yCrt2Scr(funY(t)),
                            xCrt2Scr(funX(t + 0.1)),
                            yCrt2Scr(funY(t + 0.1)),
                        )
                        t = t + 0.1
                    //,,
                }
           }
        }

    }
}

