package sample.screens.constructor

import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import sample.field.FieldState
import sample.field.FieldView

class ConstructorScreen : AnchorPane() {

    private val playerFieldView = FieldView(FieldState.CONSTRUCTOR)

    init {
        playerFieldView.render(this, 0, 0)

        addEventFilter(MouseEvent.MOUSE_PRESSED) { event ->
            playerFieldView.onClick(event.x.toInt(), event.y.toInt())
        }
    }


}