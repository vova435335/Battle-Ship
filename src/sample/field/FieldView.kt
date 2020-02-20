package sample.field

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import sample.Position
import sample.field.field_item.*
import sample.util.FieldUtil

class FieldView(var state: FieldState) {

    private val presenter = FieldPresenter(this)
    private lateinit var canvas: AnchorPane
    private var x: Int = 0
    private var y: Int = 0

    fun render(canvas: AnchorPane, x: Int, y: Int) {
        this.x = x
        this.y = y
        this.canvas = canvas
        presenter.start()
        drawFieldGreed()
    }

    fun onClick(x: Int, y: Int) {
        when (state) {
            FieldState.CONSTRUCTOR -> {
                presenter.constructor(FieldUtil.coordinationToPosition(x - this.x, y - this.y))
            }
            FieldState.BATTLE -> {
                presenter.shot(FieldUtil.coordinationToPosition(x - this.x, y - this.y))
            }
        }
    }

    fun drawField(field: Field) {
        for ((key, value) in field.items) {
            when (value) {
                is Ship -> drawShip(key)
                is WoundShip -> drawWoundShip(key)
                is Miss -> drawMiss(key)
                is Water -> drawWater(key)
            }
        }
    }

    fun setShips():Int{
        return presenter.setItems()
    }

    private fun drawMiss(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordinationMiddle(position.col) - 4).toDouble() + x,
                (FieldUtil.positionToCoordinationMiddle(position.row) - 4).toDouble() + y,
                8.0,
                8.0
        )

        canvas.children.add(rectangle)
    }

    private fun drawShip(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordination(position.col) + 1).toDouble() + x,
                (FieldUtil.positionToCoordination(position.row) + 1).toDouble() + y,
                (FieldUtil.ITEM_SIZE - 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.GREEN
        canvas.children.add(rectangle)
    }

    private fun drawWoundShip(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordination(position.col) + 1).toDouble() + x,
                (FieldUtil.positionToCoordination(position.row) + 1).toDouble() + y,
                (FieldUtil.ITEM_SIZE - 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.RED
        canvas.children.add(rectangle)
    }

    private fun drawWater(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordination(position.col) + 1).toDouble() + x,
                (FieldUtil.positionToCoordination(position.row) + 1).toDouble() + y,
                (FieldUtil.ITEM_SIZE - 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.BLUE
        canvas.children.add(rectangle)
    }

    private fun drawFieldGreed() {
        for (lineCounter in 0..FieldUtil.LINE_COUNT) {
            var startX = 0.0 + x
            var endX = (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble() + x
            var startY = (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + y
            var endY = (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + y
            val lineHorizontal = Line(startX, startY, endX, endY)
            canvas.children.add(lineHorizontal)
            startX = (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + x
            endX = (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + x
            startY = 0.0 + y
            endY = (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble() + y
            val lineVertical = Line(startX, startY, endX, endY)
            canvas.children.add(lineVertical)
        }
    }


}