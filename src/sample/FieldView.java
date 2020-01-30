package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.field.field_item.*;
import sample.util.FieldUtil;

import java.util.Map;

public class FieldView extends Application {

    private sample.FieldPresenter presenter = new FieldPresenter(this);
    private AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        initialize(primaryStage);
        drawFieldGreed();

    }

    public void drawField(Field field) {
        for (Map.Entry<Position, FieldItem> item : field.getItems().entrySet()) {
            if (item.getValue() instanceof Ship) {
                drawShip(item.getKey());
            } else if (item.getValue() instanceof WoundShip) {
                drawWoundShip(item.getKey());
            } else if (item.getValue() instanceof Miss) {
                drawMiss(item.getKey());
            } else if (item.getValue() instanceof Water) {
                drawWater(item.getKey());
            }
        }
    }

    public void drawMiss(sample.Position position) {
        Rectangle rectangle = new Rectangle(
                FieldUtil.INSTANCE.positionToCoordinationMiddle(position.getCol()) - 4,
                FieldUtil.INSTANCE.positionToCoordinationMiddle(position.getRow()) - 4,
                8,
                8
        );
        root.getChildren().add(rectangle);
    }

    public void drawShip(sample.Position position) {
        Rectangle rectangle = new Rectangle(
                FieldUtil.INSTANCE.positionToCoordination(position.getCol() - 1),
                FieldUtil.INSTANCE.positionToCoordination(position.getRow() - 1),
                FieldUtil.INSTANCE.getITEM_INLINE_COUNT() - 1,
                FieldUtil.INSTANCE.getITEM_INLINE_COUNT() - 1
        );
        rectangle.setFill(Color.GREEN);
        root.getChildren().add(rectangle);
    }

    public void drawWoundShip(sample.Position position) {
        Rectangle rectangle = new Rectangle(
                FieldUtil.INSTANCE.positionToCoordination(position.getCol() - 1),
                FieldUtil.INSTANCE.positionToCoordination(position.getRow() - 1),
                FieldUtil.INSTANCE.getITEM_INLINE_COUNT() - 1,
                FieldUtil.INSTANCE.getITEM_INLINE_COUNT() - 1
        );
        rectangle.setFill(Color.RED);
        root.getChildren().add(rectangle);
    }

    public void drawWater(sample.Position position) {
        Rectangle rectangle = new Rectangle(
                FieldUtil.INSTANCE.positionToCoordination(position.getCol()) + 1,
                FieldUtil.INSTANCE.positionToCoordination(position.getRow()) + 1,
                FieldUtil.INSTANCE.getITEM_INLINE_COUNT() - 1,
                FieldUtil.INSTANCE.getITEM_INLINE_COUNT() - 1
        );
        rectangle.setFill(Color.BLUE);
        root.getChildren().add(rectangle);
    }

    private void drawFieldGreed() {
        for (int lineCounter = 0; lineCounter <= FieldUtil.INSTANCE.getLINE_COUNT(); lineCounter++) {
            double startX = 0;
            double endX = FieldUtil.INSTANCE.getITEM_SIZE() * FieldUtil.INSTANCE.getITEM_INLINE_COUNT();
            double startY = lineCounter * FieldUtil.INSTANCE.getITEM_SIZE();
            double endY = lineCounter * FieldUtil.INSTANCE.getITEM_SIZE();
            Line lineHorizontal = new Line(startX, startY, endX, endY);
            root.getChildren().add(lineHorizontal);
            Line lineVertical = new Line(startY, startX, endY, endX);
            root.getChildren().add(lineVertical);
        }
    }

    private sample.Position getItemNumber(int xPosition, int yPosition) {
        double col = xPosition / FieldUtil.INSTANCE.getITEM_SIZE() + 1;
        double row = yPosition / FieldUtil.INSTANCE.getITEM_SIZE() + 1;
        return new Position((int) col, (int) row);
    }

    private void initialize(Stage primaryStage) {
        root = new AnchorPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();


        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                presenter.shot(getItemNumber((int) event.getX(), (int) event.getY()));
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
