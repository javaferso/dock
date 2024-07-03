<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.getSession().invalidate();
    // Redirige al usuario a la página de login
    response.sendRedirect("login.jsp");
%>


