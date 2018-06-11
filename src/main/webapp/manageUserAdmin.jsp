<%-- 
    Document   : manageUserAdmin
    Created on : May 6, 2018, 2:34:24 PM
    Author     : ajay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<!DOCTYPE html>  
<html>  
    <head>   
        <title>New Employee</title>  
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box}

            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }

            input[type=text]:focus, input[type=password]:focus {
                background-color: #ddd;
                outline: none;
            }

            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }

            /* Set a style for all buttons */
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
            }

            button:hover {
                opacity:1;
            }

            /* Extra styles for the cancel button */
            .cancelbtn {
                padding: 14px 20px;
                background-color: #f44336;
            }

            /* Float cancel and signup buttons and add an equal width */
            .cancelbtn, .signupbtn {
                float: left;
                width: 50%;
            }

            /* Add padding to container elements */
            .container {
                padding: 16px;
                width: 40%;
            }

            /* Clear floats */
            .clearfix::after {
                content: "";
                clear: both;
                display: table;
            }

            /* Change styles for cancel button and signup button on extra small screens */
            @media screen and (max-width: 300px) {
                .cancelbtn, .signupbtn {
                    width: 100%;
                }
            }
        </style>      
        <script>
            function validateForm() {
                var x = document.forms["myForm"]["name"].value;
                if (x == "") {
                    alert("Name must be filled out");
                    return false;
                }
                var y = document.forms["myForm"]["password"].value;
                if (y == "") {
                    alert("Passward must be filled out");
                    return false;
                }
                var z = document.forms["myForm"]["email"].value;
                if (z == "") {
                    alert("email must be filled out");
                    return false;
                }
            }
        </script>
    </head>  
    <body>  
        <center>
            <h1>Add New Candidate</h1>  
            <form name="myForm" action="SaveServlet" method="post" onsubmit="return validateForm()" style="border:1px solid #ccc">  
                <div class="container" >
                    <!--                    <table>  
                                            <tr><td>Name:</td><td><input type="text" name="name"/></td></tr>  
                                            <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>  
                                            <tr><td>Email:</td><td><input type="email" name="email"/></td></tr>  
                    
                                            <tr><td colspan="2"><input type="submit" value="Save Employee"/></td></tr>  
                                        </table>  -->


                    <b>Name</b>
                    <input type="text" placeholder="Enter Name" name="name" required>

                    <b>Email</b><br>
                    <input type="text" placeholder="Enter Email" name="email" required>

                    <b>Password</b><br>
                    <input type="password" placeholder="Enter Password" name="pass" required>
                    <div class="clearfix" >
                        
                        <button type="submit">Save Candidate</button>                    </div>

                </div>
            </form>  

            <br/>  

        </center>
        <a href="ViewServlet" style="float: right; text-decoration: none; padding: 15px; background-color: green; color: #FFF; font-size: 20px">view Candidate </a>  
    </body>  
</html>
