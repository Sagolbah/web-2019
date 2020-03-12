<template>
    <div class="middle">
        <Sidebar :posts="posts" :users="users"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts" :users="users"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <AddPost v-if="page === 'AddPost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <UsersList v-if="page === 'UsersList'" :users="users"/>
            <Post v-if="page === 'Post'" :users="users" :post="post" :comments="comments" />
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import AddPost from './middle/AddPost';
    import EditPost from "./middle/EditPost";
    import Sidebar from "./Sidebar";
    import UsersList from "./middle/UsersList";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ['users', 'posts', 'comments'],
        data: function () {
            return {
                page: "Index",
                post: null
            }
        },

        components: {
            Post,
            UsersList,
            Sidebar,
            EditPost,
            Index,
            Enter,
            Register,
            AddPost
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page) => {
                this.page = page;
            });
            this.$root.$on("onOpenPost", (post) => {
                this.post = post;
                this.page = "Post";
            })
        }
    }
</script>

<style scoped>

</style>
