package shaim

import shaim.painting.CartesianPainter
import shaim.painting.Painter
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel
//класс для открисовки
//наследник
//
class GraphicsPanel(private  val painters: List<Painter>) : JPanel() {

    //переопрееленный
    //в качестве парамера графикс
    override fun paint(g: Graphics?) {
        super.paint(g)//очищает
        g?.let{
            // painter.paint(it)
            painters.forEach { p->
                p.paint(it)
            }
        }
        //метод предыдуего
//        g?.let {
//            it.color = Color.RED
//            it.fillRect(40, 50, 200, 200)
//            it.color = Color.BLUE
//            it.fillOval(250, 50, 200, 200)
//         }
    }
    //
}