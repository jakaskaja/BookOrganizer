package pl.pwn.reaktor.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;

public class AllBooksController {

    @FXML
    private TextField tf_author;

    @FXML
    private TextField tf_title;

    @FXML
    private ComboBox<String> cmb_type;

    @FXML
    private ComboBox<Integer> cmb_rate;

    @FXML
    private ComboBox<String> cmb_status;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_clear;

    @FXML
    private TableView<Book> table_books;

    @FXML
    private TableColumn<Book, Long> col_id;

    @FXML
    private TableColumn<?, ?> col_author;

    @FXML
    private TableColumn<?, ?> col_title;

    @FXML
    private TableColumn<?, ?> col_type;

    @FXML
    private TableColumn<?, ?> col_description;

    @FXML
    private TableColumn<?, ?> col_rate;

    @FXML
    private TableColumn<?, ?> col_link;

    @FXML
    private TableColumn<?, ?> col_status;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_exit;

    @FXML
    void AddAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/AddBookView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void BackAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void ClearAction(MouseEvent event) {
    	tf_author.clear();
    	tf_title.clear();
    	cmb_rate.getSelectionModel().clearSelection();
    	cmb_status.getSelectionModel().clearSelection();
    	cmb_type.getSelectionModel().clearSelection();
    }

    @FXML
    void ExitAction(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void SearchAction(MouseEvent event) {

    }

}
