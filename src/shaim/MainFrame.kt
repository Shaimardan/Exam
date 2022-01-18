package shaim

import Plane
import java.awt.*

import shaim.GraphicsPanel
import shaim.painting.*
import shaim.painting.Painter
import java.awt.Color
import java.awt.Dimension
import java.awt.event.*
import java.lang.Math.abs
import javax.swing.*

import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

//Нужно провеиритььч что точка уже существует рядом с другой
class  MainFrame : JFrame(){

    val mainPanel: GraphicsPanel
    val controlPanel: JPanel

    val xMin: JSpinner
    val xMinM: SpinnerNumberModel
    val xMax: JSpinner
    val xMaxM: SpinnerNumberModel
    val yMin: JSpinner
    val yMinM: SpinnerNumberModel
    val yMax: JSpinner
    val yMaxM: SpinnerNumberModel

    val tMin: JSpinner
    val tMinM: SpinnerNumberModel
    val tMax: JSpinner
    val tMaxM: SpinnerNumberModel




    var checkbox1:JCheckBox
    val checkbox2:JCheckBox
    val checkbox3:JCheckBox
    //val checkbox4:JCheckBox

    val btn1:JButton
    val btn2:JButton
    val btn3:JButton

    val lable1:JLabel
    val lable2:JLabel


    val lablexmin:JLabel
    val lablexmax:JLabel
    val lableymin:JLabel
    val lableymax:JLabel

    val labletmin:JLabel
    val labletmax:JLabel

    //var addornot:Boolean

    // val point=mapOf(-1.0 to 1.0, 1.0 to 1.0, 0.0 to 0.0) as MutableMap<Double, Double>
    //val coss= mutableMap<Double, Double>()
    val point: MutableMap<Double, Double> = mutableMapOf()

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(600, 400)

        xMinM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        xMin = JSpinner(xMinM)
        xMaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        xMax = JSpinner(xMaxM)
        yMinM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        yMin = JSpinner(yMinM)
        yMaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        yMax = JSpinner(yMaxM)

        tMinM = SpinnerNumberModel(-6.0, -100.0, 4.9, 0.1)
        tMin = JSpinner(tMinM)
        tMaxM = SpinnerNumberModel(6.0, -4.9, 100.0, 0.1)
        tMax = JSpinner(tMaxM)

        checkbox1=JCheckBox()
        checkbox1.setSelected(true);
        // if (checksChecked

        checkbox2=JCheckBox()
        checkbox2.setSelected(true);
        checkbox3=JCheckBox()
        checkbox3.setSelected(true);
        //heckbox4=JCheckBox()

        btn1=JButton().apply {
            background=Color. BLUE
        }
        btn2=JButton().apply {
            background=Color.GREEN
        }
        btn3=JButton().apply {
            background=Color.PINK
        }

        lable1=JLabel()
        lable1.setForeground(Color.WHITE)
        lable2=JLabel()
        lable2.setForeground(Color.WHITE)


        lable1.text="отображать график функции"
        lable2.text="отображать график параметрической функции"

        lablexmin=JLabel()
        lablexmin.setForeground(Color.WHITE)
        lablexmax=JLabel()
        lablexmax.setForeground(Color.WHITE)
        lableymin=JLabel()
        lableymin.setForeground(Color.WHITE)
        lableymax=JLabel()
        lableymax.setForeground(Color.WHITE)


        labletmin=JLabel()
        labletmin.setForeground(Color.WHITE)
        labletmax=JLabel()
        labletmax.setForeground(Color.WHITE)

        lablexmin.text="xMin"
        lablexmax.text="xMax"
        lableymin.text="yMin"
        lableymax.text="yMax"

        labletmin.text="yMin"
        labletmax.text="yMax"


        val plane=Plane(
            xMinM.value as Double,
            xMaxM.value as Double,
            yMinM.value as Double,
            yMaxM.value as Double
        )
        val f1=Funct()

        val cartesianPainter = CartesianPainter(plane)//плоскасть
        val f11 = FunctionPainter(plane, f1::f)//
        val fpar = FunctionParPainter(plane, f1::x,f1::y)//


        val painters = mutableListOf<Painter>(cartesianPainter)
        painters.add(f11)
        painters.add(fpar)
        mainPanel = GraphicsPanel(painters).apply {
            background = Color.WHITE
        }

        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                plane.width = mainPanel.width
                plane.height = mainPanel.height
                mainPanel.repaint()
            }
        })

        checkbox1.addItemListener(ItemListener {
            if(checkbox1.isSelected)painters.add(f11)
            else painters.remove(f11)
            mainPanel.repaint()
        })
        checkbox2.addItemListener(ItemListener {
            if(checkbox2.isSelected){
               // painters.add(1,(test2Painter))
                painters.add(fpar)
            }
            else painters.remove(fpar)
            mainPanel.repaint()
        })



        controlPanel = JPanel().apply{
            background = Color.RED
        }
        layout = GroupLayout(contentPane).apply{
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                            .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    )
                    .addGap(4)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(4)
                    .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(4)
            )
        }



//лямда выражение-ананимная функция,использование вместе с описыванием)
        xMin.addChangeListener{
            xMaxM.minimum = xMin.value as Double + 0.1
            plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        xMax.addChangeListener{
            xMinM.maximum = xMax.value as Double - 0.1
            plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        yMin.addChangeListener{
            yMaxM.minimum = yMin.value as Double + 0.1
            plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }
        yMax.addChangeListener{
            yMinM.maximum = yMax.value as Double - 0.1
            plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }



        tMin.addChangeListener{
            tMaxM.minimum = tMin.value as Double + 0.1
            plane.tMin= tMin.value as Double
            mainPanel.repaint()

        }
        tMax.addChangeListener{
            tMinM.maximum = tMax.value as Double - 0.1
            plane.tMax= tMax.value as Double
            mainPanel.repaint()
        }



        controlPanel.layout = GroupLayout(controlPanel).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(lablexmin)
                            .addComponent(lableymin)
                            .addComponent(labletmin)
                    )
                    .addGroup(
                        createParallelGroup()
                            .addComponent(xMin, 100, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yMin, 100, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tMin, 100, 100, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(8)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(lablexmax)
                            .addComponent(lableymax)
                            .addComponent(labletmax)
                    )
                    .addGroup(
                        createParallelGroup()
                            .addComponent(xMax, 100, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yMax, 100, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tMax, 100, 100, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(8,8, Int.MAX_VALUE)


                    .addGroup(
                        createParallelGroup()
                            .addComponent(checkbox1,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkbox2,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                    )
                    .addGroup(
                        createParallelGroup()
                            .addComponent(lable1,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lable2,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                    )
                    .addGap(10)
                    .addGap(4, 4, Int.MAX_VALUE)
            )
            setVerticalGroup(
                createSequentialGroup()

                    //.addComponent(textField, 30, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    //Минимально возможный предпочетаемый максимаоьно возможный
                    // .addGap(8, 8, Int.MAX_VALUE)
                    .addGroup(
                        createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addGap(15)
                            //.addComponent(checkbox1  , GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(lable1,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lable2,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(checkbox1,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkbox2,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(xMin,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yMin,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tMin,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(lablexmax)
                                    .addComponent(lableymax)
                                    .addComponent(labletmax)
                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(xMax,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yMax,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tMax,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(lablexmin)
                                    .addComponent(lableymin)
                                    .addComponent(labletmin)

                            )

                        //.addComponent(btnPress2,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                    )
            )
        }
        pack()
        plane.width = mainPanel.width
        plane.height = mainPanel.height

    }

}