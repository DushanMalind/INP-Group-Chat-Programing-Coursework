package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFromController {

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
