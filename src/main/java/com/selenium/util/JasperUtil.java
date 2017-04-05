package com.selenium.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selenium.model.ImageBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperUtil {
	
	public static void createPdfEvidence(String fileName, List<ImageBean> listImageBean) throws FileNotFoundException, JRException {
		
		//Parameters to pass to the report. In this case we don't have any additional parameter
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//Loading jasper report compiled file.
		//First of all it is necessary to compile the .jrxml file and put the .jasper file in the project's resource to load it.
		InputStream file = new BufferedInputStream(new FileInputStream("src/main/resources/seleniumtemplate.jasper"));
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(listImageBean));
		
		//Writing the evidence pdf file.
		FileOutputStream output = new FileOutputStream(new File(fileName));
		JasperExportManager.exportReportToPdfStream(jasperPrint, output);
	}
}
