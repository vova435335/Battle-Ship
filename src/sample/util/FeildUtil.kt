package sample.util

object FieldUtil {

    val ITEM_INLINE_COUNT = 10
    val LINE_COUNT = 10
    val ITEM_SIZE = 40

    fun positionToCoordinationMiddle(positionValue: Int): Int {
        return (positionValue - 1) * ITEM_SIZE + ITEM_SIZE / 2
    }

    fun positionToCoordination(positionValue: Int): Int {
        return (positionValue - 1) * ITEM_SIZE
    }

}