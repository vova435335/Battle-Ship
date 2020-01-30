package sample

import sample.field.field_item.Miss
import sample.field.field_item.Ship
import sample.field.field_item.Water
import sample.field.field_item.WoundShip

class FieldPresenter(private val view: FieldView) {

    private val model = FieldModel()

    fun shot(position: Position) {
        val item = model.field.items[position]
        when(item){
            is Water ->{
                model.field.items[position] = Miss()
            }
            is Ship -> {
                model.field.items[position] = WoundShip()
            }
            else -> {
                //ToDo
            }
        }
        view.drawField(model.field)
    }
}