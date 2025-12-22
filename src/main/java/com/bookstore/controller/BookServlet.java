package com.bookstore.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

    private BookDAO dao;

    @Override
    public void init() {
        dao = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            req.setAttribute("books", dao.getAllBooks());
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, res);
        }
        else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("book", dao.getBookById(id));
            req.getRequestDispatcher("edit.jsp").forward(req, res);
        }
        else if ("delete".equals(action)) {
            dao.deleteBook(Integer.parseInt(req.getParameter("id")));
            res.sendRedirect("BookServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            dao.addBook(new Book(
                0,
                req.getParameter("title"),
                req.getParameter("author")
            ));
        }
        else if ("update".equals(action)) {
            dao.updateBook(new Book(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("title"),
                req.getParameter("author")
            ));
        }
        res.sendRedirect("BookServlet");
    }
}
