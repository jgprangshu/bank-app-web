<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	text-align: left;
}

table#t01 {
	width: 100%;
	background-color: #0ce2f1;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #030303;
}

li {
	float: left;
}

li a {
	display: block;
	color: rgb(248, 224, 9);
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: rgb(240, 104, 25);
}
</style>
</head>
<ul>
	<li style="float: center"><a href="index.html" class="active">Home</a>
	</li>
	<li style="float: center"><a href="account.html" class="active">New
			Account</a></li>
	<li style="float: center"><a href="withdraw.html" class="active">Withdraw</a>
	</li>
	<li style="float: center"><a href="deposit.html" class="active">Deposit</a>
	</li>
	<li style="float: center"><a href="fundtransfer.html"
		class="active">Fund Transfer</a></li>
	<li style="float: center"><a href="checkbalance.html"
		class="active">Check Balance</a></li>
	<li style="float: center"><a href="searchaccount.html">Search
			Account</a></li>
	<li style="float: center"><a href="getAllBankAccounts.jsp"
		class="active">Account Details</a></li>
	<li style="float: center"><a href="deleteaccount.html">Delete
			Details</a></li>
	<li style="float: center"><a href="updatedetails.html">Update
			Details</a></li>

</ul>

<body>

	 <form action="updateAccount.do" METHOD= "post">
   <br>
 </div>
 <fieldset>
  <legend>Update details:</legend>
  Account ID<br> <input type="text" name="accountid" value="${accounts.accountId}"><br>
  <br> Updated name<br> <input type="text" name="updatedname"
   value="${accounts.accountType}" title="Enter valid account type"><br> <br>
  Updated account type<br> <input type="text" name="updatedtype"
   value="${accounts.accountHolderName}"><br> <br> <br>
  <input type="submit" value="Submit" align="center"> <br>
 </fieldset>
 </form>

</body>
</html>

