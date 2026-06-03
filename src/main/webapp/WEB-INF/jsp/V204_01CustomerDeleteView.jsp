<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先削除画面</title>
</head>
<body>
<jsp:include page="Header.jsp" />
  <div style="text-align:center; max-width: 600px; margin: 0 auto;">
   <h2>得意先削除</h2><br>
   得意先コードを入力して、検索ボタンをクリックしてください。
   <div style="text-align:center; color:red; font-weight:bold; margin-bottom: 15px;">
   <%--エラーメッセージがある場合、出力--%>
    <c:out value="${requestScope.errorMessage }" />
    <c:forEach var="message" items="${requestScope.errorMessageList}">
     <c:out value="${message}" /><br>
    </c:forEach>
   </div>

   <form action="/jsys_sales/jsysFC" method="post">
    <!-- 得意先コード入力 & 検索ボタン -->
    <div style="margin-bottom: 20px;">
     得意先コード：
     <input type="text" name="custCode" value="<c:out value="${requestScope.customer.custCode}" default="${param.custCode}"/>" placeholder="例：KA0001" style="padding: 6px; font-size: 14px;">
     <button type="submit" name="buttonId" value="c302" style="padding: 6px 14px; font-size: 14px; cursor: pointer;">検索</button>
    </div>

    <!-- 得意先情報表示フォーム（編集不可） -->
    <table style="margin: 0 auto; text-align: left; border-collapse: separate; border-spacing: 0 10px;">
     <tr>
      <td style="padding-right: 15px; font-weight: bold;">得意先名：</td>
      <td>
       <input type="text" name="custName" value="<c:out value="${requestScope.customer.custName}"/>" readonly style="width: 250px; padding: 6px; background-color: #e3f2fd; border: 1px solid #90caf9; border-radius: 4px;">
      </td>
     </tr>
     <tr>
      <td style="padding-right: 15px; font-weight: bold;">電話番号：</td>
      <td>
       <input type="text" name="telNo" value="<c:out value="${requestScope.customer.telNo}"/>" readonly style="width: 150px; padding: 6px; background-color: #e3f2fd; border: 1px solid #90caf9; border-radius: 4px;">
      </td>
     </tr>
     <tr>
      <td style="padding-right: 15px; font-weight: bold;">郵便番号：</td>
      <td>
       <input type="text" name="postalCode" value="<c:out value="${requestScope.customer.postalCode}"/>" readonly style="width: 100px; padding: 6px; background-color: #e3f2fd; border: 1px solid #90caf9; border-radius: 4px;">
      </td>
     </tr>
     <tr>
      <td style="padding-right: 15px; font-weight: bold;">住所：</td>
      <td>
       <input type="text" name="address" value="<c:out value="${requestScope.customer.address}"/>" readonly style="width: 300px; padding: 6px; background-color: #e3f2fd; border: 1px solid #90caf9; border-radius: 4px;">
      </td>
     </tr>
     <tr>
      <td style="padding-right: 15px; font-weight: bold;">割引率：</td>
      <td>
       <input type="text" name="discountRate" value="<c:out value="${requestScope.customer.discountRate}"/>" readonly style="width: 60px; padding: 6px; background-color: #e3f2fd; border: 1px solid #90caf9; border-radius: 4px; text-align: right;"> ％
      </td>
     </tr>
    </table>

    <!-- 削除ボタン -->
    <div style="margin-top: 25px; margin-bottom: 20px;">
     <button type="submit" name="buttonId" value="c301" ${empty requestScope.customer ? 'disabled' : ''} style="padding: 10px 24px; font-size: 15px; font-weight: bold; cursor: pointer;">削除</button>
    </div>
   </form>

   <!-- メニューに戻るボタン -->
   <div style="text-align:right;">
    <form action="/jsys_sales/jsysFC" method="post">
     <button type="submit" name="buttonId" value="c110" style="padding: 8px 16px; cursor: pointer;">得意先管理メニューへ</button>
    </form>
   </div>
  </div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先削除の操作ヘルプ" />
	<jsp:param name="content" value="削除したい得意先の「得意先コード（半角英数字6桁）」を入力し、「検索」ボタンをクリックしてください。<br>得意先情報が表示されたら、内容を確認し「削除」ボタンをクリックして削除を実行してください。" />
</jsp:include>

</body>
</html>