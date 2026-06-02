<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先削除結果</title>
</head>
<body>
<jsp:include page="Header.jsp" />
	<div style="text-align:center">
		<h2>得意先削除結果</h2>
		<div style="color:blue; font-weight:bold; margin-bottom:15px;">
			<c:choose>
				<c:when test="${requestScope.result}">
					得意先情報を削除しました。
				</c:when>
				<c:otherwise>
					得意先情報の削除に失敗しました。
				</c:otherwise>
			</c:choose>
		</div>
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
			<button type="submit" name="buttonId" value="c300">得意先削除へ</button>
		</form>
	</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先削除結果の操作ヘルプ" />
	<jsp:param name="content" value="削除条件に一致した得意先の詳細情報が表示されています。<br>「得意先削除へ」をクリックすると削除画面に戻ることができます。" />
</jsp:include>

</body>
</html>