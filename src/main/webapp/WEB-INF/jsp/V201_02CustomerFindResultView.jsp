<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先検索結果</title>
</head>
<body>
<jsp:include page="Header.jsp" />
	<div style="text-align:center">
		<h2>得意先検索結果画面</h2>
		<form action="/jsys_sales/jsysFC">
	得意先コード：<c:out value="${requestScope.customer.custCode }" /><br/>
	得意先名：<c:out value="${requestScope.customer.custName }" /><br/>
	電話番号：<c:out value="${requestScope.customer.telNo }" /><br/>
	郵便番号：<c:out value="${requestScope.customer.postalCode }"/><br/>
	住所：<c:out value="${requestScope.customer.address }" /><br/>
	割引率：<c:out value="${requestScope.customer.discountRate }" /><br/>
		</form>
	</div>
	<div style="text-align:right">
		<form action="/jsys_sales/jsysFC" method="post">
			<button type="submit" name="buttonId" value="c101">得意先検索へ</button>
		</form>
	</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先検索結果の操作ヘルプ" />
	<jsp:param name="content" value="検索条件に一致した得意先の詳細情報が表示されています。&lt;br&gt;「得意先検索へ」をクリックすると検索入力画面に戻ることができます。" />
</jsp:include>

</body>
</html>