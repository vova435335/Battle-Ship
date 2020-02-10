package sample.field

import sample.Position
import sample.field.field_item.Miss
import sample.field.field_item.Ship
import sample.field.field_item.Water
import sample.field.field_item.WoundShip

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
}