<template>
    <div class="bar">
        <table>
            <tr>
                <td style="text-align: left">
                    <h2>TutoringTurtles {{ login != undefined ? '(' + login + ')' : '' }} | {{ title }}</h2> 
                </td>
                <td class="item" v-for="item in items" v-bind:key="item.link">
                    <button
                        v-if="item.name != null"
                        class="item"
                        v-on:click="goto(item.link)"
                        v-bind:style="goto().endsWith(item.link) ? 'opacity: 1' : ''">
                            {{ item.name }}
                    </button>
                    <div v-else class="item" />
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
import VueCookies from 'vue-cookies'

export default
{
    name: 'bar',
    components: { VueCookies },
    props:
    {
        title: { type: String, required: true }
    },
    data: function()
    {
        return {
            login: VueCookies.get('ManagerName'),
            items: [
                { name: 'Home', link: '#/home' },
                { name: 'Courses Subjects Schools', link: '#/css' },
                { name: 'Tutors', link: '#/tutors' },
                { name: 'Students', link: '#/students' },
                { name: 'Evaluations', link: '#/evaluations' },
                { name: 'Tutoring Sessions', link: '#/tutoringsessions' },
                { name: null, link: null },
                { name: 'Sign Out', link: '#/signin' }
        ]};
    },
    created: function()
    {
        if (location.href.endsWith('#/home'))
            this.items = [ this.items.pop() ];      // Just signout button
        if (location.href.endsWith('#/signin'))
            this.items = [];    // Clear all
        else if (this.login == undefined)
            location.href = '#/signin';     // Redirect to Signin

    },
    methods:
    {
        goto: function(link)
        {
            if (link == undefined)
                return location.href;
            else
                location.href = link
        }
    }
}
</script>

<style scoped>
.bar {
    background-color:#5c9bb7;

    background-image:-webkit-linear-gradient(top, #5c9bb7, #5392ad);
    background-image:-moz-linear-gradient(top, #5c9bb7, #5392ad);
    background-image:linear-gradient(top, #5c9bb7, #5392ad);
    box-shadow: 0 1px 1px #ccc;
    border-radius: 2px;
    
    width: 100%;
    padding: 5px;
    margin-top: 0px;
    margin-bottom: 20px;
    line-height: 1;
    
}

.bar table {
    width: 100%;
    margin-right: 0px;
    margin-left: 0px;
}

.bar .item {
    width:100px;
    height:60px;
}

.bar button {
    opacity: 0.6;
}

.bar h2{
    display: block;
    margin: 10px;
    margin-right: auto;
    font-weight: bold;
    font-size: 20px;
    color:#FFFFFF;
}
</style>