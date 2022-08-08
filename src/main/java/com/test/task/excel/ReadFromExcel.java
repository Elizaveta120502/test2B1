package com.test.task.excel;

import com.mysql.cj.util.DataTypeUtil;
import com.test.task.database.ConnectionToDB;
import com.test.task.model.Entity;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFromExcel {
    private static ReadFromExcel instance;

    public static ReadFromExcel  getInstance(){
        if (instance == null){
            instance = new ReadFromExcel();
        }
        return instance;
    }

    public List<List<Entity>> readExcel(String file) throws IOException {
        int id = 1;
        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream("C:/TestTask2B1/src/main/resources/excelFile.xls"));
        HSSFSheet excelSheet = excelBook.cloneSheet(0);

        List<List<Entity>> commonlist = new ArrayList<>();

        List<Entity> listForGroupTable = new ArrayList<>();

        List<Entity> listForBankAccount = new ArrayList<>();
        List<Entity> listForClass = new ArrayList<>();
        Iterator<Row> rowIterator = excelSheet.iterator();

       // for (int i=8; i < excelSheet.getLastRowNum(); i ++){
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            //For each row, iterate through all the columns


            // HSSFRow row = excelSheet.getRow(i);
            Entity ent = new Entity();
            for (int j = 1; j < row.getLastCellNum(); j = row.getLastCellNum()) {
                Iterator<Cell> cellIterator = row.cellIterator();
                //  while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();


                if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().contains("КЛАСС")
                 && !cell.getStringCellValue().equals("ПО КЛАССУ")) {
                    ent.setNameOfClass(cell.toString());
                    listForClass.add(ent);
                    break;
                }else {


//                        cellIterator.next();
//                        break;

//                     if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().isEmpty()){
//                        cellIterator.next();
//                        break;
//                    }


                    if (row.getCell(row.getFirstCellNum()).toString().equalsIgnoreCase("ПО КЛАССУ")) {
                        Entity ent1 = new Entity();
                        ent1.setAccountNumber(null);
                        ent1.setOpeningBalanceAssets(Double.parseDouble(row.getCell(j).toString()));
                        ent1.setOpeningBalanceLiabiblity(Double.parseDouble(row.getCell(j + 1).toString()));
                        ent1.setCirculationDebit(Double.parseDouble(row.getCell(j + 2).toString()));
                        ent1.setCirculationCredit(Double.parseDouble(row.getCell(j + 3).toString()));
                        ent1.setClosingBalanceAssets(Double.parseDouble(row.getCell(j + 4).toString()));
                        ent1.setClosingBalanceLiability(Double.parseDouble(row.getCell(j + 5).toString()));
                        ent1.setNameOfClass(ent.getNameOfClass());
                        ent1.setId(id);
                        id++;
                        listForClass.add(ent1);
                        break;
                    }
                    if (cell.getCellType().equals(CellType._NONE)) {
                        if (!cell.getCellType().equals(CellType.STRING)) {

                            if (Double.parseDouble(row.getCell(row.getFirstCellNum()).toString()) < 100) {
                                Entity ent2 = new Entity();
                                ent2.setAccountNumber(Double.parseDouble(row.getCell(0).toString()));
                                ent2.setOpeningBalanceAssets(Double.parseDouble(row.getCell(j).toString()));
                                ent2.setOpeningBalanceLiabiblity(Double.parseDouble(row.getCell(j + 1).toString()));
                                ent2.setCirculationDebit(Double.parseDouble(row.getCell(j + 2).toString()));
                                ent2.setCirculationCredit(Double.parseDouble(row.getCell(j + 3).toString()));
                                ent2.setClosingBalanceAssets(Double.parseDouble(row.getCell(j + 4).toString()));
                                ent2.setClosingBalanceLiability(Double.parseDouble(row.getCell(j + 5).toString()));
                                ent2.setNameOfClass(ent.getNameOfClass());
                                ent2.setId(id);
                                id++;

                                listForGroupTable.add(ent2);
                                break;

                            } else if (Double.parseDouble(row.getCell(row.getFirstCellNum()).toString()) > 100) {
                                Entity ent3 = new Entity();
                                ent3.setAccountNumber(Double.parseDouble(row.getCell(0).toString()));
                                ent3.setOpeningBalanceAssets(Double.parseDouble(row.getCell(j).toString()));
                                ent3.setOpeningBalanceLiabiblity(Double.parseDouble(row.getCell(j + 1).toString()));
                                ent3.setCirculationDebit(Double.parseDouble(row.getCell(j + 2).toString()));
                                ent3.setCirculationCredit(Double.parseDouble(row.getCell(j + 3).toString()));
                                ent3.setClosingBalanceAssets(Double.parseDouble(row.getCell(j + 4).toString()));
                                ent3.setClosingBalanceLiability(Double.parseDouble(row.getCell(j + 5).toString()));
                                ent3.setNameOfClass(ent.getNameOfClass());
                                ent3.setId(id);
                                id++;

                                listForBankAccount.add(ent3);
                                break;

                            }
                        } else {
                            cellIterator.next();
                        }
                    }
                }

                }
            }

        commonlist.add(listForClass);
        commonlist.add(listForBankAccount);
        commonlist.add(listForGroupTable);

        return commonlist;

    }

    public static void main (String[] args){
        try {
            ConnectionToDB.getInstance().getConnection(ReadFromExcel.getInstance().readExcel(" "));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
