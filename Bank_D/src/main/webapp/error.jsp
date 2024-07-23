<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dhaachuko Bank - Net Banking and Personal Banking</title>
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
        .container {
            margin-top: 30px;
        }
        h2 {
            color: #004080;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 10px 5px;
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
        /* Box Styles */
        .box {
            padding: 20px;
            margin-bottom: 30px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 64, 128, 0.3);
            transition: transform 0.5s ease;
            text-align: center; /* Center align text */
        }
        .box:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<div class="header">
    <h1 class="display-4">Dhaachuko Bank</h1>
</div>
    <div class="container">
        <h1>Something went wrong</h1>
        <p>We're sorry, but there was an error processing your request.</p>
        <a href="admin_login.jsp" class="btn btn-custom">Go back to Login</a>
    </div>
<!-- Copyright Footer -->
<footer class="footer">
    <p>&copy; 2024 Dhaachuko Bank. All rights reserved.</p>
</footer>

<!-- Bootstrap JS and jQuery (Optional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
