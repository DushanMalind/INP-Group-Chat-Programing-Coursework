package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client2FromController {

    @FXML
    private JFXButton btnNewSend;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea txtAreaClient2;

    @FXML
    private TextField txtFiledClient12;



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
                    txtAreaClient2.appendText("\nServer: "+message.trim()+"\n");



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
            String read=txtFiledClient12.getText();
            txtAreaClient2.appendText("\tClient1 :"+read.trim());
            dataOutputStream.writeUTF(read);
            txtFiledClient12.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
