package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client3FromController {

    public Button btnsendImage;
    public Button btnsendemogi;
    @FXML
    private JFXButton btnNewSend;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea txtAreaClient3;

    @FXML
    private TextField txtFiledClient3;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;

    String message="";
    String reply="";

    public void initialize(){
        new Thread(() -> {
            try {
                socket=new Socket("localhost",4000);

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    txtAreaClient3.appendText("\n"+message.trim());



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
            dataOutputStream.writeUTF(txtFiledClient3.getText().trim());
            reply=txtFiledClient3.getText();
            txtAreaClient3.setStyle("-fx-text-fill: blue;-fx-border-color: #FF0000;-fx-font-size: 20;");


            txtAreaClient3.appendText("\n\t\t\t\t\t\t\t\t\t\t\tClient3 :"+reply);
            dataOutputStream.flush();
            txtFiledClient3.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void imgOnActon(ActionEvent event) {
    }

    public void emogiOnActon(ActionEvent event) {
        byte[] emojiByteCode = new byte[]{(byte)0xF0,(byte)0x9F, (byte)0x98, (byte)0x81};
        String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
        txtFiledClient3.appendText("\uD83D\uDE07");
        txtFiledClient3.appendText("\uD83E\uDD2D");
        txtFiledClient3.appendText("\uD83D\uDE34");
        txtFiledClient3.setText(txtFiledClient3.getText() + " "+ emoji);

    }
}
