import javafx.animation.AnimationTimer
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import tornadofx.*
import java.util.Random



val SquareSize = 40.0

class Tetris: App(Stage::class)

class Stage: View() {
    val canvas = Pane()
    val shapes = mutableListOf<Shape>()

    override val root = gridpane {
        hgap = 10.0
        vgap = 10.0
        padding = Insets(10.0, 10.0, 10.0, 10.0)

        add(canvas, 0, 0, 1, 20)
        add(Label("Hey there 1!"), 1, 0)
        add(newButton("Add random block!!"){
            action {
                val random = Random()
                shapes += when (random.nextInt(7)) {
                    0 -> TBlock(0.0, 0.0)
                    1 -> LBlock(0.0, 0.0)
                    2 -> JBlock(0.0, 0.0)
                    3 -> ZBlock(0.0, 0.0)
                    4 -> SBlock(0.0, 0.0)
                    5 -> OBlock(0.0, 0.0)
                    6 -> IBlock(0.0, 0.0)
                    else -> Block(0.0, 0.0)
                }
            }
        }, 1, 2)
    }

    init {
        canvas.setPrefSize(SquareSize * 10, SquareSize * 18)

        /*
        shapes.addAll(OBlock(SquareSize * 2, 0.0), JBlock(0.0, SquareSize * 2), Block(SquareSize * 3, SquareSize * 5))

        println(shapes[0].type + " below " + shapes[0].type + ": " + (shapes[0] below shapes[0]))
        println(shapes[0].type + " below " + shapes[1].type + ": " + (shapes[0] below shapes[1]))
        println(shapes[0].type + " below " + shapes[2].type + ": " + (shapes[0] below shapes[2]))
        println(shapes[1].type + " below " + shapes[0].type + ": " + (shapes[1] below shapes[0]))
        println(shapes[1].type + " below " + shapes[1].type + ": " + (shapes[1] below shapes[1]))
        println(shapes[1].type + " below " + shapes[2].type + ": " + (shapes[1] below shapes[2]))
        println(shapes[2].type + " below " + shapes[0].type + ": " + (shapes[2] below shapes[0]))
        println(shapes[2].type + " below " + shapes[1].type + ": " + (shapes[2] below shapes[1]))
        println(shapes[2].type + " below " + shapes[2].type + ": " + (shapes[2] below shapes[2]))

        //canvas += Rectangle(0.0, SquareSize, SquareSize, SquareSize)
        */

        val startNanoTime = System.nanoTime()
        var pt = 0
        var work = true
        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {
                val t = (currentNanoTime - startNanoTime) / 1000000000.0
                if(pt != t.toInt()) {
                    pt = t.toInt()
                    work = true
                }

                if(work) {
                    shapes.forEach { elem ->
                        println(elem)

                        if (!canvas.children.contains(elem.rects[0]))
                            canvas += elem.rects

                        if (!elem.isBottom() && !shapes.any { it below elem })
                            elem.Y += SquareSize
                    }
                }
                work = false
            }
        }.start()
    }
}