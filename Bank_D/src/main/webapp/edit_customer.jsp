<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dhaachuko Bank - Edit Customer</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #000;
            color: white;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            padding: 40px;
            text-align: center;
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
            box-shadow: 0 6px 20px rgba(255, 255, 255, 0.2);
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>Edit Customer</h2>
            <form action="ModifyCustomerServlet" method="post">
                <div class="form-group">
                    <label for="accountNumber">Account Number:</label>
                    <input type="text" class="form-control" id="accountNumber" name="accountNumber" value="<%= request.getAttribute("accountNumber") %>" readonly>
                </div>
                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="<%= request.getAttribute("fullName") %>" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" value="<%= request.getAttribute("address") %>" required>
                </div>
                <div class="form-group">
                    <label for="mobileNo">Mobile Number:</label>
                    <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="<%= request.getAttribute("mobileNo") %>" required>
                </div>
                <div class="form-group">
                    <label for="emailId">Email:</label>
                    <input type="email" class="form-control" id="emailId" name="emailId" value="<%= request.getAttribute("emailId") %>" required>
                </div>
                <div class="form-group">
                    <label for="accountType">Account Type:</label>
                    <select class="form-control" id="accountType" name="accountType" required>
                        <option value="Saving" <%= "Saving".equals(request.getAttribute("accountType")) ? "selected" : "" %>>Saving</option>
                        <option value="Current" <%= "Current".equals(request.getAttribute("accountType")) ? "selected" : "" %>>Current</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="initialBalance">Initial Balance:</label>
                    <input type="text" class="form-control" id="initialBalance" name="initialBalance" value="<%= request.getAttribute("initialBalance") %>" required>
                </div>
                <div class="form-group">
                    <label for="dateOfBirth">Date of Birth:</label>
                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="<%= request.getAttribute("dateOfBirth") %>" required>
                </div>
                <div class="form-group">
                    <label for="idProof">ID Proof:</label>
                    <input type="text" class="form-control" id="idProof" name="idProof" value="<%= request.getAttribute("idProof") %>" required>
                </div>
                <button type="submit" class="btn btn-custom">Update</button>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
