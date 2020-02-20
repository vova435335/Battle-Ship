package sample.field

import sample.Position
import sample.field.field_item.FieldItem
import sample.field.field_item.Water
import sample.util.FieldUtil.ITEM_INLINE_COUNT
import sample.util.FieldUtil.LINE_COUNT

class Field {
    val items = mutableMapOf<Position, FieldItem>()

    init {
        for (col in 1..ITEM_INLINE_COUNT) {
            for (row in 1..LINE_COUNT) {
                items[Position(col, row)] = Water()
            }
        }
    }
}