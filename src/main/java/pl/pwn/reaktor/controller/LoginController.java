package pl.pwn.reaktor.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;
import pl.pwn.reaktor.service.UserService;


public class LoginController {

    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField psf_pass;

    @FXML
    private Button btn_logIn;

    @FXML
    private Button btn_register;

    @FXML
    private Button btn_restore;

    @FXML
    void LogInAction(MouseEvent event) throws IOException {
    	String mail = tf_login.getText();
    	String password = psf_pass.getText();
    	boolean isLog = userService.login(mail, password);
    	
    	if(isLog) {
    		Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
    		Scene scene = new Scene(parent);
    		Main.getPrimaryStage().setScene(scene);
    	}else {
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Błąd logowania");
    		error.setContentText("Spróbuj jeszcze raz");
    		error.setHeaderText("Podano błędny e-mail lub hasło");
    		error.show();
    	}
    
    }

    @FXML
    void RegisterAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/RegisterView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void RestorePassAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/RestoreView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }
    
    private UserService userService;
    public void initialize() {
    	userService = new UserService();
    }

}
