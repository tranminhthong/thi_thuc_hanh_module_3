package Controller;

import Model.Entities.Category;
import Model.Entities.Product;
import Model.Service.ProductService;
import Model.Service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Controller.ProductManagementServlet", value = "/products")
public class ProductManagementServlet extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("ac");
        if (action == null) action = "";
        switch (action){
            case "add_product":
                showFormAdd(request,response);
                break;
            case "edit_product":
                showFormEdit(request,response);
                break;
            case "delete_product":
                deleteProduct(request,response);
                break;
            default:
                listProduct(request,response);
        }
    }

    private void searchProductByKey(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("search");
        List<Product> products = productService.searchByKeyword(key);
        request.setAttribute("products",products);
        request.getRequestDispatcher("/ListProduct.jsp").forward(request,response);
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = productService.getAllCate();
        request.setAttribute("categories",categories);
        request.getRequestDispatcher("/AddProduct.jsp").forward(request,response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        List<Category> categories = productService.getAllCate();
        request.setAttribute("categories",categories);
        request.setAttribute("product",product);
        request.getRequestDispatcher("/EditProduct.jsp").forward(request,response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getAll();
        request.setAttribute("products",products);
        request.getRequestDispatcher("/ListProduct.jsp").forward(request,response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("/products");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("ac");
        if (action == null) action = "";
        switch (action){
            case "add_product":
                addProduct(request,response);
                break;
            case "edit_product":
                edit_Product(request,response);
                break;
            case "search_product":
                searchProductByKey(request,response);
                break;
            default:
                response.sendRedirect("/products");
        }
    }

    private void edit_Product(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setName(request.getParameter("name_product"));
        product.setPrice(Long.parseLong(request.getParameter("price_product")));
        product.setCategory(productService.getCategoryById(Integer.parseInt(request.getParameter("cate_id"))));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setColor(request.getParameter("color"));
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setDes(request.getParameter("des"));
        productService.editProduct(product);
        response.sendRedirect("/products");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setName(request.getParameter("name_product"));
        product.setPrice(Long.parseLong(request.getParameter("price_product")));
        product.setCategory(productService.getCategoryById(Integer.parseInt(request.getParameter("cate_id"))));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setColor(request.getParameter("color"));
        product.setDes(request.getParameter("des"));
        productService.addProduct(product);
        response.sendRedirect("/products?ac=add_product");
    }
}
