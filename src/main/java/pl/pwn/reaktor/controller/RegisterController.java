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
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;
import pl.pwn.reaktor.model.User;
import pl.pwn.reaktor.service.UserService;

public class RegisterController {

	@FXML
    private ImageView img_end;
	
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
    	clearForm();
    }

    @FXML
    void CreateAction(MouseEvent event) {
    	if (isNotCompleted()) {
    		showAlertNotCompleted();
    	}else if (!(ps_pass.getText().equals(ps_pass2.getText()))){
    		alertWrongPass();
    		ps_pass.clear();
    		ps_pass2.clear();
    		}else {	
    	UserService userService = new UserService();
    	User user = createUser();
    	userService.saveUser(user);
    	}
    }
    @FXML
    void endAction(MouseEvent event) {
    	System.exit(0);
    }

	private void showAlertNotCompleted() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Brak danych");
		alert.setHeaderText("Nie uzupełniono wszystkich pól formularza");
		alert.setContentText("Uzupełnij puste pola");
		alert.show();
	}
    
    private boolean isNotCompleted() {
    	return "".equals(tf_name.getText()) 
    			|| "".equals(tf_last.getText()) 
    			|| "".equals(tf_mail.getText()) 
    			|| "".equals(ps_pass.getText()) 
    			|| "".equals(ps_pass2.getText());
    }
    
    private void clearForm() {
		tf_name.clear();
    	tf_last.clear();
    	tf_mail.clear();
    	ps_pass.clear();
    	ps_pass2.clear();
	}
    
    private User createUser() {
    	String name = tf_name.getText();
    	String last = tf_last.getText();
    	String mail = tf_mail.getText();
    	String pass = ps_pass.getText();
    		clearForm();
        	alertConfirmation();
        	return new User(mail, name, last, pass, 2);
    }
    
    private void alertWrongPass() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Błędne hasło");
		alert.setHeaderText("Podane hasła nie są jednakowe");
		alert.setContentText("Ponownie wpisz hasło");
		alert.show();
	}

	private void alertConfirmation() {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("Dodawanie użytkownika");
		alertConfirm.setHeaderText("Sukces!");
		alertConfirm.setContentText("Pomyślnie dodano nowego użytkownika");
		alertConfirm.show();
	}

}
