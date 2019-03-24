import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * handles the h2 options table and queries
 * @author dxb4791
 */
public class OptionsTable {
    public static void populateOptionsTableFromCSV(Connection conn, String filename) throws SQLException {
        ArrayList<Options> options = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(",");
                options.add(new Options(split));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String sql = createOptionsInsertSQL(options);

        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * create options table
     * @param conn established connection
     */
    public static void createOptionsTable(Connection conn){
        try {
            String query = "CREATE TABLE IF NOT EXISTS options("
                    + "o_id VARCHAR(255) PRIMARY KEY,"
                    + "TRANSMISSION VARCHAR(255),"+"COLOR VARCHAR(255),"+"ENGINE VARCHAR(255),"+"DRIVE VARCHAR(255)," +
                    "INTERIOR VARCHAR(255),"
                    + ");";
            Statement statement = conn.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * add an option to the database
     * @param conn established connection
     * @param o_id option id
     * @param transmission
     * @param color
     * @param engine
     * @param drive
     * @param interior
     */
    public static void addOptions(Connection conn, String o_id,String transmission,String color, String engine,
                                  String drive,String interior){
        String query = String.format("INSERT INTO options "
                + "VALUES(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');", o_id,transmission,color, engine,
                drive, interior);
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This creates an sql statement to do a bulk add of option
     *
     * @param options: list of option objects to add
     *
     * @return
     */
    public static String createOptionsInsertSQL(ArrayList<Options> options){
        StringBuilder sb = new StringBuilder();

        /**
         * The start of the statement,
         * tells it the table to add it to
         * the order of the data in reference
         * to the columns to ad dit to
         */
        sb.append("INSERT INTO options (o_id,transmission,color, engine,\n" +
                "                drive, interior) VALUES");

        /**
         * For each person append a (makename, model) tuple
         *
         * If it is not the last person add a comma to seperate
         *
         * If it is the last person add a semi-colon to end the statement
         */
        for(int i = 0; i < options.size(); i++){
            Options o = options.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                    o.getO_id(), o.getTransmission(),o.getColor(),o.getEngine(),o.getDrive(),o.getInterior()));
            if( i != options.size()-1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /**
     * Makes a query to the option table
     * with given columns and conditions
     *
     * @param conn
     * @param columns: columns to return
     * @param whereClauses: conditions to limit query by
     * @return
     */
    public static ResultSet queryOptionsTable(Connection conn,
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
        sb.append("FROM options ");

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
    public static void printOptionsTable(Connection conn){
        String query = "SELECT * FROM options;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("Options %s: %s: %s: %s: %s: %s \n",
                        result.getString(1),
                        result.getString(2),result.getString(3),result.getString(4),
                        result.getString(5),result.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
