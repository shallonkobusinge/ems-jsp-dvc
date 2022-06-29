<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,400;1,100&display=swap" rel="stylesheet">
    <title>Register Employee</title>
    <style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        .register-employee-container{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-top: 30px;

        }
        .register-employee-container h2{
            font-size: 20px;
            text-transform: uppercase;
        }
        .register-employee-container-sub{
            display:flex;
            flex-direction: column;
            padding-top: 15px;

        }
        .register-employee-container select{
            width: 20rem;
            height: 2rem;
            padding-left: 10px;
            margin-top: 15px;
        }
        .button{
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

<jsp:include page="../components/Navbar.jsp"></jsp:include>
<div class="register-employee-container">
    <h2>Register Employee</h2>
    <form action="/employees/create" method="post">
        <div class="register-employee-container-sub">
            <label>Department</label>
            <select name="departments">
                <option value="IT">IT</option>
                <option value="FINANCE">FINANCE</option>
                <option value="MARKETING">MARKETING</option>
                <option value="HR">HR</option>
            </select>


        </div>
        <div class="register-employee-container-sub">
            <label>Employee</label>
            <select name="userId">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                </c:forEach>
            </select>

        </div>
        <input type="submit" name="Submit" class="button" />
    </form>


</div>
</body>
</html>