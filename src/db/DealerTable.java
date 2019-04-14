package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * class for the dealer table and its queries
 * @author dxb4791
 */
public class DealerTable {
    public static void populateDealerTableFromCSV(Connection conn, String filename) throws SQLException {
        ArrayList<Dealer> dealer = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(",");
                dealer.add(new Dealer(split));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String sql = createDealerInsertSQL(dealer);

        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * creates a dealer table
     * @param conn
     */
    public static void createDealerTable(Connection conn){
        try {
            String query = "CREATE TABLE IF NOT EXISTS dealer("
                    + "D_ID VARCHAR(255) PRIMARY KEY,"
                    + "NAME VARCHAR(255),"+ "location VARCHAR(255),"
                    + "INVENTORY VARCHAR(255),"+ "PRIMARYMAKE VARCHAR(255),"
                    + ");";
            Statement statement = conn.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * add a dealer to the database
     * @param conn established connection
     * @param D_ID dealer id
     * @param name name
     * @param location address
     * @param inventory inventor
     * @param primaryMake make
     */
    public static void addDealer(Connection conn, String D_ID,
                                 String name,
                                 String location,
                                 String inventory,
                                 String primaryMake){
        String query = String.format("INSERT INTO dealer "
                + "VALUES(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');", D_ID, name,location,inventory,primaryMake);
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This creates an sql statement to do a bulk add of dealer
     *
     * @param dealer: list of dealer objects to add
     *
     * @return
     */
    public static String createDealerInsertSQL(ArrayList<Dealer> dealer){
        StringBuilder sb = new StringBuilder();

        /**
         * The start of the statement,
         * tells it the table to add it to
         * the order of the data in reference
         * to the columns to ad dit to
         */
        sb.append("INSERT INTO dealer ( D_ID, name,location,inventory,primaryMake) VALUES");

        /**
         * For each person append a (makename, model) tuple
         *
         * If it is not the last person add a comma to seperate
         *
         * If it is the last person add a semi-colon to end the statement
         */
        for(int i = 0; i < dealer.size(); i++){
            Dealer m = dealer.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                    m.getD_ID(), m.getName(),m.getLocation(),m.getInventory(),m.getPrimaryMake()));
            if( i != dealer.size()-1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /**
     * Makes a query to the dealer table
     * with given columns and conditions
     *
     * @param conn
     * @param columns: columns to return
     * @param whereClauses: conditions to limit query by
     * @return
     */
    public static ResultSet queryDealerTable(Connection conn,
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
        sb.append("FROM dealer ");

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
     * @param conn established connection
     */
    public static void printDealerTable(Connection conn){
        String query = "SELECT * FROM dealer;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("db.Dealer %s: %s : %s : %s : %s \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void printDealersBySales(Connection conn) {
        String stringquery = "select dealer.name, dealer.location, sum(vehicle.price)\n" +
                "from dealer inner join vehicle on dealer.D_ID = vehicle.d_id\n" +
                "group by dealer.name\n" +
                "order by sum(vehicle.price) desc;";
        try {
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(stringquery);

            while(result.next()){
                System.out.printf("db.Dealer %s: %s : \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
