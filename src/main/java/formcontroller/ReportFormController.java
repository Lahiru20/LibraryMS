package formcontroller;

import controller.ReportController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

    public TextField txt_member_count;
    public DatePicker dt_returned_books;
    public DatePicker dt_borrowed_books;
    public TextField txt_bb_nowdate;
    public TextField txt_rb_nowdate;
    public TextField txt_book_count;
    public CheckBox chbox_returnbooks;
    public CheckBox chbox_members;
    public CheckBox chbox_borrowedbooks;
    public CheckBox chbox_books;



    ReportController reportController = new ReportController();

    public void GenerateReportOnAction(ActionEvent actionEvent) {
        if (!(isNumber(txt_book_count)) || Integer.parseInt(txt_book_count.getText()) < 0 || txt_book_count.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"Invalid input, please check the fields.").show();
            return;
        }

        if(chbox_books.isSelected()){
            reportController.GenerateReport("src/main/resources/reports/BooksReport.jrxml","booksreport.pdf","SELECT * FROM books LIMIT " + Integer.valueOf(txt_book_count.getText()));
        }

        if(chbox_borrowedbooks.isSelected()){
            reportController.GenerateReport("src/main/resources/reports/BorrowedBooksReport.jrxml","borrowedbooksreport.pdf","SELECT * FROM borrowedbooks WHERE borrow_date >= '"+dt_borrowed_books.getValue().toString()+"' AND borrow_date < '"+txt_bb_nowdate.getText()+"';");
        }

        if(chbox_returnbooks.isSelected()){
            reportController.GenerateReport("src/main/resources/reports/ReturnedBooksReport.jrxml","returnedbooksreport.pdf","SELECT * FROM returnsbooks WHERE return_date>= '"+dt_returned_books.getValue().toString()+"' AND return_date < '"+txt_rb_nowdate.getText()+"';");
        }

        if(chbox_members.isSelected()){
            reportController.GenerateReport("src/main/resources/reports/MemberReport.jrxml","memberreport.pdf","SELECT * FROM members LIMIT " + Integer.valueOf(txt_member_count.getText()));
        }
    }

    public boolean isNumber(TextField textField) {
        try {
            Double.parseDouble(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void LoadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        BreakIterator lblDate;
        txt_bb_nowdate.setText(f.format(date));
        txt_rb_nowdate.setText(f.format(date));
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private void configureDatePicker(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDate();
        configureDatePicker(dt_returned_books);
        configureDatePicker(dt_borrowed_books);
    }
}
