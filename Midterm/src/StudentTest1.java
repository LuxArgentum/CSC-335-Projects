import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StudentTest1 extends Application {

    private BorderPane window;
    private int width = 500;
    private int height = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Student Test");
        window = new BorderPane();
        Scene scene = new Scene(window, width, height);

        stage.setScene(scene);
        stage.show();
    }
}
