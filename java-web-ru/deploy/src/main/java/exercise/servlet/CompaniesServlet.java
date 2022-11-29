package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        String queryString = request.getQueryString();
        if (queryString != null && request.getParameter("search") != null) {
            List<String> result = getCompanies().stream()
                    .filter(company -> company.contains(request.getParameter("search"))).toList();
            if (result.isEmpty()) {
                pw.write("Companies not found");
            }
            result.forEach(company -> pw.write(company + "\n"));
        } else {
            getCompanies().forEach(company -> pw.write(company + "\n"));
        }
        // END
    }
}
