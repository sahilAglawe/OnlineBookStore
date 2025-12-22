<%@ page import="java.util.*,com.bookstore.model.Book" %>
<html>
<body>

<h2>Add Book</h2>
<form action="BookServlet" method="post">
    <input type="hidden" name="action" value="add">
    Title: <input type="text" name="title">
    Author: <input type="text" name="author">
    <input type="submit" value="Add">
</form>

<hr>

<h2>Book List</h2>
<table border="1">
<tr>
    <th>ID</th><th>Title</th><th>Author</th><th>Action</th>
</tr>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books != null) {
        for (Book b : books) {
%>
<tr>
    <td><%=b.getId()%></td>
    <td><%=b.getTitle()%></td>
    <td><%=b.getAuthor()%></td>
    <td>
        <a href="BookServlet?action=edit&id=<%=b.getId()%>">Edit</a>
        <a href="BookServlet?action=delete&id=<%=b.getId()%>">Delete</a>
    </td>
</tr>
<%
        }
    }
%>
</table>

</body>
</html>
