package sample.field

import sample.Position
import sample.field.field_item.*
import sample.util.FieldUtil

class FieldPresenter(private val view: FieldView) {

    private val model = FieldModel()

    fun start() {
        view.drawField(model.field)
    }

    fun shot(position: Position) {
        val item = model.field.items[position]

        when (item) {
            is Water -> {
                model.field.items[position] = Miss()
            }
            is Ship -> {
                model.field.items[position] = WoundShip()
            }
            else -> {
                //Do nothing
            }
        }
        view.drawField(model.field)
    }

    fun constructor(position: Position) {
        val item = model.field.items[position]

        when (item) {
            is Water -> {
                model.field.items[position] = Ship()
            }
            else -> {
                //Do nothing
            }
        }
        view.drawField(model.field)
    }

    fun setItems(): Int {
        var count = 0
        for (col in 1..FieldUtil.ITEM_INLINE_COUNT) {
            for (row in 1..FieldUtil.LINE_COUNT) {
                if(model.field.items[Position(col, row)] is Ship){
                    count++
                }
            }
        }
        return count
    }

}