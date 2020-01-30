package sample.field

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import sample.Field
import sample.field.field_item.Miss
import sample.field.field_item.Ship
import sample.field.field_item.Water
import sample.field.field_item.WoundShip
import sample.util.FieldUtil

class FieldView {
    private val presenter = FieldPresenter(this)
    private lateinit var canvas: AnchorPane

    fun render(canvas: AnchorPane) {
        this.canvas = canvas
        presenter.start()
        drawFieldGreed()
    }

    fun onClick(x: Int, y: Int) {
        presenter.shot(FieldUtil.coordinationToPosition(x, y))
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

    private fun drawMiss(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordinationMiddle(position.col) - 4).toDouble(),
                (FieldUtil.positionToCoordinationMiddle(position.row) - 4).toDouble(),
                8.0,
                8.0
        )
        canvas.children.add(rectangle)
    }

    private fun drawShip(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordination(position.col) + 1).toDouble(),
                (FieldUtil.positionToCoordination(position.row) + 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.GREEN
        canvas.children.add(rectangle)
    }

    private fun drawWoundShip(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordination(position.col) + 1).toDouble(),
                (FieldUtil.positionToCoordination(position.row) + 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.RED
        canvas.children.add(rectangle)
    }

    private fun drawWater(position: sample.Position) {
        val rectangle = Rectangle(
                (FieldUtil.positionToCoordination(position.col) + 1).toDouble(),
                (FieldUtil.positionToCoordination(position.row) + 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble(),
                (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.BLUE
        canvas.children.add(rectangle)
    }

    private fun drawFieldGreed() {
        for (lineCounter in 0..FieldUtil.LINE_COUNT) {
            val startX = 0.0
            val endX = (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble()
            val startY = (lineCounter * FieldUtil.ITEM_SIZE).toDouble()
            val endY = (lineCounter * FieldUtil.ITEM_SIZE).toDouble()
            val lineHorizontal = Line(startX, startY, endX, endY)
            canvas.children.add(lineHorizontal)
            val lineVertical = Line(startY, startX, endY, endX)
            canvas.children.add(lineVertical)
        }
    }
}