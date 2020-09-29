/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.account;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.action.IAction;

/**
 *
 * @author luciano
 */
public class LoginViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return false;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=login");

        rd.forward(request, response);
    }

}
