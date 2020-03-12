<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box-talks">
        <div class="header">Send new message</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="submitMessage"/>
                <div class="field">
                    <div class="name">
                        <label for="user_choose">Choose a user:</label>
                    </div>
                    <div class="value">
                        <select name="target_user">
                            <#list users as u>
                                <#if u.login != user.login>
                                    <option value="${u.id}">${u.login}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="message">Message</label>
                    </div>
                    <div class="value">
                        <textarea id="talk" name="talk"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Send message">
                </div>
            </form>
            <div class="datatable">
                <div class="caption">Received messages</div>
                <table>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Text</th>
                        <th>Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list messages as msg>
                        <tr>
                            <td>${msg.id}</td>
                            <td>${msg.from}</td>
                            <td>${msg.to}</td>
                            <td>${msg.text}</td>
                            <td>${msg.creationTime}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</@c.page>
