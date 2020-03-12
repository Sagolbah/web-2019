<#import "common.ftl" as c/>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Registered</th>
                <th>Handle</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td>${u.id}</td>
                    <td>2018-09-10 12:32:11</td>
                    <td><@c.userlink_handle user=u nameOnly=false /></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
