<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dhaachuko Bank - Admin Dashboard</title>
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
        .navbar {
            background-color: #004080;
        }
        .navbar-brand, .nav-link {
            color: white !important;
            transition: all 0.3s ease; /* Smooth transition for hover effect */
        }
        .nav-link:hover {
            color: #00aaff !important; /* Hover effect color */
        }
        .nav-box {
            background-color: rgba(0, 64, 128, 0.8); /* Semi-transparent background for the div box */
            border-radius: 10px;
            padding: 10px 20px;
        }
        .container {
            margin-top: 30px;
            text-align: center;
        }
        .content {
            background: rgba(125, 123, 123, 0.1);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(145, 140, 140, 0.2);
            max-width: 800px;
            margin: auto;
        }
        .content h3 {
            margin-bottom: 30px;
            color: #004080;
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
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="admin_dashboard.jsp">Dhaachuko Bank</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse nav-box" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="register_customer.jsp">Register Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="view_customer.jsp">View Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="delete_customer.jsp">Delete Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin_login.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="content">
            <h3>Welcome, <%= session.getAttribute("adminUser") %></h3>
            <p>Use the options to manage the system</p>
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
