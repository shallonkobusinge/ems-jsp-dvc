
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,400;1,100&display=swap" rel="stylesheet">
    <title>Login</title>
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
    <h2>Login</h2>
    <div class="form-container-sub">
        <label>Username</label>
        <input type="text" name="username" id="username" >
    </div>
    <div class="form-container-sub">
        <label>Password</label>
        <input type="password" name="password" id="password">
    </div>
    <button>Submit</button>

</div>
</body>
</html>