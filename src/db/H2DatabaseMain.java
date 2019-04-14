package db;

import java.sql.*;
import java.util.ArrayList;

/**
 * This is a sample main program.
 * You will create something similar
 * to run your database.
 *
 * @author scj
 *
 */
public class H2DatabaseMain {

    //The connection to the database
    private Connection conn;

    /**
     * Create a database connection with the given params
     * @param location: path of where to place the database
     * @param user: user name for the owner of the database
     * @param password: password of the database owner
     */
    public void createConnection(String location,
                                 String user,
                                 String password){
        try {

            //This needs to be on the front of your location
            String url = "jdbc:h2:" + location;

            //This tells it to use the h2 driver
            Class.forName("org.h2.Driver");

            //creates the connection
            conn = DriverManager.getConnection(url,
                    user,
                    password);
        } catch (SQLException | ClassNotFoundException e) {
            //You should handle this better
            e.printStackTrace();
        }
    }

    /**
     * just returns the connection
     * @return: returns class level connection
     */
    public Connection getConnection(){
        return conn;
    }

    /**
     * When your database program exits
     * you should close the connection
     */
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts and runs the database
     * @param args: not used but you can use them
     */
    public static void main(String[] args) {

        H2DatabaseMain demo = new H2DatabaseMain();

        //Hard drive location of the database
        String location = "./database/database";
        String user = "ceo";
        String password = "test";

        //Create the database connections, basically makes the database
        demo.createConnection(location, user, password);

        try {

//            /**
//             * Creates a sample make table
//             * and populates it from a csv file
//             */
//            MakeTable.createMakeTable(demo.getConnection());
//            MakeTable.populateMakeTableFromCSV(
//                    demo.getConnection(),
//                    "make.csv");
//
//            /**
//             * Just displays the table
//             */
//            MakeTable.printMakeTable(demo.getConnection());
//
//            /**
//             * Runs a basic query on the table
//             */
//            System.out.println("\n\nPrint results of SELECT * FROM make");
//            ResultSet results = MakeTable.queryMakeTable(
//                    demo.getConnection(),
//                    new ArrayList<String>(),
//                    new ArrayList<String>());
//
//            /**
//             * Iterates the Result set
//             *
//             * Result Set is what a query in H2 returns
//             *
//             * Note the columns are not 0 indexed
//             * If you give no columns it will return them in the
//             * order you created them. To gaurantee order list the columns
//             * you want
//             */
//            while(results.next()){
//                System.out.printf("\tdb.Make %s: %s\n",
//                        results.getString(1),
//                        results.getString(2));
//            }
//
//            /**
//             * A more complex query with columns selected and
//             * addition conditions
//             */
//            System.out.println("\n\nPrint results of SELECT "
//                    + "makename, model "
//                    + "FROM make "
//                    + "WHERE makename = \'Corliss\' "
//                    + "AND model = \'Walther\'");
//
//            /**
//             * This is one way to do this, but not the only
//             *
//             * Create lists to make the whole thing more generic or
//             * you can just construct the whole query here
//             */
//            ArrayList<String> columns = new ArrayList<String>();
//            columns.add("makename");
//            columns.add("model");
//
//            /**
//             * Conditionals
//             */
//            ArrayList<String> whereClauses = new ArrayList<String>();
//            whereClauses.add("makename = \'Corliss\'");
//            whereClauses.add("model = \'Walther\'");
//
//            /**
//             * query and get the result set
//             *
//             * parse the result set and print it
//             *
//             * Notice not all of the columns are here because
//             * we limited what to show in the query
//             */
//            ResultSet results2 = MakeTable.queryMakeTable(
//                    demo.getConnection(),
//                    columns,
//                    whereClauses);
//            while(results2.next()){
//                System.out.printf("\tdb.Make %s: %s\n",
//                        results2.getString(1),
//                        results2.getString(2));
//            }
//            /**
//             * Creates a sample make table
//             * and populates it from a csv file
//             */
//            CustomerTable.createCustomerTable(demo.getConnection());
            CustomerTable.populateCustomerTableFromCSV(
                    demo.getConnection(),
                    "customer.csv");

            /**
             * Just displays the table
             */
//            CustomerTable.printCustomerTable(demo.getConnection());
//
//            /**
//             * Runs a basic query on the table
//             */
//            System.out.println("\n\nPrint results of SELECT * FROM customer");
//            ResultSet mresults = CustomerTable.queryCustomerTable(
//                    demo.getConnection(),
//                    new ArrayList<String>(),
//                    new ArrayList<String>());
//
//            DealerTable.createDealerTable(demo.getConnection());
//            DealerTable.populateDealerTableFromCSV(
//                    demo.getConnection(),
//                    "dealer.csv");
//            ResultSet dresults = MakeTable.queryMakeTable(
//                    demo.getConnection(),
//                    new ArrayList<String>(),
//                    new ArrayList<String>());
//            ModelTable.createModelTable(demo.getConnection());
//            ModelTable.populateModelTableFromCSV(
//                    demo.getConnection(),
//                    "model.csv");
//            OptionsTable.createOptionsTable(demo.getConnection());
//            OptionsTable.populateOptionsTableFromCSV(
//                    demo.getConnection(),
//                    "options.csv");
//            VehicleTable.createVehicleTable(demo.getConnection());
//            VehicleTable.populateVehicleTableFromCSV(
//                    demo.getConnection(),
//                    "vehicle.csv");
//
//            /**
//             * Iterates the Result set
//             *
//             * Result Set is what a query in H2 returns
//             *
//             * Note the columns are not 0 indexed
//             * If you give no columns it will return them in the
//             * order you created them. To gaurantee order list the columns
//             * you want
//             */
//            while(mresults.next()){
//                System.out.printf("\tdb.Customer %s: %s\n",
//                        mresults.getString(1),
//                        mresults.getString(2));
//            }
//            CustomerTable.printCustomerTable(demo.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
