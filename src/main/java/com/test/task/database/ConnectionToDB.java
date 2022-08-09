package com.test.task.database;

import com.test.task.logger.LoggerProvider;
import com.test.task.model.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionToDB {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/test2b1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static final String HIGH_COMMA = "\'";


    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ConnectionToDB instance;

    public static ConnectionToDB getInstance() {
        if (instance == null) {
            instance = new ConnectionToDB();
        }
        return instance;
    }

    public boolean getConnection(List<List<Entity>> commonList) {


        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // getting Statement object to execute query
            statement = connection.createStatement();

            for (List<Entity> e: commonList){
                System.out.println("------------------------------");
                for (Entity e1: e){
                    System.out.println(e1.toString());
                }
            }
          for (String s : insertClass(commonList)){
              statement.executeUpdate(s);
          }

            for (String s : insertBankAccount(commonList)){
                statement.executeUpdate(s);
            }
            for (String s : insertGroup(commonList)){
                statement.executeUpdate(s);
            }



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            LoggerProvider.getLOG().error("SQLException occurred");
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
                LoggerProvider.getLOG().error("SQLException occurred");
                return false;
            }
            try {
                statement.close();
            } catch (SQLException se) {
                se.printStackTrace();
                LoggerProvider.getLOG().error("SQLException occurred");
                return false;
            }

        }

        return true;
    }


    public static List<String>  insertClass(List<List<Entity>> commonList){

        List<String> allQueries = new ArrayList<>();

        for (int i = 0; i < commonList.get(0).size(); i++){

            String query  = "insert into class (id,\n" +
                    "                  class_name,\n" +
                    "                  sum_opening_balance_assets,\n" +
                    "                  sum_opening_balance_liability,\n" +
                    "                  sum_circulation_debit,\n" +
                    "                  sum_circulation_credit,\n" +
                    "                  sum_closing_balance_assets,\n" +
                    "                  sum_closing_balance_liability)" +
                    "values (" + commonList.get(0).get(i).getId() +"," + HIGH_COMMA+
                    commonList.get(0).get(i).getNameOfClass()+ HIGH_COMMA +"," +
                    commonList.get(0).get(i).getOpeningBalanceAssets()+"," +
                    commonList.get(0).get(i).getOpeningBalanceLiabiblity()+"," +
                    commonList.get(0).get(i).getCirculationDebit()+"," +
                    commonList.get(0).get(i).getCirculationCredit()+"," +
                    commonList.get(0).get(i).getClosingBalanceAssets()+","
                    +commonList.get(0).get(i).getClosingBalanceLiability() +");";
            allQueries.add(query);

        }


        return allQueries;
    }

    public static List<String>  insertBankAccount(List<List<Entity>> commonList){
        List<String> allQueries = new ArrayList<>();

        for (int i = 0; i < commonList.get(1).size(); i++) {

            String query = "insert into bank_account (id, account_number,\n" +
                    "                          opening_balance_assets,\n" +
                    "                          opening_balance_liability,\n" +
                    "                          circulation_debit,\n" +
                    "                          circulation_credit,\n" +
                    "                          closing_balance_assets,\n" +
                    "                          closing_balance_liability,\n" +
                    "                          class_id)" +
                    "values (" + commonList.get(1).get(i).getId() +","+
                    commonList.get(1).get(i).getAccountNumber()+"," +
                    commonList.get(1).get(i).getOpeningBalanceAssets()+"," +
                    commonList.get(1).get(i).getOpeningBalanceLiabiblity()+"," +
                    commonList.get(1).get(i).getCirculationDebit()+"," +
                    commonList.get(1).get(i).getCirculationCredit() +","+
                    commonList.get(1).get(i).getClosingBalanceAssets()+","
                    +commonList.get(1).get(i).getClosingBalanceLiability()+","+
                    commonList.get(0).get(i).getId() + ");";
            allQueries.add(query);
        }
        return allQueries;
    }


    public static List<String>  insertGroup(List<List<Entity>> commonList){
        List<String> allQueries = new ArrayList<>();

        for (int i = 0; i < commonList.get(2).size(); i++) {

            for(int j= 0; j < 8; j++) {
                String query = "insert into group_table (id,group_number,\n" +
                        "                         sum_opening_balance_assets,\n" +
                        "                         sum_opening_balance_liability,\n" +
                        "                         sum_circulation_debit,\n" +
                        "                         sum_circulation_credit,\n" +
                        "                         sum_closing_balance_assets,\n" +
                        "                         sum_closing_balance_liability,\n" +
                        "                         class_id)" +
                        "values (" + commonList.get(2).get(i).getId() + ","  +
                        commonList.get(2).get(i).getAccountNumber()  + "," +
                        commonList.get(2).get(i).getOpeningBalanceAssets() + "," +
                        commonList.get(2).get(i).getOpeningBalanceLiabiblity() + "," +
                        commonList.get(2).get(i).getCirculationDebit() + "," +
                        commonList.get(2).get(i).getCirculationCredit() + "," +
                        commonList.get(2).get(i).getClosingBalanceAssets() + ","
                        + commonList.get(2).get(i).getClosingBalanceLiability() + "," +
                        commonList.get(0).get(j).getId() + ");";
                allQueries.add(query);
            }

        }
        return allQueries;
    }





}
