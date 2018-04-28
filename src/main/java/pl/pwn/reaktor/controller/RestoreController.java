package pl.pwn.reaktor.controller;

import java.io.IOException;

import javax.mail.MessagingException;

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
import pl.pwn.reaktor.mail.MailService;
import pl.pwn.reaktor.service.LoginService;

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
    	goToLoginView();
    }

	private void goToLoginView() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
	}

    @FXML
    void ResetMailAction(MouseEvent event) throws IOException {
    	String mail = tf_mail.getText();
    	String pass = loginService.getUserPass(mail);
    	if("".equals(mail) || "".equals(pass)) {
    		alertEmptyTextField();
    	}else {
    		MailService mailService = new MailService();
    		try {
				mailService.sendMail(mail, "Przypomnienie hasła", "Wysłano prośbę o zmianę hasła. Twoje hasło to: "+pass);
				tf_mail.clear();
				alertConfirmation();
				goToLoginView();
				
			} catch (MessagingException e) {
				Alert errorSendMail = new Alert(AlertType.ERROR);
				errorSendMail.setTitle("Nie udało się wyslać maila");
				errorSendMail.setHeaderText("Błąd");
				errorSendMail.setContentText(e.getMessage());
				errorSendMail.show();
			}
    	}
    	
    }

	private void alertEmptyTextField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Błędny mail");
		alert.setHeaderText("Brak użytkownika o takim adresie e-mail w bazie");
		alert.setContentText("Podaj poprawny mail");
		alert.show();
	}
    
    @FXML
    void endAction(MouseEvent event) {
    	System.exit(0);
    }
    
    private void alertConfirmation() {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("Przypomnienie hasła");
		alertConfirm.setHeaderText("Sukces!");
		alertConfirm.setContentText("Twoje hasło zostało wysłane na adres: "+tf_mail.getText());
		alertConfirm.show();
	}
    
    private LoginService loginService;
    public void initialize() {
    	loginService = new LoginService();
    }
}
