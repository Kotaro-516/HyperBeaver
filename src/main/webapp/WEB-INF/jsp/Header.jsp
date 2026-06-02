<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- === 共通ヘッダー（ハンバーガーメニュー統合版） === -->
<style>
	.global-header {
		width: 100%;
		background-color: #ffffff;
		border-bottom: 1px solid #e2e8f0;
		padding: 10px 24px;
		display: flex;
		justify-content: space-between; /* 左右に分散 */
		align-items: center;
		box-sizing: border-box;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
		margin-bottom: 25px;
		font-family: "Helvetica Neue", Arial, "Hiragino Kaku Gothic ProN", Meiryo, sans-serif;
		position: relative;
		z-index: 100;
	}
	.header-left {
		display: flex;
		align-items: center;
	}
	.header-right {
		display: flex;
		align-items: center;
	}
	.header-user-info {
		font-size: 13px;
		color: #4a5568;
	}
	.header-user-name {
		font-weight: bold;
		color: #1a202c;
		margin-right: 5px;
	}
	.header-user-code {
		color: #718096;
		font-size: 12px;
	}

	/* === ハンバーガーメニューのスタイル === */
	.hamburger {
		width: 22px;
		height: 18px;
		cursor: pointer;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		padding: 8px 10px;
		background: #ffffff;
		border: 1px solid #cbd5e1;
		border-radius: 6px;
		transition: background 0.3s ease, border-color 0.3s ease, transform 0.2s ease;
		z-index: 1001;
	}
	.hamburger:hover {
		background: #f8fafc;
		border-color: #94a3b8;
		transform: scale(1.03);
	}
	.hamburger span {
		display: block;
		width: 100%;
		height: 2px;
		background: #475569;
		border-radius: 2px;
		transition: 0.3s ease;
	}

	/* ハンバーガーの変形（X印） */
	.hamburger.active span:nth-child(1) {
		transform: translateY(8px) rotate(45deg);
	}
	.hamburger.active span:nth-child(2) {
		opacity: 0;
	}
	.hamburger.active span:nth-child(3) {
		transform: translateY(-8px) rotate(-45deg);
	}

	/* ドロップダウンメニュー (左端基準) */
	.mobile-menu {
		position: absolute;
		top: 55px;
		left: 24px;
		width: 180px;
		max-height: 0;
		overflow: hidden;
		display: flex;
		flex-direction: column;
		gap: 6px;
		background: #ffffff;
		border-radius: 8px;
		box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1), 0 2px 5px rgba(0, 0, 0, 0.05);
		border: 0 solid #e2e8f0;
		padding: 0 12px;
		transition: max-height 0.3s ease, padding 0.3s ease, border-width 0.1s ease;
		z-index: 1000;
	}
	.mobile-menu.show {
		max-height: 150px;
		padding: 12px;
		border-width: 1px;
	}
	.mobile-menu a {
		color: #475569;
		text-decoration: none;
		padding: 8px 12px;
		font-size: 14px;
		font-weight: bold;
		border-radius: 6px;
		background: #f8fafc;
		border: 1px solid #e2e8f0;
		transition: color 0.2s ease, background 0.2s ease, transform 0.2s ease;
		text-align: left;
	}
	.mobile-menu a:hover {
		background: #f1f5f9;
		border-color: #cbd5e1;
		color: #3498db;
		transform: translateX(3px);
	}
</style>

<div class="global-header">
	<div class="header-left">
		<!-- ハンバーガーボタン -->
		<div class="hamburger" id="hamburger">
			<span></span>
			<span></span>
			<span></span>
		</div>
	</div>
	
	<div class="header-right">
		<div class="header-user-info">
			<c:choose>
				<c:when test="${not empty sessionScope.loginEmployee}">
					ログイン中：<span class="header-user-name"><c:out value="${sessionScope.loginEmployee.empName}" /></span> さん 
					<span class="header-user-code">（従業員番号：<c:out value="${sessionScope.loginEmployee.empNo}" />）</span>
				</c:when>
				<c:otherwise>
					<span style="color: #dc3545; font-weight: bold;">ログインしていません</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- ドロップダウンメニュー -->
	<nav class="mobile-menu" id="mobileMenu">
		<a href="/jsys_sales/jsysFC?buttonId=c100" id="mainMenuLink">メインメニュー</a>
		<a href="/jsys_sales/jsysFC?buttonId=c002" id="logoutLink">ログアウト</a>
	</nav>
</div>

<!-- ハンバーガー制御のJSスクリプト -->
<script>
	(function() {
		const hamburger = document.getElementById('hamburger');
		const mobileMenu = document.getElementById('mobileMenu');

		if (hamburger && mobileMenu) {
			// 1. 開閉の切り替え
			hamburger.addEventListener('click', (e) => {
				e.stopPropagation();
				hamburger.classList.toggle('active');
				mobileMenu.classList.toggle('show');
			});

			// 2. メニュー外クリック時に閉じる
			document.addEventListener('click', (e) => {
				if (!mobileMenu.contains(e.target) && !hamburger.contains(e.target)) {
					mobileMenu.classList.remove('show');
					hamburger.classList.remove('active');
				}
			});
		}
	})();
</script>
