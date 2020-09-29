/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.domain.User;
import servlet.action.IAction;
import servlet.action.view.account.LoginViewAction;

/**
 *
 * @author luciano
 */
public class CreateAccountSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String url = request.getParameter("profile_picture");
        
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPasswordHash(password);
        user.setProfilePicture(url);
        user.setIsAdmin(false);

        new UserDAO().insert(user);

        new LoginViewAction().execute(request, response);
    }

}
