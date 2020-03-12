<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Manage view</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>
                        <form method="post" action="">
                            <#if article.hidden>
                                <input type="hidden" name="action" value="show"/>
                                <button type="submit" name="show" value=${article.id}>Show</button>
                            <#else>
                                <input type="hidden" name="action" value="hide"/>
                                <button type="submit" name="hide" value=${article.id}>Hide</button>
                            </#if>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            $("tbody").submit(function (event) {
                event.preventDefault();
                var $btn = $(this).find("button");
                var id = $btn.val();
                var act = $btn.attr("name");
                var newact = act === "show" ? "hide" : "show";
                ajax(
                    {
                        action: act,
                        articleId: id
                    },
                    "POST",
                    function (response) {
                        $btn.attr("name", newact);
                    }
                )
            })
        })
    </script>
</@c.page>
