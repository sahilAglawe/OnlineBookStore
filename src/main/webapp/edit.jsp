<%@ page import="com.bookstore.model.Book" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>

<%
    Book b = (Book) request.getAttribute("book");
%>

<h2>Edit Book</h2>

<form action="BookServlet" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= b.getId() %>">

    <label>Title:</label>
    <input type="text" name="title" value="<%= b.getTitle() %>" required>
    <br><br>

    <label>Author:</label>
    <input type="text" name="author" value="<%= b.getAuthor() %>" required>
    <br><br>

    <input type="submit" value="Update Book">
</form>

<br>
<a href="BookServlet">⬅ Back to Book List</a>

</body>
</html>
