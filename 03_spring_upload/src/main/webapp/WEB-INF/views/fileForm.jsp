<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="1000" color="red">
		<h2>파일 업로드 </h2>
		<hr width="1000" color="red">
	
		<form action="fileUpload" method="post" enctype="multipart/form-data">
			<input type="text" name="title" size="50" placeholder="제목" maxlength="50"> <br><br>
			<input type="text" name="content" size="50" placeholder="내용" maxlength="50"> <br><br>
			
			첨부파일1 : <input type="file" name="file1"> <br>
			첨부파일2 : <input type="file" name="file2"> <br>
			
			<input type="submit" value="전송">
		</form>
	</div>
</body>
</html>