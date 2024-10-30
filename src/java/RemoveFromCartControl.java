
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Cart;

@WebServlet("/RemoveFromCartControl")
public class RemoveFromCartControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the cart from the session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Get the product ID from the request
        int productId = Integer.parseInt(request.getParameter("pID"));
        // Check if the cart and product ID are valid
        if (cart != null) {
            // Remove the item from the cart
            cart.removeItem(productId); // Assuming you have a method to remove an item
            // Optionally, update the cart in the session
            session.setAttribute("cart", cart);
        }

        // Redirect back to the shopping cart page
        response.sendRedirect("addToCart"); // Replace with your cart page URL
    }
}
