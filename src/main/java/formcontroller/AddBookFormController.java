package formcontroller;



import controller.BookController;
import dbconnection.DBConnection;
import model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddBookFormController {
    BookController bookController=new BookController();

    @FXML
    private TableColumn<?, ?> Colum_Lanvage;

    @FXML
    private TableColumn<?, ?> Colum_isbn;

    @FXML
    private TextField Txt_title;

    @FXML
    private TableColumn<?, ?> colum_Author;

    @FXML
    private TableColumn<?, ?> colum_booksid;

    @FXML
    private TableColumn<?, ?> colum_tiittle;

    @FXML
    private TableView<Book> tabel_Books;

    @FXML
    private TextField txt_Author;

    @FXML
    private TextField txt_Bookid;

    @FXML
    private TextField txt_Isbn;

    @FXML
    private TextField txt_lanvage;

    @FXML
    void btn_Add_Action(ActionEvent event) {
        bookController.Addbook(new Book(
                txt_Bookid.getText(),
                Txt_title.getText(),
                txt_Author.getText(),
                txt_Isbn.getText(),
                txt_lanvage.getText()
        ));

    }

    @FXML
    void btn_Delete_Action(ActionEvent event) {
        Connection connection= DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("Delete from Books  where BookID =?");

            preparedStatement.setString(1,txt_Bookid.getText());

            if (0 < preparedStatement.executeUpdate()){
                new Alert(Alert.AlertType.INFORMATION,"Deleted successfully").show();

            }else {
                new  Alert(Alert.AlertType.INFORMATION,"Could not be deleted.").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btn_Search_Action(ActionEvent event) {
        Connection connection=DBConnection.getInstance().getConnection();
        Book book = bookController.SearcBooks(txt_Bookid.getText());

        if (book != null) {
        }
            Txt_title.setText(String.valueOf(book.getTiitle()));
            txt_Author.setText(String.valueOf(book.getAuthor()));
            txt_Isbn.setText(String.valueOf(book.getIsbn()));
            txt_lanvage.setText(String.valueOf(book.getLanvage()));


    }

    @FXML
    void btn_View_Action(ActionEvent event) {
        colum_booksid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colum_tiittle.setCellValueFactory(new PropertyValueFactory<>("tiitle"));
        colum_Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Colum_isbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        Colum_Lanvage.setCellValueFactory(new PropertyValueFactory<>("lanvage"));
        Lodtable();

    }

    @FXML
    void btn_update_Action(ActionEvent event) {
       boolean b=bookController.UpdateBooks(new Book(
               txt_Bookid.getText(),
               Txt_title.getText(),
               txt_Author.getText(),
               txt_Isbn.getText(),
               txt_lanvage.getText()
       ));




    }
    public void Lodtable(){

        List<Book> books=bookController.getInstancce().getAll();
        System.out.println(books);
        ObservableList<Book> objects = FXCollections.observableArrayList();
        books.forEach(book -> objects.add(book));
        tabel_Books.setItems(objects);
    }

}
