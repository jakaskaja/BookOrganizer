package pl.pwn.reaktor.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;

public class RegisterController {

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_last;

    @FXML
    private TextField tf_mail;

    @FXML
    private PasswordField ps_pass;

    @FXML
    private PasswordField ps_pass2;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_createAcc;

    @FXML
    private Button btn_clear;
    
    @FXML
    void BackAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void ClearAction(MouseEvent event) {
    	tf_name.clear();
    	tf_last.clear();
    	tf_mail.clear();
    	ps_pass.clear();
    	ps_pass2.clear();
    }

    @FXML
    void CreateAction(MouseEvent event) {

    }

}
