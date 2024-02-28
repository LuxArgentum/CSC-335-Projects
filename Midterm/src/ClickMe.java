import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClickMe extends Application {

    private static final int width = 254;
    private static final int height = 100;
    private BorderPane window;
    private Canvas canvas;
    private GraphicsContext g;
    private Button clickMeButton;
    private Button clickCountButton;
    private int xPos;
    private int yPos;
    private int clickCount;

    @Override
    public void start(Stage stage) {
        window = new BorderPane();
        Scene scene = new Scene(window, width, height);
        clickMeButton = new Button("Click Me");
        canvas = new Canvas(254, 50);
        g = canvas.getGraphicsContext2D();
//        canvas.getGraphicsContext2D().setFill(Color.BLUE);
//        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        xPos = 1;
        yPos = 10;
        clickCountButton = new Button("0");

        // Top button that says "Click Me"
        window.setTop(clickMeButton);
        clickMeButton.setOnAction(e -> {
            clickCount++;
            clickCountButton.setText(Integer.toString(clickCount));
            drawLines();
        });

        // Draw lines in middle
        window.setCenter(canvas);

        // Bottom button that says how many times clicked
        window.setBottom(clickCountButton);

        stage.setScene(scene);
        stage.show();
    }

    private void drawLines() {
        xPos += 5;
        draw();
    }

    private void draw() {
        g.setStroke(Color.BLACK);  // Set the line color
        g.setLineWidth(1);   // Set the line width

        // The lines are about 20 pixels tall and about 5 pixels apart.
        int yPosStart = yPos;
        int yPosEnd = yPos + 20;

        // Draw the line:
        g.strokeLine(xPos, yPosStart, xPos, yPosEnd);
    }
}
