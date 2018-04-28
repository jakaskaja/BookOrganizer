package pl.pwn.reaktor.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import pl.pwn.reaktor.Main;
import pl.pwn.reaktor.model.Book;
import pl.pwn.reaktor.model.BookFilter;
import pl.pwn.reaktor.service.BookService;

public class AllBooksController {

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
    private ComboBox<String> cmb_status;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_clear;

    @FXML
    private TableView<Book> table_books;

    @FXML
    private TableColumn<Book, Integer> col_id;

    @FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, String> col_title;

    @FXML
    private TableColumn<Book, String> col_type;

    @FXML
    private TableColumn<Book, String> col_description;

    @FXML
    private TableColumn<Book, String> col_rate;

    @FXML
    private TableColumn<Book, String> col_link;

    @FXML
    private TableColumn<Book, String> col_status;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

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
    void DeleteAction(MouseEvent event) {
    	if (Objects.isNull(table_books.getSelectionModel())
				|| Objects.isNull(table_books.getSelectionModel().getSelectedItem())) {

			Alert error = new Alert(AlertType.ERROR);
			error.setHeaderText("Błąd");
			error.setContentText("Wybierz książkę do usunięcia");
			error.setTitle("Nie wybrano książki");
			error.show();
			return;
		}
    	int id = table_books.getSelectionModel().getSelectedItem().getId();
    	bookService.delete(id);
    	
    	fillTableData();
    }

    @FXML
    void SearchAction(MouseEvent event) {
    	String authorValue = tf_author.getText();
    	String titleValue = tf_title.getText();
    	String rateValue = cmb_rate.getValue();
    	String statusValue = cmb_status.getValue();
    	String typeValue = cmb_type.getValue();
    	
    	BookFilter filter = new BookFilter(authorValue, titleValue, typeValue, rateValue, statusValue);
    	List<Book> bookList = bookService.bookFilter(filter);
    	
    	ObservableList<Book> bookData = FXCollections.observableArrayList(bookList);
    	table_books.setItems(null);
    	table_books.setItems(bookData);
    }

    @FXML
    void endAction(MouseEvent event) {
    	System.exit(0);
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
		
		fillTableData();
		setCellValue();
		editCells();
		
		table_books.setEditable(true);
	}
	private void editCells() {
		editAuthorCell();
		editTitleCell();
		editLinkCell();
		editDescriptionCell();
		editRateCell();
		editTypeCell();
		editStatusCell();
	}

	private void editRateCell() {
		col_rate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				Book book = param.getValue();
				
				String rate = book.getRate();
				return new SimpleObjectProperty<String>(rate);
			}
		});
		
		col_rate.setCellFactory(ComboBoxTableCell.forTableColumn(rateList));
		col_rate.setOnEditCommit((CellEditEvent<Book, String> event) -> {
			TablePosition<Book, String> pos = event.getTablePosition();

			String newRate = event.getNewValue();
			int row = pos.getRow();
			Book book = event.getTableView().getItems().get(row);
			book.setRate(newRate);
			updateCell(book);
		});
	}
	
	private void editTypeCell() {
		col_type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				Book book = param.getValue();
				
				String type = book.getType();
				return new SimpleObjectProperty<String>(type);
			}
		});
		
		col_type.setCellFactory(ComboBoxTableCell.forTableColumn(typeList));
		col_type.setOnEditCommit((CellEditEvent<Book, String> event) -> {
			TablePosition<Book, String> pos = event.getTablePosition();

			String newType = event.getNewValue();
			int row = pos.getRow();
			Book book = event.getTableView().getItems().get(row);
			book.setType(newType);
			updateCell(book);
		});
	}
	
	private void editStatusCell() {
		col_status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				Book book = param.getValue();
				
				String status = book.getStatus();
				return new SimpleObjectProperty<String>(status);
			}
		});
		
		col_status.setCellFactory(ComboBoxTableCell.forTableColumn(statusList));
		col_status.setOnEditCommit((CellEditEvent<Book, String> event) -> {
			TablePosition<Book, String> pos = event.getTablePosition();

			String newStatus = event.getNewValue();
			int row = pos.getRow();
			Book book = event.getTableView().getItems().get(row);
			book.setStatus(newStatus);
			updateCell(book);
		});
	}


	private void editAuthorCell() {
		col_author.setCellFactory(TextFieldTableCell.forTableColumn());
		col_author.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book,String>>() {

			@Override
			public void handle(CellEditEvent<Book, String> e) {
				((Book)e.getTableView().getItems().get(e.getTablePosition().getRow())).setAuthor(e.getNewValue());
				
				Book selectedBook = table_books.getSelectionModel().getSelectedItem();
				updateCell(selectedBook);
				}	
		});
	}
	
	private void editTitleCell() {
		col_title.setCellFactory(TextFieldTableCell.forTableColumn());
		col_title.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book,String>>() {

			@Override
			public void handle(CellEditEvent<Book, String> e) {
				((Book)e.getTableView().getItems().get(e.getTablePosition().getRow())).setTitle(e.getNewValue());
				
				Book selectedBook = table_books.getSelectionModel().getSelectedItem();
				updateCell(selectedBook);
				}	
		});
	}

	private void editLinkCell() {
		col_link.setCellFactory(TextFieldTableCell.forTableColumn());
		col_link.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book,String>>() {

			@Override
			public void handle(CellEditEvent<Book, String> e) {
				((Book)e.getTableView().getItems().get(e.getTablePosition().getRow())).setLink(e.getNewValue());
				
				Book selectedBook = table_books.getSelectionModel().getSelectedItem();
				updateCell(selectedBook);
				}	
		});
	}
	
	private void editDescriptionCell() {
		col_description.setCellFactory(TextFieldTableCell.forTableColumn());
		col_description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book,String>>() {

			@Override
			public void handle(CellEditEvent<Book, String> e) {
				((Book)e.getTableView().getItems().get(e.getTablePosition().getRow())).setDescription(e.getNewValue());
				
				Book selectedBook = table_books.getSelectionModel().getSelectedItem();
				updateCell(selectedBook);
				}	
		});
	}


	BookService bookService;
	private void fillTableData() {
    	bookService = new BookService();
    	List<Book> books = bookService.getAll();
    	ObservableList<Book> data = FXCollections.observableArrayList(books);
    	table_books.setItems(null);
    	table_books.setItems(data);
    }
	
	private void setCellValue() {
		col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		col_author.setCellValueFactory(new PropertyValueFactory<>("Author"));
		col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
		col_link.setCellValueFactory(new PropertyValueFactory<>("Link"));
		col_rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("Status"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
		col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
		
	}
	private void updateCell(Book selectedBook) {
		bookService.update(selectedBook);
	}
}
