<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
    </head>
    <body>
            <tr>
            <td>${user.get("id")}</td>
            <br>
            <td>${user.get("firstName")} ${user.get("lastName")}</td>
            <br>
            <td>${user.get("email")}</td>
            <br>
            <td><a href='/users/delete?id=${user.get("id")}'>Delete user</a></td>
            </tr>
    </body>
</html>
<!-- END -->
