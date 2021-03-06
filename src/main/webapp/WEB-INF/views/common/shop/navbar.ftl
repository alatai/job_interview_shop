<div class="ji-nav">
    <div class="ji-container">
        <a href="http://localhost:8080/jishop/index">
            <span><i class="fas fa-home"></i></span>
            JI SHOP ホーム
        </a>

        <#if Session["user"]??>
            <span></span>
            <a href="javascript:">こんにちは、${Session["user"].name}</a>
            <a href="http://localhost:8080/jishop/user/logout">ログアウト</a>
        </#if>

        <#if !Session["user"]>
            <span></span>
            <a href="http://localhost:8080/jishop/user/login">ログイン</a>
            <a href="http://localhost:8080/jishop/user/register">新規登録</a>
        </#if>

        <span class="float-right">
            <a href="http://localhost:8080/jishop/order/orders">注文履歴</a>
            <a href="http://localhost:8080/jishop/order/cart">
                <i class="fas fa-shopping-cart"></i>
                <span>
                    カート <strong id="cartNum" >${Session["cartNum"]}</strong> 件
                </span>
            </a>
        </span>
    </div>
</div>
