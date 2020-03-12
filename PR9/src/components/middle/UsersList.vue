<template>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <tr class="noData" v-if="Object.entries(users).length === 0 && users.constructor === Object">
                <td colspan="3">
                    No data
                </td>
            </tr>
            <tr v-else v-for="user in users" v-bind:key="user.id">
                <td class="id">{{ user.id }}</td>
                <td class="login">{{ user.login }}</td>
                <td class="name">{{ user.name }}</td>
            </tr>
        </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: "UsersList",
        props: ['users']
    }
</script>

<style scoped>

</style>