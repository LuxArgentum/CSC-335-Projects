import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class BankAccountFXGUI extends Application {


    private BankAccount account = new BankAccount("Chris", 100);
    private Label accountID = new Label(account.getID() + "'s balance:");
    private Label balance = new Label(account.toString());
    private TextField input = new TextField();
    private Button withdraw = new Button("Withdraw");
    private Button deposit = new Button("Deposit");
    private GridPane pane;

    public static void main(String[] args) {

        launch(args);

    }

    @Override

    public void start(Stage stage) {

        layoutGUI();

        Scene scene = new Scene(pane, 190, 200);

        stage.setScene(scene);

        stage.show();

    }


    private void layoutGUI() {

        pane = new GridPane();

        pane.setHgap(10);

        pane.setVgap(10);

        pane.setPadding(new Insets(10, 10, 10, 10));

        // accountID
        pane.add(accountID, 0, 0);
        // balance
        pane.add(balance, 0, 1);
        // input
        pane.add(input, 0, 2);
        // withdrawn
        pane.add(withdraw, 0, 3);
        // deposit
        pane.add(deposit, 0, 4);

        // input event handler
        input.setOnAction(e -> {
            account.deposit(Double.parseDouble(input.getText()));
            balance.setText(account.toString());
        });

        // withdrawn event handler
        withdraw.setOnAction(e -> {
            account.withdraw(Double.parseDouble(input.getText()));
            balance.setText(account.toString());
        });

        //deposit event handler
        deposit.setOnAction(e -> {
            account.deposit(Double.parseDouble(input.getText()));
            balance.setText(account.toString());
        });
    }

}  // End class