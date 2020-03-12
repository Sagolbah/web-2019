<#-- @ftlvariable name="viewedPost" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <#if viewedPost??>
        <@c.post post=viewedPost />

        <div class="comments">
            Comments:
            <#if viewedPost.getComments()?size == 0>
                <p>None</p>
            <#else>
                <ul>
                    <#list viewedPost.comments as comment>
                        <li>${comment.user.login}: ${comment.text}</li>
                    </#list>
                </ul>
            </#if>
        </div>
        <#if user??>
            <div class="form-box-talks">
                <div class="header">Comment this post</div>
                <div class="body">
                    <form method="post" action="">
                        <input type="hidden" name="action" value="submitComment"/>
                        <div class="field">
                            <div class="name">
                                <label for="text">Comment:</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text"></textarea>
                            </div>
                        </div>
                        <div class="error"><@c.error "commentForm.text"/></div>
                        <div class="button-field">
                            <input type="submit" value="Submit comment">
                        </div>
                    </form>
                </div>
            </div>
        </#if>
    <#else>
        <h1>No such post</h1>
    </#if>
</@c.page>