package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client1FromController {

    @FXML
    private JFXButton btnNewSend;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea txtAreaClient1;

    @FXML
    private TextField txtFiledClient1;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;

    String message="";

    public void initialize(){
        new Thread(() -> {
            try {
                socket=new Socket("localhost",3001);

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    txtAreaClient1.appendText("\nServer: "+message.trim()+"\n");



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
            String read=txtFiledClient1.getText();
            txtAreaClient1.appendText("\tClient1 :"+read.trim());
            dataOutputStream.writeUTF(read);
            txtFiledClient1.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
