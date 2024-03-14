import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Server is running...");
        ServerSocket serverSocket = new ServerSocket(4000);
        Socket clientConnection = serverSocket.accept();
        System.out.println("This Server just got a Client at port 4000");

        ObjectInputStream inputStream = new ObjectInputStream(clientConnection.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(clientConnection.getOutputStream());

        double sum = 0;
        double inputNumber;

        while ((inputNumber = (double) inputStream.readObject()) != -1) {
            sum += inputNumber;
        }

        outputStream.writeObject(sum);
        inputStream.close();
        outputStream.close();
        serverSocket.close();
    }
}
