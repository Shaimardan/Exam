package shaim.painting

import Plane
import java.awt.*

class PointPainter(
    private val plane: Plane,
    public var point: MutableMap<Double, Double>
) : Painter{

    var funColor: Color = Color.BLUE

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
                //  setRenderingHints(rh)//для сглаживания
                var x=point.keys.toList()
                for(i in 0..x.size-1) {
                    //drawOval(plane.xCrt2Scr(x[i]) - 2, plane.yCrt2Scr(point[x[i]]!!) - 2, 6, 6)
                    g.fillOval(plane.xCrt2Scr(x[i]) - 2, plane.yCrt2Scr(point[x[i]]!!) - 2, 6, 6)
                }

            }
        }
    }
}