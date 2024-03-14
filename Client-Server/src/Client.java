import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        System.out.println("Client started");
        Socket server = new Socket("localhost", 4000);
//        System.out.println("This Client found a server on port 4000");
        ObjectOutputStream outputStream = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(server.getInputStream());

        Scanner scanner = new Scanner(System.in);
        double inputNumber = 0;

        while (inputNumber != -1) {
            System.out.print("Enter a number or -1 to quit: ");
            inputNumber = scanner.nextDouble();
            outputStream.writeObject(inputNumber);
        }

        double sum = (double) inputStream.readObject();
        System.out.println("Sum: " + sum);

        outputStream.close();
        inputStream.close();
        server.close();
    }
}
