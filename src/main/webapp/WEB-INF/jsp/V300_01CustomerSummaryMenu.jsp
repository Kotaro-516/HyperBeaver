<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上集計メニュー</title>
<style>
	/* 基本スタイル */
	body {
		font-family: "Hiragino Kaku Gothic ProN", "Meiryo", sans-serif;
		background-color: #f4f6f9;
		margin: 0;
		padding: 0;
		color: #333;
	}
	.menu-container {
		text-align: center;
		background-color: #ffffff;
		padding: 40px;
		border-radius: 12px;
		box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
		max-width: 500px;
		margin: 40px auto;
	}
	h2 {
		color: #2c3e50;
		border-bottom: 2px solid #2ecc71;
		padding-bottom: 12px;
		margin-top: 0;
		margin-bottom: 20px;
		font-size: 22px;
	}
	.dev-message {
		font-size: 15px;
		color: #e67e22;
		font-weight: bold;
		margin-bottom: 30px;
		background-color: #fff3e0;
		padding: 15px;
		border-radius: 8px;
		border: 1px dashed #ffe0b2;
	}
	.btn-menu {
		padding: 12px 24px;
		border: none;
		border-radius: 6px;
		font-size: 14px;
		font-weight: bold;
		cursor: pointer;
		background-color: #94a3b8;
		color: white;
		box-shadow: 0 4px 6px rgba(148, 163, 184, 0.2);
		transition: background-color 0.2s, transform 0.1s;
	}
	.btn-menu:hover {
		background-color: #64748b;
	}
	.btn-menu:active {
		transform: scale(0.98);
	}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />

<div class="menu-container">
	<h2>売上集計メニュー</h2>
	<div class="dev-message">
		※ 売上集計システムは現在開発中です。
	</div>
	<form action="/jsys_sales/jsysFC" method="post">
		<button type="submit" name="buttonId" value="c100" class="btn-menu">メインメニューへ戻る</button>
	</form>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="売上集計メニューの操作ヘルプ" />
	<jsp:param name="content" value="売上集計システムの初期画面です。現在は開発中のため、操作を行うことはできません。&lt;br&gt;「メインメニューへ戻る」ボタンを押して、システム選択画面にお戻りください。" />
</jsp:include>

</body>
</html>
