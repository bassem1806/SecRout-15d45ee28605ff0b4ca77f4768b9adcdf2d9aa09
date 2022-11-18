package com.example.Securite_Routiere.service;


import com.example.Securite_Routiere.repositories.PvAccident1Repository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PvAccidentReportService {

    @Autowired
    PvAccident1Repository pvAccident1Repository;

    public String exportReport(String reportFormat, Long pvaccidId) throws FileNotFoundException, JRException {

        System.out.println("pvaccidid :" + pvaccidId);
        System.out.println("aazaeaeaeae789");

        String path = "C:\\Users\\HP\\Desktop\\Report";

        //  Optional<PvAccident1> pvAccident1 = pvAccident1Repository.findById(pvaccidId);

        // List<PvAccident1> pvAccidents1= (List<PvAccident1>) pvAccident1Repository.findAll();

        System.out.println("aazaeaeaeae78900000+++");
        //  System.out.println("size liste pv" +((List<PvAccident1>) pvAccident1Repository.findAll()).size());

        File file = ResourceUtils.getFile("classpath:Blank_A4.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(pvAccident1.get()));
        System.out.println("aazaeaeaeae78900000+++////////");
        Map<String, Object> parameters = new HashMap<>();
        //  parameters.put("createdBy", "PvAccident");
        System.out.println("aazaeaeaeae78900000+++////////**************");


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        System.out.println("azery");

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\pvAccident.html");
        }
        System.out.println("html");
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\pvAccident.pdf");
        }


        System.out.println("pdf");

        return "report generated in path : " + path;
    }
}
