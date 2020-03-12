<template>
    <ul class="posts_line">
        <li v-for="post in reversedLine" v-bind:key="post.id">
            <div class="article">
                <div class="title"><a class="post_link" href="#page=Post" @click="openPost(post)">{{ post.title }}</a>
                </div>
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
            </div>
        </li>
    </ul>
</template>

<script>
    export default {
        name: "Index",
        props: ['posts', 'users'],
        methods: {
            openPost: function (post) {
                this.$root.$emit("onOpenPost", post);
            }
        },
        computed: {
            reversedLine: function () {
                return Object.values(this.posts).reverse();
            }
        }
    }
</script>

<style scoped>
    .post_link {
        text-decoration: none;
    }
</style>
