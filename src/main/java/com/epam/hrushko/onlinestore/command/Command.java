package com.epam.hrushko.onlinestore.command;

import com.epam.hrushko.onlinestore.command.request.RequestManager;

import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(RequestManager manager, HttpServletResponse response);

}
