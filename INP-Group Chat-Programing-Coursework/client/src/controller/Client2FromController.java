package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client2FromController {

    public JFXButton btnNewhandel;
    public JFXButton btnNewhandelMax;
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
            txtAreaClient2.setStyle("-fx-text-fill: green;-fx-border-color: #FF0000;-fx-font-size: 20;");
            txtAreaClient2.setWrapText(true);
            txtAreaClient2.appendText("\n\t\t\t\t\t\t\t\t\t\t\tClient2 :"+reply);
            dataOutputStream.flush();
            txtFiledClient12.clear();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void imgOnActon(ActionEvent event) {
    }

    public void emogiOnActon(ActionEvent event) {
        byte[] emojiByteCode = new byte[]{(byte)0xF0,(byte)0x9F, (byte)0x98, (byte)0x81};
        String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
        txtFiledClient12.appendText("\uD83D\uDE07");
        txtFiledClient12.appendText("\uD83E\uDD2D");
        txtFiledClient12.appendText("\uD83D\uDE34");
        txtFiledClient12.setText(txtFiledClient12.getText() + " "+ emoji);
    }

    Stage stage=null;

    public void btnOnAction(ActionEvent event) {
        stage=(Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    public void btnOnActionMax(ActionEvent event) {
        stage=(Stage) root.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(false);
        }else {
            stage.setMaximized(true);
        }
    }
}
