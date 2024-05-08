<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.StringUtils" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
    <link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/userprofile.css" />
</head>

<body>
	<jsp:include page="Header.jsp"/>
    <div class="container">
        <h4>Account settings</h4>
        <div class="card">
            <div class="row">
                <div class="sideNav">
                    <div class="list">
                        <a class="navlist active"id="general" href="#">General</a>
                        <a class="navlist" id="change-password"href="#">Change password</a>
                        <a class="navlist" id="#account-info"href="">My Cart</a>
                        <a class="navlist" id="account-connections"href="#">My Order History</a>
                    </div>
                </div>
                <div class="contents">
                    <div class="card-body">
                        <div class="account-general" id="account-general">                      
                            <form class="user" action = "/Tech4All/UserDataModify?username=${user.username}" method="post" enctype="multipart/form-data">
                            								
								<div class="image_div">
									<img src="/Tech4All/resources/Images/Users/${user.pfpUrl}" alt="Profile Picture">
									<a class="editImg" id="editImg" href="#">Change Profile Picture</a>
									<input type="file" id="new_image" name="image" accept="image/*" >
								</div>
								<div class=" details">
									<div class="form_row">
										<label for="username">UserName:</label> <input type="text" id="username" 
										name="username" value = "${user.username}">
									</div>
									<div class="form_row">
										<label for="firstName">First Name:</label> <input type="text" id="firstName" name="firstName" 
										value = "${user.firstName}">
									</div>
									<div class="form_row">
										<label for="lastName">Last Name:</label> <input type="text"	id="lastName"
										 name="lastName" value = "${user.lastName}">
									</div>
									<div class="form_row">
										<label for="birthday">Birthday:</label> <input type="date" id="birthday"
										 name="birthday"  value = "${user.dob}" >
									</div>
									<div class="form_row">
										<label for="gender">Gender:</label> 
										<select id="gender"name="gender"  ><option value="male"  
										${user.gender == 'male' ? 'selected' : ''}>Male</option>
										<option value="female" ${user.gender == 'female' ? 'selected' : ''}>
										Female</option></select>
									</div>
									<div class="form_row">
										<label for="email">Email:</label> <input type="email" id="email"
										name="email"  value = "${user.email}" >
									</div>	
									<div class="form_row">
										<label for="phoneNumber">Phone Number:</label> <input type="tel" id="phoneNumber"
										 name="phoneNumber"  value = "${user.phoneNumber}" >
									</div>
									<div class="form_row">
										<label for="subject">Address:</label> <input type="addr" id="address"
										name="address"  value="${user.address}" >
									</div>
									<center><button type="submit">Save Changes</button>
										<button type="submit">Cancel</button></center>  	
									</div>	
						
							</form>
                            </div>

                        <div class="account-change-password" id="account-change-password" style="display:none">
                            <div class="card-body">
                                <div class="form-group">
                                    <label>Current password</label>
                                    <input type="password">
                                </div>
                                <div class="form-group">
                                    <label>New password</label>
                                    <input type="password">
                                </div>
                                <div class="form-group">
                                    <label>Repeat new password</label>
                                    <input type="password">
                                </div>
                            </div>
                        </div>
                        <div class="account-info" id="account-info">
                            <div class="card-body">
                                <!-- Content for account info -->
                            </div>
                        </div>
                        <div class="account-connections" id="account-connections">
                            <div class="lists">
                                <div class="container">
                                    <!-- Content for account connections -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
    <script>
     document.getElementById("editImg").addEventListener("click", function(){
	        var imageInput = document.getElementById("new_image");
	        if (imageInput.style.display === "none" || imageInput.style.display === "") {
	            imageInput.style.display = "block";
	        } else {
	            imageInput.style.display = "none";
	        }
	    });
     document.getElementById("general").addEventListener("click", function(){
	        var imageInput = document.getElementById("account-general");
	        if (imageInput.style.display === "none" || imageInput.style.display === "") {
	            imageInput.style.display = "block";
	            document.getElementById("account-change-password").style.display = "none";
	        } else {
	            imageInput.style.display = "none";
	        }
	    });
     document.getElementById("change-password").addEventListener("click", function(){
	        var imageInput = document.getElementById("account-change-password");
	        if (imageInput.style.display === "none" || imageInput.style.display === "") {
	            imageInput.style.display = "block";
	            document.getElementById("account-general").style.display = "none";
	        } else {
	            imageInput.style.display = "none";
	        }
	    });
	 </script>
</body>

</html>