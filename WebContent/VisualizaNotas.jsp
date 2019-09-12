<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300i,400" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="VisualizaNotas.css">
</head>
<body>
<div class="container">
<%@ page import="java.io.IOException,
					java.io.PrintWriter,
					java.util.Calendar,
					java.util.List,
					javax.servlet.ServletException,
					javax.servlet.annotation.WebServlet,
					javax.servlet.http.HttpServlet,
					javax.servlet.http.HttpServletRequest,
					javax.servlet.http.HttpServletResponse,
					br.edu.insper.*"%>
<% DAO dao = new DAO();
List<Note> notes = dao.getNotes();
for (Note note : notes) {
%>
  <div class="card">
  	<div class="row">
  		<h3 class="title"><%=note.getName()%></h3>
  	</div>
    <h4 class="label"><%=note.getLabel()%></h4>
    <div class="bar">
      <div class="emptybar"></div>
      <div class="filledbar"></div>
    </div>
    <h5 class="text"><%=note.getText()%></h5>
    
  </div>
<% } %>
</div>
</body>
</html>