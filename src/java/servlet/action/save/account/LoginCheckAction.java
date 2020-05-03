/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.account;

import servlet.action.view.HomeViewAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.domain.User;
import servlet.action.IAction;
import servlet.action.view.account.LoginViewAction;

/**
 *
 * @author dalvo
 */
public class LoginCheckAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new UserDAO().getUserByLoginAndPassword(login, password);
        if (user == null) {
            request.setAttribute("error", "User not found.");
            new LoginViewAction().execute(request, response);
            return;
        }

        request.getSession().setAttribute("user", user);
        new HomeViewAction().execute(request, response);
    }

}
