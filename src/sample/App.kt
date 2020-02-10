package sample

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import sample.field.FieldView

class App : Application() {

    private lateinit var root: AnchorPane
    private val playerFieldView = FieldView()
    private val opponentFieldView = FieldView()
    private val canvas = AnchorPane()

    override fun start(primaryStage: Stage) {
        initialize(primaryStage)

        playerFieldView.render(canvas, 0, 0)
        opponentFieldView.render(canvas, 450, 0)
        root.children.add(canvas)
    }

    private fun onClick(x: Int, y: Int) {
        playerFieldView.onClick(x, y)
        opponentFieldView.onClick(x, y)
    }

    private fun initialize(primaryStage: Stage) {
        root = AnchorPane()
        primaryStage.title = "Hello World"
        primaryStage.scene = Scene(root, 1000.0, 1000.0)
        primaryStage.show()

        root.addEventFilter(MouseEvent.MOUSE_PRESSED) { event ->
            onClick(event.x.toInt(), event.y.toInt())
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}
