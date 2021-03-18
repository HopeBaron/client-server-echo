import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        Scanner userScanner = null;
        Socket socket = null;
        Scanner inputScanner = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 1234);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
             userScanner = new Scanner(System.in);
            PrintWriter writer = new PrintWriter(out, true);
            inputScanner = new Scanner(in);
           
           
            System.out.println("Connection established successfully");
            String message = null;
            while(!"exit".equals(message)) {
                System.out.print("> ");
                message = userScanner.nextLine();
                writer.println(message);
                String response = inputScanner.nextLine();
                System.out.println("#> " + response);
            }
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Distination couldn't be resolved.");
        } catch (IOException e) {
            System.out.println("Unable to connect.");
        } catch(NoSuchElementException e) {
            System.out.println("Connection interrupted unexpectdly.");
        } finally {
            try {
            userScanner.close();
            inputScanner.close();
            if(!socket.isClosed()) socket.close();
            } catch(IOException e) {
                System.out.println("Couldn't free resources");
            }
        }
    }
}