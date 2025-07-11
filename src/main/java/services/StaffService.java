package services;

import model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface StaffService {


        List<Staff> getAll();

        Staff SearchStaff(String staff) throws SQLException;

        boolean UpdateBooks(Staff staff);

        boolean DeleteBooks(Staff staffid);

        void  Addbook (Staff staff);

    }
