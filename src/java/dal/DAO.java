/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Cart;
import model.Category;
import model.Product;
import model.Review;
//import sun.reflect.generics.visitor.Reifier;

/**
 *
 * @author ASUS
 */
public class DAO extends DBContext {

    public List<Product> getAllProduct() {
        List<Product> listAll = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT [ProductID]\n"
                + "      ,[CategoryID]\n"
                + "      ,[Name]\n"
                + "      ,[ImgUrl]\n"
                + "      ,[Description]\n"
                + "      ,[Price]\n"
                + "  FROM [dbo].[Product]";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                int cateID = resultSet.getInt("CategoryID");
                String name = resultSet.getString("Name");
                String imgUrl = resultSet.getString("ImgUrl");
                String des = resultSet.getString("Description");
                float price = resultSet.getFloat("Price");

                Product pd = new Product(id, cateID, name, imgUrl, des, price);
                listAll.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
    }

    public List<Account> getAllAccount() {
        List<Account> listAll = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT [UserID]\n"
                + "      ,[Username]\n"
                + "      ,[Password]\n"
                + "      ,[Email]\n"
                + "      ,[Role]\n"
                + "  FROM [dbo].[User]";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                boolean role = resultSet.getBoolean("Role");

                Account ac = new Account(id, username, password, email, role);
                listAll.add(ac);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
    }

    public List<Product> getProductByCate(int cID) {
        List<Product> listAll = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT * from Product where CategoryID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                int cateID = resultSet.getInt("CategoryID");
                String name = resultSet.getString("Name");
                String imgUrl = resultSet.getString("ImgUrl");
                String des = resultSet.getString("Description");
                float price = resultSet.getFloat("Price");

                Product pd = new Product(id, cateID, name, imgUrl, des, price);
                listAll.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
    }
    
    public void removeItemFromCart(Cart cart, int productId) {
        if (cart != null && cart.getItems() != null) {
            cart.removeItem(productId);
        }
    }

    public List<Product> searchByName(String txt) {
        List<Product> listAll = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT * from Product where Name like ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + txt + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                int cateID = resultSet.getInt("CategoryID");
                String name = resultSet.getString("Name");
                String imgUrl = resultSet.getString("ImgUrl");
                String des = resultSet.getString("Description");
                float price = resultSet.getFloat("Price");

                Product pd = new Product(id, cateID, name, imgUrl, des, price);
                listAll.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
    }

    public Product getProductByID(int pID) {
        connection = getConnection();
        String sql = "SELECT * from Product where ProductID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                int cateID = resultSet.getInt("CategoryID");
                String name = resultSet.getString("Name");
                String imgUrl = resultSet.getString("ImgUrl");
                String des = resultSet.getString("Description");
                float price = resultSet.getFloat("Price");

                Product pd = new Product(id, cateID, name, imgUrl, des, price);
                return pd;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Category> getAllCateGory() {
        ArrayList<Category> listAll = new ArrayList<>();
        // Ket noi database
        connection = getConnection();
        //Cbi cau lenh sql
        String sql = "select * from Category";
        try {

            // cbi cho cau lenh
            statement = connection.prepareStatement(sql);
            // Thuc thi cau lenh tren => Tra ve ket qua
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("CategoryID");
                String name = resultSet.getString("CategoryName");
                Category cate = new Category(id, name);
                listAll.add(cate);
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex.getMessage() + " at DBContext at findAll()");
        }
        return listAll;
    }

    public void addProduct(String name, String img, float price, String des, int cateID) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([CategoryID]\n"
                + "           ,[Name]\n"
                + "           ,[ImgUrl]\n"
                + "           ,[Description]\n"
                + "           ,[Price])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cateID);
            statement.setString(2, name);
            statement.setString(3, img);
            statement.setString(4, des);
            statement.setFloat(5, price);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(int productID) {
        connection = getConnection();
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE ProductID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAccount(int id) {
        connection = getConnection();
        String sql = "DELETE FROM [dbo].[User]\n"
                + "      WHERE UserID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(int productID, int cateID, String name, String img, String des, float price) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [CategoryID] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[ImgUrl] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Price] = ?\n"
                + " WHERE ProductID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cateID);
            statement.setString(2, name);
            statement.setString(3, img);
            statement.setString(4, des);
            statement.setFloat(5, price);
            statement.setInt(6, productID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account login(String username, String password) {
        connection = getConnection();
        String sql = "SELECT * from [User] where Username = ? and Password = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String userName = resultSet.getString("Username");
                String passWord = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                boolean role = resultSet.getBoolean("Role");

                Account ac = new Account(id, userName, passWord, email, role);
                return ac;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account checkUsername(String username) {
        connection = getConnection();
        String sql = "SELECT * from [User] where Username = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String userName = resultSet.getString("Username");
                String passWord = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                boolean role = resultSet.getBoolean("Role");

                Account ac = new Account(id, userName, passWord, email, role);
                return ac;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getUserByID(String id) {
        connection = getConnection();
        String sql = "SELECT * from [User] where UserID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("UserID");
                String userName = resultSet.getString("Username");
                String passWord = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                boolean role = resultSet.getBoolean("Role");

                Account ac = new Account(idUser, userName, passWord, email, role);
                return ac;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getUserByEmail(String email) {
        connection = getConnection();
        String sql = "SELECT * from [User] where Email = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("UserID");
                String userName = resultSet.getString("Username");
                String passWord = resultSet.getString("Password");
                String eMail = resultSet.getString("Email");
                boolean role = resultSet.getBoolean("Role");

                Account ac = new Account(idUser, userName, passWord, eMail, role);
                return ac;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void signUp(String username, String password, String email) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[Email]\n"
                + "           ,[Role])\n"
                + "     VALUES\n"
                + "           (?, ?, ?,  0)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePassword(String id, String newPass) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [Password] = ? \n"
                + " WHERE UserID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newPass);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeEmail(String id, String newEmail) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [Email] = ? \n"
                + " WHERE UserID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newEmail);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Review> getAllReview() {
        List<Review> listAll = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT [ReviewID]\n"
                + "      ,[UserID]\n"
                + "      ,[ProductID]\n"
                + "      ,[Rating]\n"
                + "      ,[Comment]\n"
                + "      ,[Date]\n"
                + "  FROM [dbo].[Review]";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ReviewID");
                int userID = resultSet.getInt("UserID");
                int productID = resultSet.getInt("ProductID");
                int rating = resultSet.getInt("Rating");
                String comment = resultSet.getString("Comment");
                String date = resultSet.getString("Date");

                Review rv = new Review(id, userID, productID, rating, comment, date);
                listAll.add(rv);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
    }

    public void addReview(int userID, int productID, int rating, String comment, String date) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Review]\n"
                + "           ([UserID]\n"
                + "           ,[ProductID]\n"
                + "           ,[Rating]\n"
                + "           ,[Comment]\n"
                + "           ,[Date])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, productID);
            statement.setInt(3, rating);
            statement.setString(4, comment);
            statement.setString(5, date);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Review> getReviewByProductID(int productID) {
        List<Review> listAll = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT [ReviewID]\n"
                + "      ,[UserID]\n"
                + "      ,[ProductID]\n"
                + "      ,[Rating]\n"
                + "      ,[Comment]\n"
                + "      ,[Date]\n"
                + "  FROM [dbo].[Review] where ProductID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ReviewID");
                int userID = resultSet.getInt("UserID");
                int ProductID = resultSet.getInt("ProductID");
                int rating = resultSet.getInt("Rating");
                String comment = resultSet.getString("Comment");
                String date = resultSet.getString("Date");

                Review rv = new Review(id, userID, ProductID, rating, comment, date);
                listAll.add(rv);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
    }

    public void addOrderDetail(int productID, int quantity, float subPrice) {
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, SubPrice) VALUES (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productID);
            statement.setInt(2, quantity);
            statement.setFloat(3, subPrice);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.addReview(4, 8, 5, "Quá ok", "03/11/2023 16:14:56");
        System.out.println(dao.getReviewByProductID(8));
    }

}
