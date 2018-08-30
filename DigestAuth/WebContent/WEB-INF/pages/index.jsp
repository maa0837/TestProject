<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/NestServices/getBatchId" method="POST">
			<table cellpadding="7" cellspacing="0" border="1" align="center">
				<tr>
					<td>Batch Id :</td>
					<td><input type="text" name="batchId"></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="submit" value="Hit Me !"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>