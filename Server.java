import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket socket = null;
        Socket acceptedConnection = null;
        Scanner scanner = null;
        try {
            socket = new ServerSocket(1234);
            System.out.println("Welcome to my echo server!");
            acceptedConnection = socket.accept();
            InputStream in = acceptedConnection.getInputStream();
            OutputStream out = acceptedConnection.getOutputStream();
            scanner = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            
            while(true) {
                String message = scanner.nextLine();
                System.out.println("Received: " + message);
                if(message.equals("exit"))  {
                    writer.println("Thanks for using my echo server!");
                    socket.close();
                    break;
                }
                System.out.println("Sending back...");
                writer.println(message);
            }
        } catch(NoSuchElementException e) {
            System.out.println("Connection with client has been closed unexpectdly.");
        }catch (IOException e) {
            System.out.println("Connection couldn't be made... Force closing");
        } finally {
            try {
            if(!socket.isClosed()) socket.close();
            scanner.close();
            } catch(IOException e) {
                System.out.println("Couldn't free resources...");
            }
        } 
    }
    
    
}
