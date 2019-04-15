package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * creates the customer table and SQL statments
 * Created by ceo and dxb4791 on 3/24/2019.
 */
public class CustomerTable {
    public static void populateCustomerTableFromCSV(Connection conn, String filename) throws SQLException {
        ArrayList<Customer> customer = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(",");
                customer.add(new Customer(split));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String sql = createCustomerInsertSQL(customer);

        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * creates the customer table
     * precondition connection established
     * @param conn established connection
     */
    public static void createCustomerTable(Connection conn){
        try {
            String query = "CREATE TABLE IF NOT EXISTS customer("
                    + "C_ID VARCHAR(255) PRIMARY KEY,"
                    + "NAME VARCHAR(255),"+"ADDRESS VARCHAR(255),"
                    +"PHONE VARCHAR(255),"+"GENDER VARCHAR(255),"
                    +"INCOME VARCHAR(255),"+"VIN VARCHAR(255),"
                    +"D_ID VARCHAR(255),"
                    + ");";
            Statement statement = conn.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * add a customer to the database
     * @param conn established connection
     * @param C_ID customer id
     * @param name name of the customer
     * @param address address of the customer
     * @param phone phone of the customer
     * @param gender gender of the customer
     * @param income income of the customer
     * @param vin vin of the car the customer wants to buy
     * @param D_ID dealer id
     */
    public static void addCustomer(Connection conn, String C_ID,
                                   String name,
                                   String address,
                                   String phone,
                                   String gender,
                                   String income,
                                   String vin,
                                   String D_ID){
        String query = String.format("INSERT INTO customer "
                + "VALUES(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');",
                C_ID, name,address,phone,gender,income,vin,D_ID);
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This creates an sql statement to do a bulk add of customer
     *
     * @param customer: list of customer objects to add
     *
     * @return
     */
    public static String createCustomerInsertSQL(ArrayList<Customer> customer){
        StringBuilder sb = new StringBuilder();

        /**
         * The start of the statement,
         * tells it the table to add it to
         * the order of the data in reference
         * to the columns to ad dit to
         */
        sb.append("INSERT INTO customer (C_ID, name,address,phone,gender,income,vin,D_ID) VALUES");

        /**
         * For each person append a (makename, model) tuple
         *
         * If it is not the last person add a comma to seperate
         *
         * If it is the last person add a semi-colon to end the statement
         */
        for(int i = 0; i < customer.size(); i++){
            Customer m = customer.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                    m.getC_ID(),m.getName(),m.getAddress(),m.getPhone(),m.getGender(),m.getIncome(),
                    m.getVin(),m.getD_ID()));
            if( i != customer.size()-1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /**
     * Makes a query to the customer table
     * with given columns and conditions
     *
     * @param conn
     * @param columns: columns to return
     * @param whereClauses: conditions to limit query by
     * @return
     */
    public static ResultSet queryCustomerTable(Connection conn,
                                           ArrayList<String> columns,
                                           ArrayList<String> whereClauses){
        StringBuilder sb = new StringBuilder();

        /**
         * Start the select query
         */
        sb.append("SELECT ");

        /**
         * If we gave no columns just give them all to us
         *
         * other wise add the columns to the query
         * adding a comma top seperate
         */
        if(columns.isEmpty()){
            sb.append("* ");
        }
        else{
            for(int i = 0; i < columns.size(); i++){
                if(i != columns.size() - 1){
                    sb.append(columns.get(i) + ", ");
                }
                else{
                    sb.append(columns.get(i) + " ");
                }
            }
        }

        /**
         * Tells it which table to get the data from
         */
        sb.append("FROM customer ");

        /**
         * If we gave it conditions append them
         * place an AND between them
         */
        if(!whereClauses.isEmpty()){
            sb.append("WHERE ");
            for(int i = 0; i < whereClauses.size(); i++){
                if(i != whereClauses.size() -1){
                    sb.append(whereClauses.get(i) + " AND ");
                }
                else{
                    sb.append(whereClauses.get(i));
                }
            }
        }

        /**
         * close with semi-colon
         */
        sb.append(";");

        //Print it out to verify it made it right
        System.out.println("Query: " + sb.toString());
        try {
            /**
             * Execute the query and return the result set
             */
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Queries and print the table
     * @param conn
     */
    public static void printCustomerTable(Connection conn){
        String query = "SELECT * FROM customer;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("db.Customer %s: %s: %s: %s: %s: %s: %s: %s \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void printCustomersByIncome(Connection conn) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a dealer id to find it's associated customers:\n");
            String in = input.next();
            String query = "select * from customer\n where d_id = " + in + "\norder by income desc;\n";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("db.Customer %s: %s: %s: %s: %s: %s: %s: %s \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void customerSellsVehicle(Connection conn) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your customer ID to sell your car: \n");
            String in = input.next();
            String query = "update customer set vin = null where c_id = " + in + ";\n";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            //for possible testing
            /*while(result.next()){
                System.out.printf("db.Customer %s: %s: %s: %s: %s: %s: %s: %s \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateAfterBuy(Connection conn, String vin,int c_id){
        String query = "SET customer.vin= "+vin+ "WHERE customer.c_id="+c_id +";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

        }catch(SQLException e ){
            e.printStackTrace();
        }
    }
    public static void listByAddress(Connection conn){
        String query = "select name, address from customer group by address;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                System.out.printf("Name %s: Address %s\n",
                        result.getString(1),
                        result.getString(2));
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
    }
    public static Customer findUser(Connection conn,String c_id){
        String query = "select * from customer where c_id = "+c_id+";";
        String cust[]=new String[10];
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            if(result.next()){
                cust[0]= result.getString(1);
                cust[1]= result.getString(2);
                cust[2]= result.getString(3);
                cust[3]= result.getString(4);
                cust[4]= result.getString(5);
                cust[5]= result.getString(6);
                cust[6]= result.getString(7);
                cust[7]= result.getString(8);
            }

        }catch(SQLException e ){
            e.printStackTrace();
        }
        if(cust[0]==null){
            return null;
        }
        else {
            return new Customer(cust);
        }
    }

}

