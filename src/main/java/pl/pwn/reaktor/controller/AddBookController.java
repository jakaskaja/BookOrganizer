package pl.pwn.reaktor.controller;

import java.io.IOException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.Main;
import pl.pwn.reaktor.model.Book;
import pl.pwn.reaktor.service.BookService;

public class AddBookController {

	@FXML
	private ImageView img_end;

	@FXML
	private TextField tf_author;

	@FXML
	private TextField tf_title;

	@FXML
	private ComboBox<String> cmb_type;

	@FXML
	private ComboBox<String> cmb_rate;

	@FXML
	private TextField tf_link;

	@FXML
	private ComboBox<String> cmb_status;

	@FXML
	private TextArea ta_description;

	@FXML
	private Button btn_add;

	@FXML
	private Button btn_addFromFile;

	@FXML
	private Button btn_back;

	@FXML
	private Button btn_clear;

	@FXML
	private Button btn_all;

	@FXML
	void AddBookAction(MouseEvent event) {
		if (isNotCompleted()) {
			showAlertNotCompleted();
		} else {
			BookService bookService = new BookService();
			Book book = addNew();
			bookService.addNewBook(book);
		}
	}

	@FXML
	void AddFromFileAction(MouseEvent event) {
		// dodawanie z pliku
	}

	@FXML
	void AllBooksAction(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/view/AllBooksView.fxml"));
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
		clearForm();
	}

	@FXML
	void endAction(MouseEvent event) {
		System.exit(0);
	}

	private boolean isNotCompleted() {
		return "".equals(tf_author.getText()) || "".equals(tf_title.getText()) || "".equals(tf_author.getText())
				|| Objects.isNull(cmb_type.getValue()) || Objects.isNull(cmb_rate.getValue())
				|| Objects.isNull(cmb_status.getValue());
	}

	private void showAlertNotCompleted() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Brak danych");
		alert.setHeaderText("Nie uzupełniono wszystkich pól formularza");
		alert.setContentText("Uzupełnij puste pola");
		alert.show();
	}

	private Book addNew() {
		String author = tf_author.getText();
		String title = tf_title.getText();
		String type = cmb_type.getValue();
		String rate = cmb_rate.getValue();
		String link = tf_link.getText();
		String status = cmb_status.getValue();
		String description = ta_description.getText();

		clearForm();
		alertConfirmation();
		return new Book(author, title, type, description, rate, link, status);
	}

	private void clearForm() {
		tf_author.clear();
		tf_title.clear();
		cmb_type.setValue(null);
		cmb_rate.setValue(null);
		tf_link.clear();
		cmb_status.setValue(null);
		ta_description.clear();
	}

	private void alertConfirmation() {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("Dodawanie książki");
		alertConfirm.setHeaderText("Sukces!");
		alertConfirm.setContentText("Pomyślnie dodano nową książkę");
		alertConfirm.show();
	}

	ObservableList<String> typeList = FXCollections.observableArrayList("biografia", "fantastyka", "poezja",
			"thriller", "sensacja", "kryminał", "lit. obyczajowa", "reportaż");
	ObservableList<String> rateList = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	ObservableList<String> statusList = FXCollections.observableArrayList("do przeczytania",
			"w trakcie czytania", "przeczytana");
	
	public void initialize() {
		cmb_type.setItems(typeList);
		cmb_rate.setItems(rateList);
		cmb_status.setItems(statusList);
	}
}
