/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dalvo
 */
public interface IAction {

    public boolean requiresAuth();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
