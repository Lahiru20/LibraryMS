package formcontroller;



import controller.StaffController;
import dbconnection.DBConnection;
import model.Staff;
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

public class StaffFormController {
    StaffController staffController=new StaffController();

        @FXML
        private TableColumn<?, ?> colum_PhoneNumber;

        @FXML
        private TableColumn<?, ?> colum_Staffid;

        @FXML
        private TableColumn<?, ?> colum_email;

        @FXML
        private TableColumn<?, ?> colum_name;

        @FXML
        private TableView<Staff> table_Staff;

        @FXML
        private TextField txt_Staffid;

        @FXML
        private TextField txt_email;

        @FXML
        private TextField txt_name;

        @FXML
        private TextField txt_phoneNumber;

        @FXML
        void btn_Add_Action(ActionEvent event) {
            staffController.Addbook(new Staff(
                   txt_Staffid.getText(),
                    txt_email.getText(),
                    txt_name.getText(),
                    txt_phoneNumber.getText()



            ));

        }

        @FXML
        void btn_Search_Action(ActionEvent event) throws SQLException {
            Staff staff = staffController.SearchStaff(txt_Staffid.getText());

            if (staff!= null) {
            }

            txt_email.setText(String.valueOf(staff.getEmail()));
            txt_name.setText(String.valueOf(staff.getName()));
            txt_phoneNumber.setText(staff.getPhoneNumber());


        }

        @FXML
        void btn_View_Action(ActionEvent event) {
            colum_Staffid.setCellValueFactory(new PropertyValueFactory<>("staffid"));
            colum_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            colum_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            colum_PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));

            loadtable();

        }

        @FXML
        void btn_delete_Action(ActionEvent event) {
            Connection connection= DBConnection.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement=connection.prepareStatement("Delete from staff  where staff_id =?");

                preparedStatement.setString(1,txt_Staffid.getText());

                if (0 < preparedStatement.executeUpdate()){
                    new Alert(Alert.AlertType.INFORMATION,"Deleted successfully.").show();

                }else {
                    new  Alert(Alert.AlertType.INFORMATION,"Could not be deleted.").show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public void loadtable(){
            List<Staff> staff=staffController.getInstance().getAll();
            System.out.println(staff);
            ObservableList<Staff> observableList= FXCollections.observableArrayList();
            staff.forEach(stafff -> observableList.add(stafff));
            table_Staff.setItems(observableList);
        }

    public void btn_update_Actiom(ActionEvent actionEvent) {
        boolean b=staffController.UpdateBooks(new Staff(
            txt_Staffid.getText(),
                txt_email.getText(),
                txt_name.getText(),
                txt_phoneNumber.getText()

        ));

    }
}


