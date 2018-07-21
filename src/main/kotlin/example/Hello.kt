package example

import javafx.scene.layout.Pane
import tornadofx.*

class HelloWorld : View() {
    override val root = gridpane {
        gridpaneConstraints {
            hgap = 10.0
            vgap = 10.0
        }
        row {
            add(cell("111"))
            add(cell("222"))
        }
        row {
            add(cell("wrong usage with rowIndex/columnIndex").gridpaneConstraints {
                columnSpan = 2
                // following constraints are not working with `row`, they are ignored silently
                // `this.columnRowIndex(6, 6)`, or
                rowIndex = 6
                columnIndex = 6
            })
        }

        add(cell("correct usage").gridpaneConstraints {
            columnSpan = 2
            // following constraints are not working with `row`
            // `this.columnRowIndex(6, 6)`, or
            rowIndex = 6
            columnIndex = 6
        })
    }

    private fun cell(label: String): Pane {
        return pane {
            style = "-fx-background-color: RED"
            prefWidth = 50.0
            prefHeight = 50.0
            label(label)
        }
    }

}

class HelloWorldStyle : Stylesheet() {
    init {
        root {
            prefWidth = 400.px
            prefHeight = 400.px
        }
    }
}

class HelloWorldApp : App(HelloWorld::class, HelloWorldStyle::class)

fun main(args: Array<String>) {
    launch<HelloWorldApp>()
}
