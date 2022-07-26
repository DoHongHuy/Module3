package controller;


import model.Product;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productServlet", urlPatterns = "/products")
public class productServlet extends HttpServlet {


    ProductService productService;
    CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();
        if (this.getServletContext().getAttribute("listCategory") == null) {
            this.getServletContext().setAttribute("listCategory", categoryService.selectAllCategory());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "list":
//                showProductPage(req, resp);
                break;
            case "edit":
                showProductEditPage(req, resp);
                break;
            case "create":
                showProductCreatePage(req, resp);
                break;
            case "delete":
                try {
                    doDeleteProduct(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                showAllProductPage(req, resp);
                break;
            default:
                showAllProductPage(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "list":
//                showProductPage(req, resp);
                break;
            case "edit":
                doEditProductPage(req, resp);
                break;
            case "create":
                doCreateProduct(req, resp);
                break;
            case "delete":
//                showProductDelete(req, resp);
                break;
//            case "search":
//                dosearchByKey(req, resp);
//                break;
            default:
                showAllProductPage(req, resp);
                break;
        }
    }

    private void doEditProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cp/product/edit.jsp");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Double price = Double.parseDouble(req.getParameter("price"));
        int quantity  = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String describe = req.getParameter("describe");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));

        List<String> error = new ArrayList<>();
        if (name.equals("")) {
            error.add("Tền không được để trống");
        }
        if (price < 9000 ) {
            error.add("Giá quá thấp, vui lòng nhập giá vs số giá phải hơn 9k");
        }
        if (price > 100000000) {
            error.add("Giá cao quá, giảm giá lại đi, nhỏ hơn 1 trăm triệu ");
        }
        if (name.equals("")) {
            error.add("Tên không được để trống");
        }
        if (color.equals("")) {
            error.add("Color Không được để trống");
        }
        if (describe.equals("")) {
            error.add("describe Không được để trống");
        }

        if (error.size() > 0) {
            req.setAttribute("errors", error);
            error.add("Dữ liệu không đúng, vui lòng kiểm tra lại!");
        }
        if(error.size() == 0){
            Product product = new Product(id,name,price,quantity ,color ,describe , idCategory );
            productService.update(product);
        }
        dispatcher.forward(req, resp);
    }

    private void showProductEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/edit.jsp");
        String id = req.getParameter("id");
        System.out.println(id);
        Product product = productService.findById(Integer.parseInt(id));
        if (product != null) {
            req.setAttribute("product", product);
        }
        dispatcher.forward(req, resp);
    }

    private void doCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String describe = req.getParameter("describe");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));

        List<String> error = new ArrayList<>();
        if (name.equals("")) {
            error.add("Tền không được để trống");
        }
        if (price < 9000 ) {
            error.add("Giá quá thấp, vui lòng nhập giá vs số giá phải hơn 9k");
        }
        if (price > 1000000000) {
            error.add("Giá cao quá, giảm giá lại đi, nhỏ hơn 1 tỉ ");
        }
        if (name.equals("")) {
            error.add("Tên không được để trống");
        }
        if (color.equals("")) {
            error.add("Color Không được để trống");
        }
        if (describe.equals("")) {
            error.add("describe Không được để trống");
        }

        if (error.size() > 0) {
            req.setAttribute("errors", error);
            error.add("Dữ liệu không đúng, vui lòng kiểm tra lại!");
        }
        if(error.size() == 0){
            Product newProduct = new Product(name, price, quantity, color, describe, idCategory);
            productService.create(newProduct);
        }
        dispatcher.forward(req, resp);
    }

    private void showProductCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAllProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/list.jsp");
        String key = "";
        List<Product> productList = null;

        if (req.getParameter("search") != null) {
            key = req.getParameter("key");
            System.out.println(key + " key day ne");
            productList = productService.searchByKey(key);
        }else productList = productService.findAll();
        if(req.getParameter("") != null){
            productList = productService.findAll();
        }

//        productList = productService.findAll();
        req.setAttribute("productList", productList);
        dispatcher.forward(req, resp);
    }
    private void doDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.existByid(id);

        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cp/product/list.jsp");
        dispatcher.forward(request, response);
    }


}
