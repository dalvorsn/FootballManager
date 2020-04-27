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
import model.domain.User;
import servlet.action.IAction;
import servlet.action.db.CreateAccountSaveAction;
import servlet.action.db.CreatePlayerSaveAction;
import servlet.action.db.CreateTeamSaveAction;
import servlet.action.db.DeletePlayerSaveAction;
import servlet.action.db.EditPlayerSaveAction;
import servlet.action.db.EditTeamSaveAction;
import servlet.action.db.LoginCheckAction;
import servlet.action.db.LogoutAction;
import servlet.action.db.TeamAddPlayerSaveAction;
import servlet.action.db.TeamRemovePlayerSaveAction;
import servlet.action.view.ChampionshipViewAction;
import servlet.action.view.CreateAccountViewAction;
import servlet.action.view.CreatePlayerViewAction;
import servlet.action.view.CreateTeamViewAction;
import servlet.action.view.EditPlayerViewAction;
import servlet.action.view.EditTeamViewAction;
import servlet.action.view.HomeViewAction;
import servlet.action.view.LoginViewAction;
import servlet.action.view.PlayersViewAction;
import servlet.action.view.RankViewAction;
import servlet.action.view.TeamsViewAction;

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
        actionsMap.put("logout", LogoutAction.class);

        actionsMap.put("create-account", CreateAccountViewAction.class);
        actionsMap.put("create-account-save", CreateAccountSaveAction.class);

        actionsMap.put("home", HomeViewAction.class);

        actionsMap.put("teams", TeamsViewAction.class);
        actionsMap.put("create-team", CreateTeamViewAction.class);
        actionsMap.put("create-team-save", CreateTeamSaveAction.class);
        actionsMap.put("edit-team", EditTeamViewAction.class);
        actionsMap.put("edit-team-save", EditTeamSaveAction.class);
        actionsMap.put("team-add-player", TeamAddPlayerSaveAction.class);
        actionsMap.put("team-rem-player", TeamRemovePlayerSaveAction.class);
        
        actionsMap.put("championship", ChampionshipViewAction.class);

        actionsMap.put("players", PlayersViewAction.class);
        actionsMap.put("create-player", CreatePlayerViewAction.class);
        actionsMap.put("edit-player", EditPlayerViewAction.class);
        actionsMap.put("create-player-save", CreatePlayerSaveAction.class);
        actionsMap.put("edit-player-save", EditPlayerSaveAction.class);
        actionsMap.put("delete-player", DeletePlayerSaveAction.class);

        actionsMap.put("rank", RankViewAction.class);
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
            if(action == null) {
                throw new Exception("Page not found.");
            }
            
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                request.setAttribute("user", user);
            } else if (action.requiresAuth()) {
                new LoginViewAction().execute(request, response);
                return;
            }
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
