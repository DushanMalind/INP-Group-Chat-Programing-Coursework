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

    String message="";
    String replay="";

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket=new ServerSocket(3001);
                txtAreaServer.appendText("Server Start");
                socket=serverSocket.accept();
                txtAreaServer.appendText("\nClient Start");
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());



                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    txtAreaServer.appendText("\nClient: "+message.trim()+"\n"+"\r");


                }
                dataInputStream.close();
                dataOutputStream.close();


            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    void btnSend(ActionEvent event) {
        try {
            String read=txtFiledServer.getText();
            txtAreaServer.appendText("\tServer:"+read.trim());
            dataOutputStream.writeUTF(read);
            txtFiledServer.clear();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
