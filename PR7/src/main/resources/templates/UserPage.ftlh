<#-- @ftlvariable name="viewedUser" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if viewedUser??>
        <div class="user_profile">
            <div class="username"><a href="/user/${viewedUser.id}"> ${viewedUser.login}</a></div>
            <ul>
                <li>id: ${viewedUser.id}</li>
                <li>login: ${viewedUser.login}</li>
                <li>registration date: ${viewedUser.creationTime}</li>
            </ul>
        </div>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
