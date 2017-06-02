import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import tornadofx.add

operator fun Pane.plusAssign(recs: MutableList<Rectangle>) = recs.forEach { add(it) }

fun MutableList<Shape>.addAll(vararg shapes: Shape) = shapes.forEach { add(it) }

fun <T> initTag(obj: T, init: T.() -> Unit): T {
    obj.init()
    return obj
}

fun newLabel(text: String = "" ,op: Label.() -> Unit) = initTag(Label(text), op)
fun newButton(text: String = "" ,op: Button.() -> Unit) = initTag(Button(text), op)

abstract class Shape(x: Double = 0.0, y: Double = 0.0, color: Color){
    val rects: MutableList<Rectangle> = MutableList(4) {
        val r = Rectangle(X, Y, SquareSize, SquareSize)
        r.fill = color
        return@MutableList r
    }

    var X: Double = x
        get() { return field }
        set(value) {
            field = value
            update()
        }

    var Y: Double = y
        get() { return field }
        set(value) {
            field = value
            update()
        }

    //var rotation = 0

    init {
        update(/*rotation*/)
    }

    infix fun below(shape: Shape): Boolean {
        return getRight() > shape.getLeft() && getLeft() < shape.getRight() && getTop() - shape.getBottom() >= 0 && getTop() - shape.getBottom() < SquareSize
    }

    fun getTop(): Double = (rects.minBy { it.layoutY } ?: rects[0]).layoutY

    fun getBottom(): Double = (rects.maxBy { it.layoutY }?: rects[0]).layoutY + SquareSize

    fun getLeft(): Double = (rects.minBy { it.layoutX } ?: rects[0]).layoutX

    fun getRight(): Double = (rects.maxBy { it.layoutX }?: rects[0]).layoutX + SquareSize

    fun isBottom(): Boolean = getBottom() >= SquareSize * 16

    abstract val type: String

    abstract fun update(/*rotation: Int*/): Unit

    override fun toString(): String {
        return "type: $type, right: ${getRight()}, left: ${getLeft()}, top: ${getTop()}, bottom: ${getBottom()}\n"
    }
}

class TBlock(X: Double, Y: Double): Shape(X, Y, Color.BROWN){
    override val type: String = "TBlock"

    override fun update(/*rotation: Int*/) {/*
        when(rotation % 4) {
            0 -> {*/
                rects[0].relocate(X, Y)
                rects[1].relocate(X + SquareSize, Y)
                rects[2].relocate(X + SquareSize * 2, Y)
                rects[3].relocate(X + SquareSize, Y + SquareSize)/*
            }
            1 -> {
                rects[0].relocate(X + SquareSize, Y)
                rects[1].relocate(X, Y + SquareSize)
                rects[2].relocate(X + SquareSize, Y + SquareSize)
                rects[3].relocate(X + SquareSize, Y + SquareSize * 2)
            }
            2 -> {
                rects[0].relocate(X + SquareSize, Y)
                rects[1].relocate(X, Y + SquareSize)
                rects[2].relocate(X + SquareSize, Y + SquareSize)
                rects[3].relocate(X + SquareSize, Y + SquareSize * 2)
            }
            3 -> {
                rects[0].relocate(X + SquareSize, Y)
                rects[1].relocate(X, Y + SquareSize)
                rects[2].relocate(X + SquareSize, Y + SquareSize)
                rects[3].relocate(X + SquareSize, Y + SquareSize * 2)
            }
        }*/
    }
}

class LBlock(X: Double, Y: Double): Shape(X, Y, Color.PURPLE){
    override val type: String = "LBlock"

    override fun update() {
        rects[0].relocate(X, Y)
        rects[1].relocate(X + SquareSize, Y)
        rects[2].relocate(X + SquareSize * 2, Y)
        rects[3].relocate(X, Y + SquareSize)
    }
}

class JBlock(X: Double, Y: Double): Shape(X, Y, Color.SILVER){
    override val type: String = "JBlock"

    override fun update(){
        rects[0].relocate(X, Y)
        rects[1].relocate(X + SquareSize, Y)
        rects[2].relocate(X + SquareSize * 2, Y)
        rects[3].relocate(X + SquareSize * 2, Y + SquareSize)
    }
}

class ZBlock(X: Double, Y: Double): Shape(X, Y, Color.TEAL){
    override val type: String = "ZBlock"

    override fun update(){
        rects[0].relocate(X, Y)
        rects[1].relocate(X + SquareSize, Y)
        rects[2].relocate(X + SquareSize, Y + SquareSize)
        rects[3].relocate(X + SquareSize * 2, Y + SquareSize)
    }
}

class SBlock(X: Double, Y: Double): Shape(X, Y, Color.DARKGREEN){
    override val type: String = "SBlock"

    override fun update(){
        rects[0].relocate(X, Y + SquareSize)
        rects[1].relocate(X + SquareSize, Y + SquareSize)
        rects[2].relocate(X + SquareSize, Y)
        rects[3].relocate(X + SquareSize * 2, Y)
    }
}

class OBlock(X: Double, Y: Double): Shape(X, Y, Color.NAVY){
    override val type: String = "OBlock"

    override fun update(){
        rects[0].relocate(X, Y)
        rects[1].relocate(X, Y + SquareSize)
        rects[2].relocate(X + SquareSize, Y)
        rects[3].relocate(X + SquareSize, Y + SquareSize)
    }
}

class IBlock(X: Double, Y: Double): Shape(X, Y, Color.MAROON){
    override val type: String = "IBlock"

    override fun update(){
        rects[0].relocate(X, Y)
        rects[1].relocate(X + SquareSize, Y)
        rects[2].relocate(X + SquareSize * 2, Y)
        rects[3].relocate(X + SquareSize * 3, Y)
    }
}

class Block(X: Double, Y: Double): Shape(X, Y, Color.BLACK){
    override val type: String = "Block"

    override fun update() = rects.forEach { it.relocate(X, Y) }
}