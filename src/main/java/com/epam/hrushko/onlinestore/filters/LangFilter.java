package com.epam.hrushko.onlinestore.filters;

import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LangFilter implements Filter {
    private static final String LANGUAGE = "language";
    private static final String UA = "ua";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestManager requestManager = new RequestManager(request);
        Requests requests = requestManager.createContext();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionLanguage = (String) requests.getSessionAttribute(LANGUAGE);
        if(sessionLanguage == null) {
            requests.addSessionAttribute(LANGUAGE, UA);
            requestManager.updateRequest(requests);
        }

        String requestLanguage = request.getParameter(LANGUAGE);
        if (requestLanguage != null) {
            requests.addSessionAttribute(LANGUAGE, requestLanguage);
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
                .filter(e -> !LANGUAGE.equals(e.getKey()))
                .forEach(e -> requestString.append(e.getKey()).append("=").append(e.getValue()[0]).append("&"));
        requestString.deleteCharAt(requestString.length() - 1);
        return requestString.toString();
    }
}
