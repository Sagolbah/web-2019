<template>
    <ul class="posts_line">
        <li v-for="post in posts" v-bind:key="post.id">
            <div class="article">
                <div class="title">{{ post.title }}</div>
                <div class="information">By {{ post.user.name }}</div>
                <div class="body"><p>{{ post.text }}</p></div>
                <div class="footer">
                    <div class="left">
                        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        {{ post.creationTime }}
                        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</template>

<script>

    export default {
        name: "Index",
        props: ['posts']
    }
</script>

<style scoped>
    .post_link {
        text-decoration: none;
    }
</style>
