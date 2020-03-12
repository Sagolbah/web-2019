package ru.itmo.wp.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import freemarker.template.*;
import ru.itmo.wp.util.ImageUtils;

public class CaptchaFilter extends HttpFilter {
    private Configuration cfg;
    private Template template;

    @Override
    public void init() throws ServletException {
        super.init();
        cfg = new Configuration(Configuration.VERSION_2_3_29);
        String homeDirectory = "/home/sagolbah/SPbITMO/Web/Practices/PR3/src/main/webapp/static/ftl";
        File currentDir = new File(homeDirectory);
        // System.out.println(System.getProperty("user.dir")); returns /usr/share/tomcat9/bin ?
        try {
            cfg.setDirectoryForTemplateLoading(currentDir);
        } catch (IOException e) {
            throw new ServletException("Unable to set freemarker directory", e);
        }
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        try {
            template = cfg.getTemplate("captcha.ftl");
        } catch (IOException e) {
            throw new ServletException("Unable to load template", e);
        }
    }

    private void generateCaptcha(HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        String captchaKey = Integer.toString(new Random().nextInt(900) + 100);
        session.setAttribute("captchaAnswer", captchaKey);
        String base64Picture = Base64.getEncoder().encodeToString(ImageUtils.toPng(captchaKey));
        response.setContentType("text/html");
        Map<String, String> data = new HashMap<>();
        data.put("img", base64Picture);
        try {
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            throw new ServletException("Unable to process template", e);
        }
    }

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if (request.getMethod().equals("POST") && request.getParameter("captchaPassed") == null) {
            String answer = (String) session.getAttribute("captchaAnswer");
            String userInput = request.getParameter("captchaInput");
            if (answer != null && answer.equals(userInput)) {
                session.setAttribute("captchaPassed", "OK");
                // default redirects to index.html
                response.sendRedirect(uri.equals("/") ? "/index.html" : uri);
            } else {
                generateCaptcha(session, response);
            }
        } else if (request.getMethod().equals("GET") && session.getAttribute("captchaPassed") == null) {
            generateCaptcha(session, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
