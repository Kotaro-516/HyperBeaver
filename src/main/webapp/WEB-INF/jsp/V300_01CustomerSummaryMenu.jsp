<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上集計メニュー</title>
</head>
<body>
<jsp:include page="Header.jsp" />

<div style="text-align:center">
	<h2>売上集計メニュー</h2>
	<p>※ 売上集計システムは現在開発中です。</p>
	<form action="/jsys_sales/jsysFC" method="post">
		<button type="submit" name="buttonId" value="c100">メインメニューへ戻る</button>
	</form>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="売上集計メニューの操作ヘルプ" />
	<jsp:param name="content" value="売上集計システムの初期画面です。現在は開発中のため、操作を行うことはできません。<br>「メインメニューへ戻る」ボタンを押して、システム選択画面にお戻りください。" />
</jsp:include>

</body>
</html>
