<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dhaachuko Bank - Admin Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin-bottom: 70px; /* Adjusted to accommodate the footer */
            position: relative; /* Added for positioning */
        }
        .header {
            background-color: #004080;
            color: white;
            padding: 20px;
            text-align: center;
        }
        h3{
        text-align: center;
        }
        .container {
            margin-top: 30px;
        }
        .form-container {
            background: rgba(125, 123, 123, 0.1);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(145, 140, 140, 0.2);
            max-width: 600px;
            margin: auto;
        }
        .form-container .form-group {
            text-align: left;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 10px 0;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2);
            color: white;
        }
        .footer {
            text-align: center;
            padding: 10px 0; /* Reduced padding */
            background-color: #004080;
            color: #fff;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            z-index: 1;
        }
        footer p {
            margin: 0;
            padding: 5px 20px; /* Reduced padding */
            font-size: 12px; /* Smaller font size */
        }
    </style>
</head>
<body>
    <div class="header">
        <h1 class="display-4">Dhaachuko Bank</h1>
    </div>

    <div class="container">
        <div class="form-container">
            <form action="AdminLoginServlet" method="post">
            <h3> Admin Login</h3>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-custom">Login</button>
            </form>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2024 Dhaachuko Bank. All rights reserved.</p>
    </footer>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
