package com.epam.hrushko.onlinestore.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag
 */
public class Tag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger();

    private String year;
    private String project;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder info = new StringBuilder();
        info.append(year).append(" ").append(project);
        try {
            JspWriter out = pageContext.getOut();
            out.write(info.toString());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new JspException();
        }
        return super.doStartTag();
    }
}
