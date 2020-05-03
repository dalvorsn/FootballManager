/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.action.IAction;
import servlet.action.view.account.LoginViewAction;

/**
 *
 * @author dalvo
 */
public class LogoutAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        request.setAttribute("user", null);

        new LoginViewAction().execute(request, response);
    }

}
