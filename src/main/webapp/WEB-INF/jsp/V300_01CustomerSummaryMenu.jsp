<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上集計メニュー</title>
<style>
	.menu-area {
		text-align: center;
	}

	.menu-buttons {
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 18px;
		margin: 30px auto;
	}

	.menu-buttons button {
		width: 220px;
		padding: 10px 18px;
	}

	.back-button {
		margin-top: 28px;
	}

	.back-button button {
		width: 220px;
		padding: 10px 18px;
	}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />

<div class="menu-area">
	<h2>売上集計メニュー</h2>

	<form action="${pageContext.request.contextPath}/jsysFC" method="post">
		<div class="menu-buttons">
			<button type="submit" name="buttonId" value="c311">月別受注集計</button>
			<button type="button" disabled>年次受注集計</button>
			<button type="button" disabled>商品別受注集計</button>
		</div>
	</form>

	<form class="back-button" action="${pageContext.request.contextPath}/jsysFC" method="post">
		<button type="submit" name="buttonId" value="c100">メインメニューへ戻る</button>
	</form>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="売上集計メニューの操作ヘルプ" />
	<jsp:param name="content" value="「月別受注集計」ボタンを押すと、月別受注集計画面へ遷移します。<br>年次受注集計および商品別受注集計は今回の開発対象外です。" />
</jsp:include>

</body>
</html>
