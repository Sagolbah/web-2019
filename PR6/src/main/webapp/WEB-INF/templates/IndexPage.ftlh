<#import "commons.ftlh" as c>

<@c.page>
    Welcome on Codeforces!

    <template class="article-item">
        <li>
            <div class="article">
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
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
                        <span class="time"></span>
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </div>
        </li>
    </template>

    <div class="posts_line">
        <ul>
            <li class="no-data"></li>
        </ul>
    </div>

    <script>
        $(function () {
            var $noData = $(".posts_line .no-data");
            $noData.text("Loading...");
            ajax(
                {
                    action: "findArticles"
                },
                "POST",
                function (response) {
                    var $ul = $(".posts_line ul");
                    console.log(response["articles"]);
                    if (response["articles"].length === 0) {
                        $noData.text("No posts currently.");
                    } else {
                        response["articles"].forEach(function (article) {
                            $noData.hide();
                            var $li = $($(".article-item").prop("content")).clone();
                            $li.find(".title").text(article["title"]);
                            $li.find(".information").text("By ID: " + article["userId"]);
                            $li.find(".body").text(article["text"]);
                            $li.find(".time").text(article["creationTime"]);
                            $ul.append($li);
                        });
                    }
                }
            );
            $("aside section").click(function () {
                $(this).css("background-color", "red");
            });
        });

    </script>
</@c.page>
