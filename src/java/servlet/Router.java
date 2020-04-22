/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.action.IAction;
import servlet.action.db.CreateAccountSaveAction;
import servlet.action.db.LoginCheckAction;
import servlet.action.view.CreateAccountViewAction;
import servlet.action.view.HomeViewAction;
import servlet.action.view.LoginViewAction;

/**
 *
 * @author dalvo
 */
@WebServlet(name = "Router", urlPatterns = {"/router"})
public class Router extends HttpServlet {

    private static final HashMap<String, Class<? extends IAction>> actionsMap;

    static {
        actionsMap = new HashMap<>();
        actionsMap.put(null, LoginViewAction.class);
        actionsMap.put("login", LoginViewAction.class);
        actionsMap.put("login-check", LoginCheckAction.class);
        actionsMap.put("create-account", CreateAccountViewAction.class);
        actionsMap.put("create-account-save", CreateAccountSaveAction.class);

        actionsMap.put("home", HomeViewAction.class);
    }

    public IAction getAction(String actionName) throws InstantiationException, IllegalAccessException {
        Class<? extends IAction> reflectClass = actionsMap.get(actionName);

        return reflectClass.newInstance();
    }

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
        String actionName = request.getParameter("action");

        try {
            IAction action = getAction(actionName);
            action.execute(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=error");

            request.setAttribute("error", ex.getCause().toString());
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
