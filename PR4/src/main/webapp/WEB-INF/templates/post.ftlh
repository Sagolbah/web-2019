<#import "common.ftl" as c/>

<@c.page>
    <#if post_id??>
        <#assign fullPost=c.findBy(posts, "id", post_id)! />
        <#if fullPost.id??>
            <div class="middle">
                <@c.createPost post=fullPost shouldShorten=false />
            </div>
        <#else>
            <h1>No such post</h1>
        </#if>
    </#if>

</@c.page>
