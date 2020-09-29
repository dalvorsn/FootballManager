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
import servlet.action.match.AddGoalMatchSaveAction;
import servlet.action.match.FinishMatchSaveAction;
import servlet.action.save.account.CreateAccountSaveAction;
import servlet.action.save.championship.CreateChampionshipSaveAction;
import servlet.action.save.player.CreatePlayerSaveAction;
import servlet.action.save.team.CreateTeamSaveAction;
import servlet.action.save.player.DeletePlayerSaveAction;
import servlet.action.save.championship.EditChampionshipSaveAction;
import servlet.action.save.player.EditPlayerSaveAction;
import servlet.action.save.team.EditTeamSaveAction;
import servlet.action.save.account.LoginCheckAction;
import servlet.action.save.account.LogoutAction;
import servlet.action.save.championship.StartChampionshipSaveAction;
import servlet.action.save.championship.SubscribeChampionshipSaveAction;
import servlet.action.save.team.TeamAddPlayerSaveAction;
import servlet.action.save.team.TeamRemovePlayerSaveAction;
import servlet.action.view.championship.ChampionshipsViewAction;
import servlet.action.view.account.CreateAccountViewAction;
import servlet.action.view.championship.CreateChampionshipViewAction;
import servlet.action.view.player.CreatePlayerViewAction;
import servlet.action.view.team.CreateTeamViewAction;
import servlet.action.view.championship.EditChampionshipViewAction;
import servlet.action.view.player.EditPlayerViewAction;
import servlet.action.view.team.EditTeamViewAction;
import servlet.action.view.HomeViewAction;
import servlet.action.view.account.LoginViewAction;
import servlet.action.view.player.PlayersViewAction;
import servlet.action.view.rank.RankViewAction;
import servlet.action.view.match.EditMatchViewAction;
import servlet.action.view.team.TeamsViewAction;

/**
 *
 * @author luciano
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
        
        actionsMap.put("championships", ChampionshipsViewAction.class);
        actionsMap.put("create-championship", CreateChampionshipViewAction.class);
        actionsMap.put("create-championship-save", CreateChampionshipSaveAction.class);
        actionsMap.put("edit-championship", EditChampionshipViewAction.class);
        actionsMap.put("edit-championship-save", EditChampionshipSaveAction.class);
        actionsMap.put("start-championship-save", StartChampionshipSaveAction.class);
        actionsMap.put("subscribe-championship-save", SubscribeChampionshipSaveAction.class);
        
        actionsMap.put("edit-match", EditMatchViewAction.class);
        actionsMap.put("finish-match-save", FinishMatchSaveAction.class);
        actionsMap.put("add-goal-match-save", AddGoalMatchSaveAction.class);
        
        actionsMap.put("players", PlayersViewAction.class);
        actionsMap.put("create-player", CreatePlayerViewAction.class);
        actionsMap.put("edit-player", EditPlayerViewAction.class);
        actionsMap.put("create-player-save", CreatePlayerSaveAction.class);
        actionsMap.put("edit-player-save", EditPlayerSaveAction.class);
        actionsMap.put("delete-player", DeletePlayerSaveAction.class);

        actionsMap.put("rank", RankViewAction.class);
    }

    public IAction getAction(String actionName) throws InstantiationException, IllegalAccessException, Exception {
        if(! actionsMap.containsKey(actionName) ) {
            throw new Exception("Page not found");
        }
        
        return actionsMap.get(actionName).newInstance();
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

            request.setAttribute("error", ex.toString().replaceAll("(.*:)", ""));
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
