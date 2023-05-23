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
    String reply="";

    public void initialize(){
        new Thread(() -> {
            try {
                socket=new Socket("localhost",3060);

                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                dataInputStream=new DataInputStream(socket.getInputStream());


                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    txtAreaClient2.appendText("\n"+message.trim());



                }
                /*dataInputStream.close();
                dataOutputStream.close();*/


            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
    }

    @FXML
    void btnSend(ActionEvent event) {
        try {
            dataOutputStream.writeUTF(txtFiledClient12.getText().trim());
            reply=txtFiledClient12.getText();
            txtAreaClient2.setStyle("-fx-text-fill: blue;-fx-border-color: #FF0000;-fx-font-size: 20;");
            txtAreaClient2.appendText("\n\t\t\t\t\t\t\t\t\t\t\tClient2 :"+reply);
            dataOutputStream.flush();
            txtFiledClient12.clear();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
