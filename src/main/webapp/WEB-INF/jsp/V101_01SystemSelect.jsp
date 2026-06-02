<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>システム選択</title>
</head>
<body>
<jsp:include page="Header.jsp" />

<div style="text-align:center">
	<h2>システム選択メニュー</h2>
	<form action="/jsys_sales/jsysFC" method="post">
		<button type="submit" name="buttonId" value="c110">得意先管理システム</button><br>
		<button type="submit" name="buttonId" value="c310">売上集計システム</button><br>
	</form>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="システム選択の操作ヘルプ" />
	<jsp:param name="content" value="利用するシステムを選択してください。<ul><li><b>得意先管理システム</b>：得意先情報の検索、登録、削除などを行います。</li><li><b>売上集計システム</b>：売上情報の集計や確認を行います。</li></ul>" />
</jsp:include>

</body>
</html>
