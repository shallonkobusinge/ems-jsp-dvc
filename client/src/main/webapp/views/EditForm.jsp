<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Form</title>
    <style>

        @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,400;1,100&display=swap');
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        .form-container{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
            margin-left: 10px;
            margin-right: 10px;

        }
        .form-register-sub{
            display: flex;
            flex-direction: row;
            gap: 20px;


        }
        .form-container h2{
            font-size: 25px;
            text-transform: uppercase;

        }
        .form-container-sub{
            display: flex;
            flex-direction: column;
            margin-top: 20px;
        }
        .form-container-sub input{
            width: 20rem;
            height: 3rem;
            background: rgba(148, 164, 220, 0.25);
            outline: 0;
            border:none;
            padding-left: 10px;
        }
        .form-container-sub label{
            font-size: 16px;
            font-family: 'Poppins', sans-serif;
        }
        .form-container button{
            width: 14rem;
            height: 3rem;
            background: #496CE8;
            color: white;
            border: none;
            outline: 0;
            font-size: 16px;
            margin-top: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="components/Navbar.jsp"></jsp:include>
<div class="form-container">
    <h2>Register</h2>
        <form action="/edit?id=${employee.user.id}" method="post" >
            <div class="form-register-sub">
                <div class="form-container-sub">
                    <label>First Name</label>
                    <input type="text" name="firstName"  value="${employee.user.firstName}">
                </div>
                <div class="form-container-sub">
                    <label>Last Name</label>
                    <input type="text" name="lastName" value="${employee.user.lastName}">
                </div>
            </div>
            <div class="form-register-sub">
                <div class="form-container-sub">
                    <label>Email</label>
                    <input type="text" name="email"  value="${employee.user.email}">
                </div>
                <div class="form-container-sub">
                    <label>Phone</label>
                    <input type="text" name="phone" value="${employee.user.phone}">
                </div>
            </div>
            <div class="form-register-sub">
                <div class="form-container-sub">
                    <label>Username</label>
                    <input type="text" name="username" id="username" value="${employee.user.username}">
                </div>
                <div class="form-container-sub">
                    <label>Password</label>
                    <input type="password" name="password" id="password" value="${employee.user.password}">
                </div>
                <div class="register-employee-container-sub">
                    <label>ROLE</label>
                    <select name="role">
                        <option value="${employee.user.role}">${employee.user.role}</option>

                    </select>
                </div>
            </div>
            <button>Submit</button>

        </form>

</div>

</body>
</html>