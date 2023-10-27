/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ACER
 */
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Products;
import model.ProductsTable;
import model.Shoppingcart;
import model.ShoppingcartPK;
import model.ShoppingcartTable;
import utilities.UpdatedShoppingCartList;

/**
 * Servlet to handle adding items to the shopping cart.
 */
public class AddMovieController extends HttpServlet {

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
        
        int checkUser = 1;
        
        synchronized(this.getServletContext()) {
            if (UpdatedShoppingCartList.isUpdating(this.getServletContext(), checkUser)) {
                try {
                    this.getServletContext().wait(); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        String[] selectedMovies = request.getParameterValues("selectedMovies");
        int cartIdCheck = 1;
        
        List<Shoppingcart> cartList = ShoppingcartTable.findAllShopppingcart();
        for (int i = 0; i < cartList.size(); i++) {
            if (cartIdCheck <= cartList.get(i).getShoppingcartPK().getCartId()) {
                cartIdCheck = cartList.get(i).getShoppingcartPK().getCartId() + 1;
            }
        }
        
        ShoppingcartPK cartPK = new ShoppingcartPK();
        cartPK.setCartId(cartIdCheck);
        
        if (selectedMovies != null) {
            for (String movieIdStr : selectedMovies) {
                int movieId = Integer.parseInt(movieIdStr);
                String quantityStr = request.getParameter("quantity" + movieId);
                int quantity = Integer.parseInt(quantityStr);
                
                Products mov = ProductsTable.findProductById(movieId);
               
                cartPK.setMovieId(mov.getId());
                
                Shoppingcart cart = new Shoppingcart(cartPK);
                cart.setProducts(mov);
                cart.setQuantity(quantity);
                
                int rowInserted = ShoppingcartTable.insertShoppingcart(cart);
                
            }
        
        HttpSession session = request.getSession();
        session.setAttribute("cartPK", cartPK);
        
        request.getRequestDispatcher("showCartList.jsp").forward(request, response);
        } 
    }
}
