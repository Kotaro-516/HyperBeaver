<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先管理メニュー</title>
<style>
	.menu-area {
		text-align: center;
	}

	.menu-buttons {
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 16px;
		margin: 30px auto;
	}

	.menu-buttons button {
		width: 220px;
		padding: 10px 18px;
	}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />

<div class="menu-area">
	<h2>得意先管理メニュー</h2>

	<form action="${pageContext.request.contextPath}/jsysFC" method="post">
		<div class="menu-buttons">
			<button type="submit" name="buttonId" value="c101">得意先検索</button>
			<button type="submit" name="buttonId" value="c200">得意先登録</button>
			<button type="submit" name="buttonId" value="c300">得意先削除</button>
			<button type="submit" name="buttonId" value="c400" disabled="disabled">得意先変更</button>
			<button type="submit" name="buttonId" value="c500">得意先一覧</button>
			<button type="submit" name="buttonId" value="c100">メインメニューへ戻る</button>
		</div>
	</form>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先管理メニューの操作ヘルプ" />
	<jsp:param name="content" value="得意先管理の各メニュー（検索・登録・削除）へ遷移するための画面です。行いたい操作のボタンをクリックしてください。" />
</jsp:include>

</body>
</html>
