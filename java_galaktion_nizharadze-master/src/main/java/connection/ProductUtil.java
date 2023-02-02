package connection;

// Sul 25 - 26 qula minda :D

import  com.example.java_galaktion_nizharadze.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductUtil {
    public static void createTable() throws SQLException, RuntimeException {
        String createSql = "CREATE TABLE IF NOT EXISTS gaga" +
                "(ID INTEGER not NULL AUTO_INCREMENT, " +
                " Name VARCHAR(255), " +
                " brand VARCHAR(255), " +
                " Price INTEGER NOT NULL, " +
                " expiration INTEGER NOT NULL, " +
                " PRIMARY KEY ( ID ))";

        //  String dropSql = "DROP TABLE gaga";

        JDBCUtil.getStatement().executeUpdate(createSql);

        System.out.println("Created Table in given database if not exists");
    }

    public static void insertProduct(Product product) throws SQLException, RuntimeException {
        String insertSql = "INSERT INTO gaga(Name, brand, Price, expiration) VALUES( "
                +"'"+product.getName()+"', "
                +"'"+product.getBrand()+"', "
                +"'"+product.getPrice()+"', "
                +"'"+product.getExpiration()+"' )";

        JDBCUtil.getStatement().executeUpdate(insertSql);

        System.out.println("chassmuli product "+product.getName()+" into gaga table Successfully!");
    }

    public static Map<Integer, Long> getMappedData() throws SQLException {
        String selectSql = "SELECT * FROM gaga";
        List<Product> myList = new ArrayList<>();

        ResultSet resultSet = JDBCUtil.getStatement().executeQuery(selectSql);
        while(resultSet.next()){
            myList.add(new Product(resultSet.getString("Name"),
                    resultSet.getString("brand"),
                    resultSet.getInt("Price"),
                    resultSet.getInt("expiration")));
        }

        Map<Integer, Long> myMap = myList.stream().collect(Collectors.groupingBy(Product::getPriceRange, Collectors.counting()));

        System.out.println("myMap "+myMap);
        return myMap;
    }


    public static ObservableList<PieChart.Data> getData() throws SQLException {
        Map<Integer, Long> map = getMappedData();
        ObservableList<PieChart.Data> myList = FXCollections.observableArrayList(
        );

        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            //  System.out.println(entry.getKey() + ":" + entry.getValue());
            myList.add(new PieChart.Data(entry.getKey().toString() +"-st range "+ entry.getValue().toString()+" amount", entry.getValue()));
        }

        return myList;
    }
}

