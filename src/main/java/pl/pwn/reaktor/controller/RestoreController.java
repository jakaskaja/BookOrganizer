package pl.pwn.reaktor.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;

public class RestoreController {

	@FXML
    private ImageView img_end;
	
    @FXML
    private TextField tf_mail;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_resetMail;

    @FXML
    void BackAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void ResetMailAction(MouseEvent event) {
    	//wysyłanie maila z hasłem na wskazany adres
    }

	private void alertEmptyTextField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Brak danych");
		alert.setHeaderText("Nie podano adresu e-mail");
		alert.setContentText("Uzupełnij formularz");
		alert.show();
	}
    
    @FXML
    void endAction(MouseEvent event) {
    	System.exit(0);
    }
}
