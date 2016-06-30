package com.infosys.engops.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.infosys.engops.utils.SpringContextUtil;

@Path("/actuals")
public class Actuals {

	@POST
	@Path("/actuals")
	public void saveActuals() {
		//To test, give the filepath of the .xlsx file under resources
		String filePath = "";
		List<com.infosys.engops.entities.Actuals> actualsEntityList = getActualsEntityList(filePath);

		if (null != actualsEntityList) {
			EntityManagerFactory entityManagerFactory = SpringContextUtil.getInstance().getContext()
					.getBean("entityManagerFactory", EntityManagerFactory.class);
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			for (com.infosys.engops.entities.Actuals actualsEntity : actualsEntityList) {
				try {

					transaction.begin();
					com.infosys.engops.entities.Actuals actualsFound = entityManager
							.find(com.infosys.engops.entities.Actuals.class, actualsEntity);
					if (null == actualsFound) {
						entityManager.persist(actualsEntity);
					} else {
						entityManager.merge(actualsEntity);
					}
					entityManager.flush();
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
					e.printStackTrace();
				}

			}
		}

	}

	private List<com.infosys.engops.entities.Actuals> getActualsEntityList(String filePath) {

		List<com.infosys.engops.entities.Actuals> actualsEntityList = new ArrayList<com.infosys.engops.entities.Actuals>();
		try {
			InputStream inputStream = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row labelRow = rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				String projectCode = null;

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 0) {
						projectCode = String.valueOf(cell.getStringCellValue());
					} else if (cell.getColumnIndex() != 0 && cell.getColumnIndex() % 4 == 0) {
						com.infosys.engops.entities.Actuals actualsEntity = new com.infosys.engops.entities.Actuals();
						actualsEntity.setActualRevenue(String.valueOf(cell.getNumericCellValue()));
						actualsEntity.setQuarter(labelRow.getCell(cell.getColumnIndex()).getStringCellValue());
						actualsEntity.setProjectCode(projectCode);
						actualsEntityList.add(actualsEntity);
					} else {
						continue;
					}
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return actualsEntityList;
	}

}
