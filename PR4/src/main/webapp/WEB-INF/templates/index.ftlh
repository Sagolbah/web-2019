<#import "common.ftl" as c/>

<@c.page>
    <div class="middle">
        <ul class="posts_line">
            <#list posts as p>
                <li>
                    <@c.createPost post=p shouldShorten=true />
                </li>
            </#list>
        </ul>
    </div>
</@c.page>
