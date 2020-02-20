package sample.util

import sample.Position

object FieldUtil {

    val ITEM_INLINE_COUNT = 10
    val LINE_COUNT = 10
    val ITEM_SIZE = 40
    val START_SHIPS_COUNT = 20

    fun positionToCoordinationMiddle(positionValue: Int): Int {
        return (positionValue - 1) * ITEM_SIZE + ITEM_SIZE / 2
    }

    fun positionToCoordination(positionValue: Int): Int {
        return (positionValue - 1) * ITEM_SIZE
    }

    fun coordinationToPosition(xPosition: Int, yPosition: Int): sample.Position {
        val col = (xPosition / FieldUtil.ITEM_SIZE + 1).toDouble()
        val row = (yPosition / FieldUtil.ITEM_SIZE + 1).toDouble()
        return Position(col.toInt(), row.toInt())
    }

}