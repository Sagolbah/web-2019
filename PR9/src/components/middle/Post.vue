<template>
    <div class="article">
        <div class="title">{{ post.title }}</div>
        <div class="information">By {{ users[post.userId].name }}</div>
        <div class="body"><p>{{ post.text }}</p></div>
        <div class="footer">
            <div class="left">
                <img src="../../../public/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="../../../public/img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="../../../public/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="../../../public/img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
        <div class="comments">
            Comments:
            <ul v-if="postComments.length">
                <li v-for="comment in postComments" v-bind:key="comment.id">
                    <p>{{ users[comment.userId].name }}: {{ comment.text }}</p>
                </li>
            </ul>
            <p v-else>None</p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Post",
        props: ['users', 'post', 'comments'],
        computed: {
            postComments: function () {
                return Object.values(this.comments).filter((c) => c.postId === this.post.id);
            }
        }
    }
</script>

<style scoped>
    .comments{
        margin-top: 1rem;
    }
</style>