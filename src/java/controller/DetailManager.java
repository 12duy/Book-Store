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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Product;
import model.Review;

/**
 *
 * @author ASUS
 */
public class DetailManager extends HttpServlet {

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
        int pID = Integer.parseInt(request.getParameter("pID"));
        DAO dao = new DAO();
        Product pd = dao.getProductByID(pID);
        List<Review> listRv = dao.getReviewByProductID(pID);
        
        float avgRating = averageReview(listRv);
        request.setAttribute("avgRating", avgRating);
        Map<Integer, Integer> ratingsCount = listRating(listRv);
        request.setAttribute("ratingCounts", ratingsCount);
        
        request.setAttribute("dao", dao);
        request.setAttribute("product", pd);
        request.setAttribute("listRV", listRv);
        request.getRequestDispatcher("Book.jsp").forward(request, response);
    }
    
    public float averageReview(List<Review> listRV){
        int count = 0;
        for (Review rv : listRV){
            count++;
        }
        float avg;
        float sum = 0;
        for (Review rv : listRV){
            sum+=rv.getRating();
        }
        avg = sum / count;
        return avg;
    }
    
    public Map<Integer ,Integer> listRating(List<Review> listRV){
        int ratingsRaw[] = new int[listRV.size()];
        for (int i=0; i<listRV.size(); i++){
            ratingsRaw[i] = listRV.get(i).getRating();
        }
        
        
        Map<Integer ,Integer> ratingCounts = new HashMap<>();
        for (int i=1; i<5; i++){
            ratingCounts.put(i, 0);
        }
        for (int rating : ratingsRaw){
            if (rating >=1 && rating <=5){
                if (ratingCounts.containsKey(rating)){
                    ratingCounts.put(rating, ratingCounts.get(rating)+1);
                } else {
                    ratingCounts.put(rating, 1);
                }
            }
        }
        return ratingCounts;
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
