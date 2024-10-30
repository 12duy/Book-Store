/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author ASUS
 */
public class ManageBooksControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAO dao = new DAO();
        List<Product> listSearch = dao.getAllProduct();
        List<Category> listAllCate = dao.getAllCateGory();
        request.setAttribute("listAllCate", listAllCate);
        request.setAttribute("listAllProd", listSearch);
        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        DAO dao = new DAO();
        switch (action) {
            case "add":
                String name = request.getParameter("name");
                String image = request.getParameter("image");
                Float price = Float.parseFloat(request.getParameter("price"));
                String des = request.getParameter("description");
                int cateID = Integer.parseInt(request.getParameter("cate"));

                dao.addProduct(name, image, price, des, cateID);
                response.sendRedirect("manage");
                break;
            case "detele":
                int productID = Integer.parseInt(request.getParameter("pID"));
                dao.deleteProduct(productID);
                response.sendRedirect("manage");
                break;
            case "edit":
                productID = Integer.parseInt(request.getParameter("pID"));
                name = request.getParameter("name");
                image = request.getParameter("image");
                price = Float.parseFloat(request.getParameter("price"));
                des = request.getParameter("des");
                cateID = Integer.parseInt(request.getParameter("cate"));
                dao.updateProduct(productID, cateID, name, image, des, price);
                response.sendRedirect("manage");
                break;
            default:
                throw new AssertionError();
        }

    }

}
