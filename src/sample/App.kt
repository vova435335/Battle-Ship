package sample

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import sample.field.FieldView
import sample.screens.constructor.ConstructorScreen

class App : Application() {

    private lateinit var root: AnchorPane
    private val constructorScreen = ConstructorScreen()

    override fun start(primaryStage: Stage) {
        initialize(primaryStage)

        root.children.add(constructorScreen)
    }

    private fun initialize(primaryStage: Stage) {
        root = AnchorPane()
        primaryStage.title = "Battle Ship"
        primaryStage.scene = Scene(root, 1000.0, 1000.0)
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}
