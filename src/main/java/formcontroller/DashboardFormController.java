package formcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane dashboard_frame;

    public void btn_member_Action(ActionEvent actionEvent) throws IOException {
        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Member.fxml"))));
        stage.setTitle("Members");
        stage.setResizable(false);
        stage.show();
    }

    public void btn_Addbooks_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddBooks.fxml"))));
        stage.setTitle("Add Books");
        stage.setResizable(false);
        stage.show();
    }

    public void btn_Staff_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/StaffForm.fxml"))));
        stage.setTitle("Staff");
        stage.setResizable(false);
        stage.show();

    }

    public void Borrowed_Bokkss_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/TakeBooks.fxml"))));
        stage.setTitle("Borrowed Books");
        stage.setResizable(false);
        stage.show();
    }

    public void btn_Return_book_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReturnBooks.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Return Books");
        stage.show();
    }


    public void btn_Report(ActionEvent actionEvent) throws IOException {
        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Report.fxml"))));
        stage.setTitle("Report");
        stage.setResizable(false);
        stage.show();
    }

    public void Close_Action(ActionEvent actionEvent) {
        Stage stage = (Stage) dashboard_frame.getScene().getWindow();
        stage.close();
    }

    public void Logout_Action(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Login.fxml"))));
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) dashboard_frame.getScene().getWindow();
        stage.close();
    }
}
