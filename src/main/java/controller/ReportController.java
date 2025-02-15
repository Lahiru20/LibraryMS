package controller;

import dbconnection.DBConnection;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.BookService;

import java.sql.Connection;

public class ReportController {

    private static ReportController instance;

    public ReportController getInstancce(){
        if (instance==null){
            return instance=new ReportController();
        }
        return instance;
    }

    public void GenerateReport(String ReportPath, String Filename, String query){
        try {
            JasperDesign report = JRXmlLoader.load(ReportPath);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(query);
            report.setQuery(newQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(report);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,Filename);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Report Generation Failed!.").show();
            throw new RuntimeException(e);
        }
    }

}
