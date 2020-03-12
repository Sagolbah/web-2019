<#import "common.ftl" as c/>

<@c.page>
    <#if user_id??>
        <div class="middle">
            <ul class="posts_line">
                <#list posts as p>
                    <#if p["user_id"]==user_id>
                        <li><@c.createPost post=p shouldShorten=true /></li>
                    </#if>
                </#list>
            </ul>
        </div>
    </#if>
</@c.page>
