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
 * handles the table and queries for the model
 * @author dxb4791
 */
public class ModelTable {
    public static void populateModelTableFromCSV(Connection conn, String filename) throws SQLException {
        ArrayList<Model> model = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(",");
                model.add(new Model(split));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String sql = createModelInsertSQL(model);

        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * creates a table in H2 of the model
     * @param conn established connection
     */
    public static void createModelTable(Connection conn){
        try {
            String query = "CREATE TABLE IF NOT EXISTS model("
                    + "NAME VARCHAR(255) PRIMARY KEY,"
                    + "YEAR VARCHAR(255),"+"CLASS VARCHAR(255),"+ "SEATS VARCHAR(255)," +"DOORS VARCHAR(255),"
                    +"MAKENAME VARCHAR(255),"+ ");";
            Statement statement = conn.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * add a model to the database
     * @param conn established connection
     * @param name name
     * @param year year
     * @param c_class class
     * @param seats number of seats
     * @param doors number of doors
     * @param makeName name of the make
     */
    public static void addModel(Connection conn, String name, String year, String c_class, String seats, String doors, String makeName){
        String query = String.format("INSERT INTO model "
                + "VALUES(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');", name, year,c_class,seats,doors,makeName);
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeModel(Connection conn, String name, String makeName){
        String query = String.format("DELETE FROM model where Name = " + name + " and make_name = " + makeName);
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This creates an sql statement to do a bulk add of model
     *
     * @param model: list of model objects to add
     *
     * @return
     */
    public static String createModelInsertSQL(ArrayList<Model> model){
        StringBuilder sb = new StringBuilder();

        /**
         * The start of the statement,
         * tells it the table to add it to
         * the order of the data in reference
         * to the columns to ad dit to
         */
        sb.append("INSERT INTO model (name, year,class,seats,doors,makeName) VALUES");

        /**
         * For each person append a (makename, model) tuple
         *
         * If it is not the last person add a comma to seperate
         *
         * If it is the last person add a semi-colon to end the statement
         */
        for(int i = 0; i < model.size(); i++){
            Model m = model.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                    m.getName(),m.getYear(),m.getC_class(),m.getSeats(),m.getDoors()
                    ,m.getMakename()));
            if( i != model.size()-1){
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
    public static ResultSet queryModelTable(Connection conn,
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
        sb.append("FROM model ");

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
    public static void printModelTable(Connection conn){
        String query = "SELECT * FROM model;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("db.Model %s: %s: %s: %s: %s: %s \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ResultSet avgPrice(Connection conn){
        String query ="SELECT modelname, avg(price) FROM vehicle GROUP BY modelname ORDER BY count(modelname) desc;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void listModelsforMake(Connection conn) {
        String query = "select makename, name, class from model group by makename, name;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){

                System.out.printf("MakeName: %s ModelName: %s Class: %s: \n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listClassesAndCount(Connection conn) {
        String query = "select c_class, count(c_class) from model group by c_class";

        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("Class: %s NumOfCars: %d \n",
                        result.getString(1),
                        Integer.parseInt(result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
