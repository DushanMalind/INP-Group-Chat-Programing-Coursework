package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class ServerFromController{

    @FXML
    private JFXButton btnNewSend;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea txtAreaServer;

    @FXML
    private TextField txtFiledServer;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    ServerSocket serverSocket2;
    Socket socket2;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;


    ServerSocket serverSocket3;
    Socket socket3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;


    public VBox vbox;
    PrintWriter writer;
    private File file;
    BufferedReader reader;

    String message="";
    String message2="";
    String message3="";
    String replay="";

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket=new ServerSocket(3001);
                txtAreaServer.appendText("Server Start");
                socket=serverSocket.accept();
                txtAreaServer.appendText("\nClientOne Start");
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());


                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    txtAreaServer.appendText("\nClient1: "+message.trim());

                    dataOutputStream2.writeUTF("Client1 :"+message.trim());
                    dataOutputStream3.writeUTF("Client1 :"+message.trim());

                    dataOutputStream2.flush();
                    dataOutputStream3.flush();

                   /* InputStream inputStream=socket.getInputStream();
                    int imageData = inputStream.read();
                    FileOutputStream fileOutputStream = new FileOutputStream("received_image.jpg");
                    fileOutputStream.write(imageData);
                    fileOutputStream.close();*/


                }
               /* dataInputStream.close();
                dataOutputStream.close();*/


            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                serverSocket2=new ServerSocket(3060);
                txtAreaServer.appendText("Server Start");
                socket2=serverSocket2.accept();
                txtAreaServer.appendText("\nClient2 Start");
                dataInputStream2=new DataInputStream(socket2.getInputStream());
                dataOutputStream2=new DataOutputStream(socket2.getOutputStream());



                while (!message2.equalsIgnoreCase("Finish")){
                    message2=dataInputStream2.readUTF();
                    txtAreaServer.appendText("\nClient2: "+message2.trim());

                    dataOutputStream.writeUTF("Client2 :"+message2.trim());
                    dataOutputStream3.writeUTF("Client2 :"+message2.trim());

                    dataOutputStream.flush();
                    dataOutputStream3.flush();


                }
                /*dataInputStream2.close();
                dataOutputStream2.close();*/


            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                serverSocket3=new ServerSocket(4000);
                txtAreaServer.appendText("Server Start");
                socket3=serverSocket3.accept();
                txtAreaServer.appendText("\nClient3 Start");
                dataInputStream3=new DataInputStream(socket3.getInputStream());
                dataOutputStream3=new DataOutputStream(socket3.getOutputStream());



                while (!message3.equalsIgnoreCase("Finish")){
                    message3=dataInputStream3.readUTF();
                    txtAreaServer.appendText("\nClient3: "+message3.trim());

                    dataOutputStream.writeUTF("Client3 :"+message3.trim());
                    dataOutputStream2.writeUTF("Client3 :"+message3.trim());

                    dataOutputStream.flush();
                    dataOutputStream2.flush();


                }
                dataInputStream3.close();
                dataOutputStream3.close();


            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();



       /* new Thread(() -> {

        });

        try {
            // Create a server socket to listen for incoming connections
            serverSocket = new ServerSocket(3006);

            // Wait for a client to connect
            socket = serverSocket.accept();

            // Create a data input stream to receive the image data
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            // Read the image data and save it to a file
            byte[] imageData = new byte[inputStream.available()];
            inputStream.readFully(imageData);
            FileOutputStream outputStream = new FileOutputStream("received_image.jpg");
            outputStream.write(imageData);
            outputStream.close();

            // Display the received image in a JavaFX window
            Image image = new Image("file:received_image.jpg");
            ImageView imageView = new ImageView(image);
            StackPane root = new StackPane(imageView);


            // Close the server socket
            serverSocket.close();
        } catch (  IOException e) {
            e.printStackTrace();
        }
*/

    }

    @FXML
    void btnSend(ActionEvent event) {
        try {

            dataOutputStream.writeUTF("Server :"+txtFiledServer.getText().trim());
            dataOutputStream.flush();

            dataOutputStream2.writeUTF("Server :"+txtFiledServer.getText().trim());
            dataOutputStream2.flush();

            dataOutputStream3.writeUTF("Server :"+txtFiledServer.getText().trim());
            dataOutputStream3.flush();

            /*String read=txtFiledServer.getText();
            txtAreaServer.appendText("\tServer:"+read.trim());
            dataOutputStream.writeUTF(read);
            dataOutputStream.flush();*/

            /*String read1=txtFiledServer.getText();
            txtAreaServer.appendText("\tServer:"+read1.trim());
            dataOutputStream2.writeUTF(read1);
            dataOutputStream2.flush();*/

            txtFiledServer.clear();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
