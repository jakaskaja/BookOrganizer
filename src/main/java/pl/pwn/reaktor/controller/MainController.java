package pl.pwn.reaktor.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;

public class MainController {

    @FXML
    private Button btn_all;

    @FXML
    private Button btn_add;

    @FXML
    void AllBooksAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/AllBooksView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void addAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/AddBookView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void endAction(MouseEvent event) {
    	System.exit(0);
    }

}
