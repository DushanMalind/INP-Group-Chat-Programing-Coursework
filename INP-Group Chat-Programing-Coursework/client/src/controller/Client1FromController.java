package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Base64;

public class Client1FromController extends Thread{

    public Button btnsendImage;
    public Button btnsendemogi;
    public Label labIomj;
    public ImageView firstEMO;
    public JFXButton btnNewhandel;
    public JFXButton btnNewhandelMax;
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
    public VBox vbox;
    PrintWriter writer;
    private File file;
    BufferedReader reader;

    String message="";
    String reply="";



    public void initialize(){
        new Thread(() -> {
            try {
                socket=new Socket("localhost",3001);

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                while (!message.equalsIgnoreCase("Finish")){
                    message=dataInputStream.readUTF();
                    txtAreaClient1.appendText("\n"+message.trim());



                }
               /* dataInputStream.close();
                dataOutputStream.close();*/


            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();

        StringBuilder fulmsg = new StringBuilder();
        HBox box = new HBox();


      /*  new Thread(() -> {


        }).start();*/
    }

    @FXML
    void btnSend(ActionEvent event) {
        try {

            dataOutputStream.writeUTF(txtFiledClient1.getText().trim());
            reply=txtFiledClient1.getText();
            txtAreaClient1.setStyle("-fx-text-fill: blue;-fx-border-color: #FF0000;-fx-font-size: 20;-fx-background-radius: 20px;-fx-font-size: 20px; -fx-border-color: darkslateblue;-fx-border-radius: 20px;-fx-border-width: 2px;-fx-text-alignment: center");

            txtAreaClient1.setWrapText(true);

            /* textFlow.setStyle("-fx-background-color: darkturquoise; -fx-text-fill: white; -fx-background-radius: 20px;-fx-font-size: 20px; -fx-border-color: darkslateblue;-fx-border-radius: 20px;-fx-border-width: 2px;-fx-text-alignment: center");*/
            txtAreaClient1.appendText("\n\t\t\t\t\t\t\t\t\t\t\tClient1 :"+reply);
            dataOutputStream.flush();
            txtFiledClient1.clear();

            /*String read=txtFiledClient1.getText();
            txtAreaClient1.appendText("\tClient1 :"+read.trim());
            dataOutputStream.writeUTF(read);
            txtFiledClient1.clear();*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /*public void imgOnActon(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(btnsendImage.getScene().getWindow());
        if (selectedFile != null) {
            try {

                byte[] imageData = new byte[(int) selectedFile.length()];
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                fileInputStream.read(imageData);
                fileInputStream.close();

                 socket = new Socket("localhost", 3001);

                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(imageData);
                outputStream.flush();

                socket.close();

                System.out.println("Image sent successfully.");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(txtAreaClient1.getText() + " :  ")) {
                    continue;
                }else if(fulmsg.toString().equalsIgnoreCase("Bye")) {
                    break;
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HBox box = new HBox();

                        if (fulmsg.toString().endsWith(".png") || fulmsg.toString().endsWith(".jpg")){
                            ImageView imageView = new ImageView();
                            imageView.setImage(new Image(new File(String.valueOf(fulmsg)).toURI().toString()));
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            Text text = new Text(txtAreaClient1.getText() + " : ");
                            box.setAlignment(Pos.TOP_LEFT);
                            box.getChildren().add(text);
                            vbox.getChildren().add(box);
                            vbox.getChildren().add(imageView);
                        }/*else {
                            Text text = new Text(msg + "\n");
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-background-color: darkturquoise; -fx-text-fill: white; -fx-background-radius: 20px;-fx-font-size: 20px; -fx-border-color: darkslateblue;-fx-border-radius: 20px;-fx-border-width: 2px;-fx-text-alignment: center");
                            box.setAlignment(Pos.CENTER_LEFT);
                            box.getChildren().add(textFlow);
                            vbox.getChildren().add(box);
                        }*/
                    }
                });

            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imgOnActon(ActionEvent event) {

       /* FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(btnsendImage.getScene().getWindow());
       */

      /*  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        file = fileChooser.showOpenDialog(stage);

        if (file != null){
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Text text = new Text("Me : ");


            try {
                writer.println(txtAreaClient1.getText() + " : " + file.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            writer.flush();
        }*/

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(btnsendImage.getScene().getWindow());

        if (selectedFile != null) {
            try {
                // Read the image file and convert it into bytes
                byte[] imageData = new byte[(int) selectedFile.length()];
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                fileInputStream.read(imageData);
                fileInputStream.close();
                 socket = new Socket("localhost", 3001);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(imageData);
                outputStream.flush();
                socket.close();

                System.out.println("Image sent successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void emogiOnActon(ActionEvent event) {
        byte[] emojiByteCode = new byte[]{(byte)0xF0,(byte)0x9F, (byte)0x98, (byte)0x81};
        String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
        txtFiledClient1.appendText("\uD83D\uDE07");
        txtFiledClient1.appendText("\uD83E\uDD2D");
        txtFiledClient1.appendText("\uD83D\uDE34");
        txtFiledClient1.setText(txtFiledClient1.getText() + " "+ emoji);
    }

    Stage stage=null;

    public void btnOnAction(ActionEvent event) {
       /*start();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);*/
        stage=(Stage) root.getScene().getWindow();
        stage.setIconified(true);

    }

    public void start(Stage stage) {
        //Maximized
        stage.setMaximized(true);

        //Restore down
        stage.setMaximized(false);
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
