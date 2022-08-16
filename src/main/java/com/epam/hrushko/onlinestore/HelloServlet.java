package com.epam.hrushko.onlinestore;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandFactory;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Main servlet
 */
public class HelloServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String COMMAND = "command";
    private static final String PATH = "/onlineStore?";
    private static final String HOME = "command=home";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
            super.destroy();
        } catch (ConnectionException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);

        if (commandName == null || "".equals(commandName)) {
            response.sendRedirect(PATH + HOME);
        } else {
            Command command = CommandFactory.getInstance().getCommand(commandName);
            RequestManager manager = new RequestManager(request);

            CommandResult result = command.execute(manager, response);
            dispatch(result, request, response);
        }
    }

    private void dispatch(CommandResult result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (result.isRedirect()) {
            response.sendRedirect(PATH + result.getPage());
        } else {
            request.getRequestDispatcher(result.getPage()).forward(request, response);
        }
    }
}