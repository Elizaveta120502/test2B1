package com.test.task.excel;

import com.test.task.database.ConnectionToDB;
import com.test.task.model.Entity;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFromExcel {
    private static ReadFromExcel instance;

    public static ReadFromExcel getInstance() {
        if (instance == null) {
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
        List<String> classes = new ArrayList<>();

        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();

            //For each row, iterate through all the columns


            for (int j = 1; j < row.getLastCellNum(); j = row.getLastCellNum()) {
                Iterator<Cell> cellIterator = row.cellIterator();
                //  while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Entity ent = new Entity();
                if (String.valueOf(cell) != "") {
                    if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().contains("КЛАСС")
                            && !cell.getStringCellValue().equals("ПО КЛАССУ")) {
                        classes.clear();
                        ent.setNameOfClass(cell.getStringCellValue());
                        classes.add(cell.getStringCellValue());
                    }


                    if (row.getCell(row.getFirstCellNum()).toString().equalsIgnoreCase("ПО КЛАССУ")) {

                        ent.setAccountNumber(null);
                        ent.setOpeningBalanceAssets((row.getCell(j).getNumericCellValue()));
                        ent.setOpeningBalanceLiabiblity((row.getCell(j + 1).getNumericCellValue()));
                        ent.setCirculationDebit((row.getCell(j + 2).getNumericCellValue()));
                        ent.setCirculationCredit((row.getCell(j + 3).getNumericCellValue()));
                        ent.setClosingBalanceAssets((row.getCell(j + 4).getNumericCellValue()));
                        ent.setClosingBalanceLiability((row.getCell(j + 5).getNumericCellValue()));
                        ent.setNameOfClass(classes.get(0));
                        ent.setId(id);
                        id++;

                        listForClass.add(ent);
                        break;
                    }

                    if (!cell.getCellType().equals(CellType.STRING)) {

                        if ((row.getCell(row.getFirstCellNum()).getNumericCellValue()) < 100) {
                            Entity ent2 = new Entity();
                            ent2.setAccountNumber((row.getCell(0).getNumericCellValue()));
                            ent2.setOpeningBalanceAssets((row.getCell(j).getNumericCellValue()));
                            ent2.setOpeningBalanceLiabiblity((row.getCell(j + 1).getNumericCellValue()));
                            ent2.setCirculationDebit((row.getCell(j + 2).getNumericCellValue()));
                            ent2.setCirculationCredit((row.getCell(j + 3).getNumericCellValue()));
                            ent2.setClosingBalanceAssets((row.getCell(j + 4).getNumericCellValue()));
                            ent2.setClosingBalanceLiability((row.getCell(j + 5).getNumericCellValue()));
                            ent2.setNameOfClass(classes.get(0));
                            ent2.setId(id);
                            id = id + 2;

                            if (ent2.getAccountNumber() != 0.0) {
                                listForGroupTable.add(ent2);
                            }
                            break;

                        } else if (Double.parseDouble(row.getCell(row.getFirstCellNum()).toString()) > 100) {
                            Entity ent3 = new Entity();
                            ent3.setAccountNumber((row.getCell(0).getNumericCellValue()));
                            ent3.setOpeningBalanceAssets((row.getCell(j).getNumericCellValue()));
                            ent3.setOpeningBalanceLiabiblity((row.getCell(j + 1).getNumericCellValue()));
                            ent3.setCirculationDebit((row.getCell(j + 2).getNumericCellValue()));
                            ent3.setCirculationCredit((row.getCell(j + 3).getNumericCellValue()));
                            ent3.setClosingBalanceAssets((row.getCell(j + 4).getNumericCellValue()));
                            ent3.setClosingBalanceLiability((row.getCell(j + 5).getNumericCellValue()));
                            ent3.setNameOfClass(classes.get(0));
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

        commonlist.add(listForClass);
        commonlist.add(listForBankAccount);
        commonlist.add(listForGroupTable);

        return commonlist;

    }

    public static void main(String[] args) {
        try {
            ConnectionToDB.getInstance().getConnection(ReadFromExcel.getInstance().readExcel(" "));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
