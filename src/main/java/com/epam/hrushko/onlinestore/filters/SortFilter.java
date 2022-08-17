package com.epam.hrushko.onlinestore.filters;

import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Language filter class
 */
public class SortFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SortFilter.class);
    private static final String SORT = "sort";
    private static final String PERC = "percent";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestManager requestManager = new RequestManager(request);
        Requests requests = requestManager.createContext();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionSort = (String) requests.getSessionAttribute(SORT);
        if(sessionSort == null) {
            requests.addSessionAttribute(SORT, PERC);
            requestManager.updateRequest(requests);
        }

        String requestSort = request.getParameter(SORT);
        if (requestSort != null) {
            requests.addSessionAttribute(SORT, requestSort);
            String requestString = remove(request);
            requestManager.updateRequest(requests);
            response.sendRedirect(requestString);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private String remove(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder requestString = new StringBuilder(request.getContextPath() + "/onlineStore?");
        parameterMap.entrySet().stream()
                .filter(e -> !SORT.equals(e.getKey()))
                .forEach(e -> requestString.append(e.getKey()).append("=").append(e.getValue()[0]).append("&"));
        requestString.deleteCharAt(requestString.length() - 1);
        return requestString.toString();
    }
}
