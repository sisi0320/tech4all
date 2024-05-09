<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="util.StringUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/headerFooter.css" />
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<script src="https://kit.fontawesome.com/cfef85a76c.js" crossorigin="anonymous"></script>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	<style type="text/css">
	    *{
	 	     margin: 0px auto;  
	     }
	
   </style>
</head>
<body>
<%
	String userSession = (String) session.getAttribute(StringUtils.USER_NAME);
	String cookieUsername = null;
	String cookieSessionID= null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie cookie: cookies){
			if(cookie.getName().equals(StringUtils.USER)) cookieUsername = cookie.getValue();
			if(cookie.getName().equals(StringUtils.JSESSIONID)) cookieSessionID = cookie.getValue();
		}
	}
%>
	<div class="navbar">
		<ul class="nav">
			<div class="navr">
				<a href="/Tech4All/HomeServlet" class="navlink">
					<img src="/Tech4All/Images/T.png">
				</a>
					
				<li class="dropdown">
					<a href="#" class="phone-dropdown">Phone</a>
					<div class="p-dropdown-content">
						<a href="#">Iphone</a>
						<a href="#">Samsung</a>
						<a href="#">Xaomi</a>
						<a href="#">One Plus</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="#" class="access-dropdown">Accessories</a>
					<div class="a-dropdown-content">
						<a href="#">Case</a>
						<a href="#">Charger</a>
						<a href="#">Earphone</a>
						<a href="#">Other</a>
					</div>
				</li>
			</div>
			<div class="navl">
				<li>				
				<form id="searchForm" action="/Tech4All/HomeServlet" method="post">
			    <input type="text" id="search_query" name="search_query" placeholder="Enter search term...">
			    <label for="search_criteria">Search by:</label>
			    <select id="search_criteria" name="search_criteria">
			        <option value="brand">Brand</option>
			        <option value="name">Name</option>
			    </select>
    			
				</form></li>
				<li>
					<a href="${pageContext.request.contextPath}/CartServlet?action=view" class="navlink">Cart</a>
				</li>
				<% if (userSession != null) { %>
                <li class="profile-dropdown">
                	<i class="fa-solid fa-user"></i>
						<div class="drop">
							<a href="/Tech4All/UserDataModify?username=<%=userSession %>">Profile</a>
							<a href="http://localhost:8081//Tech4All/LogOutServlet" id="logoutLink">Log out</a>
						</div>
                	</li>
           		 <% } else { %>
                <li>
                    <a href="/Tech4All/Pages/Login.jsp" class="navlink">Login</a>
                </li>
            <% } %>
			</div>
		
		</ul>
	
	</div>
</body>
<script>
document.getElementById("logoutLink").addEventListener("click", function(event) {
    event.preventDefault(); 
   
    var form = document.createElement("form");
    form.setAttribute("method", "post"); 
    form.setAttribute("action", "/Tech4All/LogOutServlet");

   
    var csrfTokenInput = document.createElement("input");
    csrfTokenInput.setAttribute("type", "hidden");
    csrfTokenInput.setAttribute("name", "csrfToken");
    csrfTokenInput.setAttribute("value", "your_csrf_token_value"); 
    form.appendChild(csrfTokenInput);

   
    document.body.appendChild(form);

    // Submit the form
    form.submit();
});
document.getElementById("searchInput").addEventListener("keydown", function(event) {
    if (event.keyCode === 13) {  // Enter key pressed
        event.preventDefault();  // Prevent form submission
        document.getElementById("searchForm").submit();  // Submit the form
    }
});
</script>
</html>