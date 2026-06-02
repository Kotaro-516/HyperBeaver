<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>システム選択</title>
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
		border-bottom: 2px solid #3498db;
		padding-bottom: 12px;
		margin-top: 0;
		margin-bottom: 30px;
		font-size: 22px;
	}
	.btn-system {
		display: block;
		width: 80%;
		margin: 15px auto;
		padding: 14px 24px;
		border: none;
		border-radius: 8px;
		font-size: 16px;
		font-weight: bold;
		cursor: pointer;
		text-align: center;
		transition: background-color 0.2s, transform 0.1s, box-shadow 0.2s;
	}
	.btn-system:active {
		transform: scale(0.98);
	}
	.btn-cust {
		background-color: #3498db;
		color: white;
		box-shadow: 0 4px 6px rgba(52, 152, 219, 0.2);
	}
	.btn-cust:hover {
		background-color: #2980b9;
		box-shadow: 0 6px 10px rgba(52, 152, 219, 0.3);
	}
	.btn-summary {
		background-color: #2ecc71;
		color: white;
		box-shadow: 0 4px 6px rgba(46, 204, 113, 0.2);
	}
	.btn-summary:hover {
		background-color: #27ae60;
		box-shadow: 0 6px 10px rgba(46, 204, 113, 0.3);
	}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />

<div class="menu-container">
	<h2>システム選択</h2>
	<form action="/jsys_sales/jsysFC" method="post">
		<button type="submit" name="buttonId" value="c110" class="btn-system btn-cust">得意先管理システム</button>
		<button type="submit" name="buttonId" value="c310" class="btn-system btn-summary">売上集計システム</button>
	</form>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="システム選択の操作ヘルプ" />
	<jsp:param name="content" value="利用するシステムを選択してください。&lt;br&gt;&lt;b&gt;・得意先管理システム&lt;/b&gt;：得意先情報の検索、登録、削除などを行います。&lt;br&gt;&lt;b&gt;・売上集計システム&lt;/b&gt;：売上情報の集計や確認を行います。" />
</jsp:include>

</body>
</html>
