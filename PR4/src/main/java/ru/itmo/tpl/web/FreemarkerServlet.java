package ru.itmo.tpl.web;

import freemarker.template.*;
import ru.itmo.tpl.util.DataUtil;
import ru.itmo.tpl.util.DebugUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerServlet extends HttpServlet {
    private Configuration freemarkerConfiguration;

    @Override
    public void init() throws ServletException {
        super.init();

        freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_29);

        File freemarkerDirectory = DebugUtil.getFile(getServletContext(), "WEB-INF/templates");
        try {
            freemarkerConfiguration.setDirectoryForTemplateLoading(freemarkerDirectory);
        } catch (IOException e) {
            throw new ServletException("Unable to configure freemarker configuration:"
                    + " freemarkerConfiguration.setDirectoryForTemplateLoading(freemarkerDirectory) failed"
                    + " [freemarkerDirectory=" + freemarkerDirectory + "].", e);
        }

        freemarkerConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfiguration.setLogTemplateExceptions(false);
        freemarkerConfiguration.setWrapUncheckedExceptions(true);
        freemarkerConfiguration.setFallbackOnNullLoopVariable(false);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String uri = request.getRequestURI();
        if (uri.equals("/")) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.sendRedirect("/index");
            uri = "/index";
        }
        Template template;
        try {
            template = freemarkerConfiguration.getTemplate(
                    URLDecoder.decode(uri, StandardCharsets.UTF_8.name()) + ".ftlh");
        } catch (TemplateNotFoundException ignored) {
            // Task 3
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/notfound");
            return;
        }
        response.setContentType("text/html");
        Map<String, Object> data = new HashMap<>();
        // Task 2
        data.put("menuCurrent", uri);
        putData(request, data, response);

        try {
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    private void putData(HttpServletRequest request, Map<String, Object> data, HttpServletResponse response) throws IOException {
        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            if (e.getValue() != null && e.getValue().length == 1) {
                // Task 1 part
                try {
                    data.put(e.getKey(), (e.getKey().endsWith("_id") ? Long.parseLong(e.getValue()[0]) : e.getValue()[0]));
                } catch (NumberFormatException nf) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.sendRedirect("/notfound");
                }
            }
        }

        DataUtil.putData(data);
    }
}
