package dao;

import java.sql.Connection;
import java.lang.reflect.Field;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import Model.Product;

public class ProductDAO {
    
	 protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	    private static final String insertStatementString = "INSERT INTO product (name, quantity) VALUES (?,?)";
	    private final static String findStatementString = "SELECT * FROM product where id = ?";
	    private final static String findByNameStatementString = "SELECT * FROM product where name = ?";

    
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String findStatementString = "SELECT * FROM product";
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");

                productList.add(new Product(id, name, quantity));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return productList;
    }

    public static Product findById(int productId) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            toReturn = new Product(productId, name, quantity);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getQuantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static void update(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateStatementString = "UPDATE product SET name = ?, quantity = ? WHERE id = ?";
    
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1, product.getName());
            updateStatement.setInt(2, product.getQuantity());
            updateStatement.setInt(3, product.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void delete(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        String deleteStatementString = "DELETE FROM product WHERE id = ?";

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static Product findByName(String name) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Product toReturn = null;

        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, name);
            rs = findStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                toReturn = new Product(id, name, quantity);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    
    public String[][] generateTableFromObjects(List<Product> objects) {
        String[][] table = null;

        if (objects != null && !objects.isEmpty()) {
            int numberOfObjects = objects.size();
            Field[] fields = objects.get(0).getClass().getDeclaredFields();
            int numberOfFields = fields.length;

            table = new String[numberOfObjects + 1][numberOfFields];

            // Add headers
            for (int i = 0; i < numberOfFields; i++) {
                table[0][i] = fields[i].getName();
            }

            // Add data
            for (int i = 0; i < numberOfObjects; i++) {
                for (int j = 0; j < numberOfFields; j++) {
                    try {
                        fields[j].setAccessible(true);
                        table[i + 1][j] = String.valueOf(fields[j].get(objects.get(i)));
                    } catch (IllegalAccessException e) {
                        LOGGER.log(Level.WARNING, "ProductDAO:generateTableFromObjects " + e.getMessage());
                    }
                }
            }
        }

        return table;
    
    
    }
}
