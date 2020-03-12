<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <div class="form-box-talks">
        <div class="header">Create new notice</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="submitArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="content">Text:</label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content"></textarea>
                    </div>
                </div>
                <div class="error"><@c.error "notifyForm.content"/></div>
                <div class="button-field">
                    <input type="submit" value="Submit notification">
                </div>
            </form>
        </div>
    </div>
</@c.page>