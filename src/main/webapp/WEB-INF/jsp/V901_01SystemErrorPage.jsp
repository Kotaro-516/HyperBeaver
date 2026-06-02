<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>システムエラー画面</title>
</head>
<body>
<jsp:include page="Header.jsp" />
<div style="text-align:center">
 <h2>システムエラー画面</h2>
 <br>
 <c:out value="${requestScope.errorMessage}" />
</div>
<form action="/jsys_sales/jsysFC" method="post">
  <button type="submit" name="buttonId" value="c000">ログイン画面へ</button>
</form>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="システムエラーのヘルプ" />
	<jsp:param name="content" value="予期せぬシステムエラー、またはセッション無効化が発生しました。画面に表示されているエラー内容を確認し、解決しない場合はシステム管理者に連絡してください。" />
</jsp:include>

</body>
</html>