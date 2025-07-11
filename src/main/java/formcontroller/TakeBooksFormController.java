package formcontroller;

import controller.BookController;
import controller.BorrowedController;
import controller.MemberController;
import controller.StaffController;
import model.Book;
import model.Borrowed_Books;
import model.Members;
import model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class TakeBooksFormController implements Initializable {
    public ComboBox combo_Bookid;
    public TextField txt_Title;
    public TextField txt_Author;
    public TextField txt_ISBN;
    public TextField txt_lanvage;
    public ComboBox combo_Memberid;
    public TextField txt_full_name;
    public TextField txt_PhoneNumber;
    public TextField txt_Address;
    public TextField txt_Membership;
    public ComboBox combo_Staffid;
    public TextField txt_email;
    public TextField txt_name;
    public TextField txt_pohneNumber;
    public Label lbl_date;
    public TableColumn colum_borrowid;
    public TableColumn colum_Bookid;
    public TableColumn colum_memberid;
    public TableColumn colum_Staffid;
    public TableColumn colum_brrowdate;
    public TableColumn colume_return_date;
    public TableColumn colum_isbrowwed;
    public TableView brrowed_table;
    public TextField txt_returndate;
    public TextField txt_broowed;
    public TextField txt_yes_or_No;
    public TextField txt_brrrowedid;
    public TableColumn colum_borrowedid;
    public TableColumn colum_bookid;
    public TableColumn colum_memeberid;
    public TableColumn colum_staffid;
    public TableColumn colum_brrow_date;
    public TableColumn colum_is_broowed;

    BookController bookController = new BookController();
    MemberController memberController = new MemberController();
    StaffController staffController = new StaffController();

    BorrowedController borrowedController =new BorrowedController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        setCombo_Bookid();
        setCombo_Memberid();
        setCombo_Staffid();

        LoadDate();


    }


    public void setCombo_Bookid() {
        ObservableList<String> setBookid = FXCollections.observableArrayList();
        for (Book book : bookController.getInstancce().getAll()) {
            setBookid.add(book.getBookid());
        }
        combo_Bookid.setItems(setBookid);

    }


    public void Combo_Book_Action(ActionEvent actionEvent) {
        String selectedID = (String) combo_Bookid.getSelectionModel().getSelectedItem();
        System.out.println(selectedID);
        Book book = bookController.getInstancce().SearcBooks(selectedID);
        setCombo_Bookid();
        txt_Title.setText(book.getTiitle());
        txt_Author.setText(book.getAuthor());
        txt_ISBN.setText(book.getIsbn());
        txt_lanvage.setText(book.getLanvage());

    }

    public void combo_Memeberid_Action(ActionEvent actionEvent) {

        String selectID = (String) combo_Memberid.getSelectionModel().getSelectedItem();
        System.out.println(selectID);
        //setCombo_Memberid();
        Members members = memberController.searchMember(selectID);
        System.out.println(members.getName());
        txt_full_name.setText(members.getName());
        txt_PhoneNumber.setText(members.getPhoneNumber());
        txt_Address.setText(members.getAdrees());
        txt_Membership.setText(members.getMembershipdates());
    }

    public void setCombo_Memberid() {
        ObservableList<String> setMemeberid = FXCollections.observableArrayList();
        for (Members members : memberController.getInstance().getAll()) {
            setMemeberid.add(members.getMemberid());
            combo_Memberid.setItems(setMemeberid);

        }
    }


    public void setCombo_Staffid() {
        ObservableList<String> setStaffid = FXCollections.observableArrayList();
        for (Staff staff : staffController.getInstance().getAll()) {
            setStaffid.add(staff.getStaffid());
            combo_Staffid.setItems(setStaffid);
        }
    }


    public void combo_staffid_Action(ActionEvent actionEvent) throws SQLException {
        String selecID = (String) combo_Staffid.getSelectionModel().getSelectedItem();
//        setCombo_Staffid();
        Staff staff = staffController.SearchStaff(selecID);

        String email = staff.getEmail();
        txt_email.setText(email);
        txt_name.setText(staff.getName());
        txt_pohneNumber.setText(staff.getPhoneNumber());

    }

    private void LoadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        BreakIterator lblDate;
        lbl_date.setText(f.format(date));
    }

    ObservableList<Borrowed_Books> borrowedBooksObservableList = FXCollections.observableArrayList();

    public void take_Books_Action(ActionEvent actionEvent) {

        borrowedController.addBooks(new Borrowed_Books(
                txt_brrrowedid.getText(),
                (String) combo_Bookid.getValue(),
                (String) combo_Memberid.getValue(),
                (String) combo_Staffid.getValue(),

                lbl_date.getText(),
                txt_yes_or_No.getText()
        ));
        System.out.println("Book successfully added to the database!");


        brrowed_table.setItems(borrowedBooksObservableList);

        colum_borrowedid.setCellValueFactory(new PropertyValueFactory<>("brrowedbokksid"));
        colum_bookid.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colum_memeberid.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        colum_staffid.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        colum_brrow_date.setCellValueFactory(new PropertyValueFactory<>("brooedate"));
        colum_is_broowed.setCellValueFactory(new PropertyValueFactory<>("isBrowwed"));
        LodTable();




    }

    public  void  LodTable(){
        List<Borrowed_Books>brrowedbooks= borrowedController.getInstance().getAll();
        System.out.println(brrowedbooks);
        ObservableList<Borrowed_Books> objects = FXCollections.observableArrayList();
        brrowedbooks.forEach(brrowedbooks1 -> objects.add(brrowedbooks1));
        brrowed_table.setItems(objects);


    }

    }




