/**
 * Created by ceo on 3/23/2019.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MakeTable {

    public static void populateMakeTableFromCSV(Connection conn, String filename) throws SQLException{
        ArrayList<Make> makes = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(",");
                makes.add(new Make(split));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String sql = createMakeInsertSQL(makes);

        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    public static void createMakeTable(Connection conn){

        try {
            String query = "CREATE TABLE IF NOT EXISTS make("
                    + "MAKENAME VARCHAR(255) PRIMARY KEY,"
                    + "MODEL VARCHAR(255),"
                    + ");";
            Statement statement = conn.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addPerson(Connection conn, String makename, String model){
        String query = String.format("INSERT INTO make "
                                    + "VALUES(\'%s\',\'%s\');", makename, model);
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
     * @param makes: list of make objects to add
     *
     * @return
     */
    public static String createMakeInsertSQL(ArrayList<Make> makes){
        StringBuilder sb = new StringBuilder();

        /**
         * The start of the statement,
         * tells it the table to add it to
         * the order of the data in reference
         * to the columns to ad dit to
         */
        sb.append("INSERT INTO make (makename, model) VALUES");

        /**
         * For each person append a (makename, model) tuple
         *
         * If it is not the last person add a comma to seperate
         *
         * If it is the last person add a semi-colon to end the statement
         */
        for(int i = 0; i < makes.size(); i++){
            Make m = makes.get(i);
            sb.append(String.format("(\'%s\',\'%s\')",
                    m.getMakename(), m.getModel()));
            if( i != makes.size()-1){
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
    public static ResultSet queryMakeTable(Connection conn,
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
        sb.append("FROM make ");

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
    public static void printMakeTable(Connection conn){
        String query = "SELECT * FROM make;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("Make %s: %s \n",
                        result.getString(1),
                        result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
