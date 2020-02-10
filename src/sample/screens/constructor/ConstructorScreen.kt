package sample.screens.constructor

import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import sample.field.FieldView

class ConstructorScreen : AnchorPane() {

    private val playerFieldView = FieldView()
    private val opponentFieldView = FieldView()

    init {
        playerFieldView.render(this, 0, 0)
        opponentFieldView.render(this, 450, 0)

        addEventFilter(MouseEvent.MOUSE_PRESSED) { event ->
            playerFieldView.onClick(event.x.toInt(), event.y.toInt())
            opponentFieldView.onClick(event.x.toInt(), event.y.toInt())
        }
    }


}