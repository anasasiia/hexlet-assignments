package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        if ("list".equals(action)) {
            showArticles(request, response);
        } else {
            showArticle(request, response);
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();
        String query = "SELECT id, title FROM articles ORDER BY id LIMIT 10 OFFSET ?";
        int page = request.getParameter("page") == null
                || Integer.parseInt(request.getParameter("page")) < 1
                ? 1 : Integer.parseInt(request.getParameter("page"));
        int offset = (page - 1) * 10;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title")
                ));
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("articles", articles);
        request.setAttribute("page", page);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Map<String, String> article = new HashMap<>();
        String id = getId(request);
        String query = "SELECT title, body FROM articles WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(Objects.requireNonNull(id)));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                article = Map.of(
                        "body", rs.getString("body"),
                        "title", rs.getString("title")
                );
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        if (article.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
