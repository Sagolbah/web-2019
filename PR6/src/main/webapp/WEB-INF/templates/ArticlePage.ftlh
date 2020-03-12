<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box-talks">
        <div class="header">Write new article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="submitArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title:</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value="${title!}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="article_text">Text:</label>
                    </div>
                    <div class="value">
                        <textarea id="article_text" name="article_text"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Submit article">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".form-box-talks").submit(function (event) {
                event.preventDefault();
                var title = $(this).find("input[name='title']").val();
                var text = $(this).find("textarea").val();
                ajax({
                        action: "submitArticle",
                        title: title,
                        article_text: text
                    },
                    "POST");
            });
        });
    </script>
</@c.page>
