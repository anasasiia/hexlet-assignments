package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;


public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper mapper = new ObjectMapper();
        String content = Files.readString(Path.of("src/main/resources/users.json"));
        return mapper.readValue(content, new TypeReference<List<Map<String, String>>>() {});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | Users</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                        rel="stylesheet"
                        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                        crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                            <tr>
                """);
        for (var user: users) {
            body.append("<td>").append(user.get("id")).append("</td>");
            body.append("<td><a href=\"/users/\"").append(user.get("id")).append(">").append(user.get("firstName"))
                    .append(" ").append(user.get("lastName")).append("</a></td>");
        }
        body.append("""
                    </tr>
                </table>
                """);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder body = new StringBuilder();
        Map<String, String> user = users.stream()
                .filter(u -> Objects.equals(u.get("id"), id))
                .findAny()
                .orElse(null);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        body.append("""
            <!DOCTYPE html>
            <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | Users</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                        rel="stylesheet"
                        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                        crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                            <tr>
                """);

        body.append("<td>" + user.get("id") + "</td>");
        body.append("<td>" + user.get("firstName") + "</td>");
        body.append("<td>" + user.get("lastName") + "</td>");
        body.append("<td>" + user.get("email") + "</td>");
        body.append("""
                </tr>
                </table>
                """);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body.toString());
        }
        // END
    }

