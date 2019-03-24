import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ceo on 3/24/2019.
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
     * This creates an sql statement to do a bulk add of make
     *
     * @param customer: list of make objects to add
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
     * Makes a query to the make table
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
                System.out.printf("Customer %s: %s: %s: %s: %s: %s: %s: %s \n",
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


}

