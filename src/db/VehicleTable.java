package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * vehicle table implementation
 * @author dxb4791
 */
public class VehicleTable {
    public static void populateVehicleTableFromCSV(Connection conn, String filename) throws SQLException {
        ArrayList<Vehicle> vehicle = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(",");
                vehicle.add(new Vehicle(split));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String sql = createVehicleInsertSQL(vehicle);

        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * creates the vehicle table
     * @param conn
     */
    public static void createVehicleTable(Connection conn){
        try {
            String query = "CREATE TABLE IF NOT EXISTS vehicle("
                    + "VIN VARCHAR(255) PRIMARY KEY,"
                    + "MILEAGE VARCHAR(255),"+"D_ID VARCHAR(255),"
                    + "OPTIONS VARCHAR(255),"+"PRICE VARCHAR(255),"
                    +"MODELNAME VARCHAR(255),"
                    + ");";
            Statement statement = conn.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * add a vehicle
     * @param conn established connection
     * @param vin
     * @param mileage
     * @param D_ID
     * @param options
     * @param price
     * @param ModelName
     */
    public static void addVehicle(Connection conn, String vin,
                                  String mileage,
                                  String D_ID,
                                  String options,
                                  String price,
                                  String ModelName){
        String query = String.format("INSERT INTO vehicle "
                + "VALUES(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');",vin, mileage, D_ID,options,price,ModelName);
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This creates an sql statement to do a bulk add of vehicle
     *
     * @param : list of vehicle objects to add
     *
     * @return
     */
    public static String createVehicleInsertSQL(ArrayList<Vehicle> vehicles){
        StringBuilder sb = new StringBuilder();

        /**
         * The start of the statement,
         * tells it the table to add it to
         * the order of the data in reference
         * to the columns to ad dit to
         */
        sb.append("INSERT INTO vehicle (vin, mileage, D_ID,options,price,ModelName) VALUES");

        /**
         * For each person append a (makename, model) tuple
         *
         * If it is not the last person add a comma to seperate
         *
         * If it is the last person add a semi-colon to end the statement
         */
        for(int i = 0; i < vehicles.size(); i++){
            Vehicle m = vehicles.get(i);
            sb.append(String.format("(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                    m.getVin(), m.getMileage(),m.getD_ID(),m.getOptions(),m.getPrice(),m.getModelName()));
            if( i != vehicles.size()-1){
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
    public static ResultSet queryVehicleTable(Connection conn,
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
        sb.append("FROM vehicle ");

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
    public static void printVehicleTable(Connection conn){
        String query = "SELECT * FROM vehicle;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("db.Vehicle %s: %s : %s: %s: %s\n",
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

    public static void printUnpurchasedCars(Connection conn){
        String query ="(select vehicle.vin, price, modelname from Vehicle)\n" +
                "except\n" +
                "(select vehicle.vin, price, modelname from Customer, Vehicle where customer.vin = vehicle.vin);\n";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                System.out.printf("VIN: %s: Price USD: %d ModelName: %s\n",
                        result.getString(1),
                        Integer.parseInt((result.getString(2))),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static ResultSet findVehicle(Connection conn,String makename){
        String query = "SELECT name, class, seats, doors, makename FROM model GROUP BY "+makename+";";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
