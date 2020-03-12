<#macro header user="">
    <header>
        <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
        <div class="languages">
            <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
            <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
        </div>
        <div class="enter-or-register-box">
            <#if user?has_content>
                <@userlink_handle user=user nameOnly=true />
                |
                <a href="#">Logout</a>
            <#else>
                <a href="#">Enter</a>
                |
                <a href="#">Register</a>
            </#if>
        </div>
        <nav>
            <ul>
                <#list menu as m>
                    <li><@menulink link=m.link name=m.name /></li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro menulink link name>
    <a href="${link}" <#if link == menuCurrent>class="currentMenu"</#if>>${name}</a>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as p>
            <section>
                <div class="header">
                    Post #${p.id}
                </div>
                <div class="body">
                    <@shortenPost text=p.text />
                </div>
                <div class="footer">
                    <a href="post?post_id=${p.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>


<#macro footer>
    <footer>
        <a href="#">Codeforces</a> &copy; 2010-2019 by Mike Mirzayanov
    </footer>
</#macro>

<#macro shortenPost text>
    <#if (text?length > 250)>
        ${text?substring(0, 250)}...
    <#else>
        ${text}
    </#if>
</#macro>

<#macro createPost post shouldShorten>
    <div class="article">
        <div class="title">${post.title}</div>
        <#assign u=findBy(users, "id", post.user_id) />
        <div class="information">By <@userlink_handle user=u nameOnly=false /></div>
        <div class="body">
            <p>
                <#if shouldShorten>
                    <@shortenPost text=post.text />
                <#else>
                    ${post.text}
                </#if>
            </p>
        </div>
        <ul class="attachment">
        </ul>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </div>
</#macro>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    </head>
    <body>

    <#if logged_user_id??>
        <#assign loggedUser=findBy(users, "id", logged_user_id)! />
        <#if loggedUser.handle??>
            <@header user=loggedUser />
        <#else>
            <@header />
        </#if>
    <#else>
        <@header/>
    </#if>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro userlink user>
    <a href="/user?handle=${user.handle}">${user.name}</a>
</#macro>

<#macro userlink_handle user nameOnly>
    <a href="/user?handle=${user.handle}"
            <#if !nameOnly> class="cf_handle" style="color: ${user.color}"</#if> >${user.handle}</a>
</#macro>

<#function findBy items key id>
    <#list items as item>
        <#if item[key]==id>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function findByIndex items key id>
    <#list items as item>
        <#if item[key]==id>
            <#return item?index/>
        </#if>
    </#list>
</#function>

<#function findByCount items key id>
    <#assign res=0>
    <#list items as item>
        <#if item[key]==id>
            <#assign res=res+1 />
        </#if>
    </#list>
    <#return res>
</#function>