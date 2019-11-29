<template>
  <div class="students">
    <topbar title="Students" />
    <h3>View all students here. You can also remove students from the system!</h3>
    <cooltable
            v-bind:headers="[
                { name: 'Username', width: '25%' },
                { name: 'First', width: '30%' },
                { name: 'Last', width: '30%' },
                { name: 'Status', width: '15%' }]"
            v-bind:columns="['username', 'firstName', 'lastName',
            {
                property: 'isActiveAccount',
                onChange: statusChanged,
                options: [
                    { value: true, display: 'Active'},
                    { value: false, display: 'Removed' }],
                
            }]"
            v-bind:list="students"
            searchid="username"
        />
  </div>
</template>


<script>


import cooltable from "./CoolTable";
import topbar from "./TopBar";
import AXIOS from "./Axios";

export default {
    name: "students",
    components:
    {
        cooltable,
        topbar
    },
    data: function() {
        return { students: [] };
    },
    created: function()
    {
        AXIOS.get('/students').then(response =>
        {
            this.students = response.data._embedded.students;
            this.students.forEach((student) => student.username = student._links.self.href.substr(student._links.self.href.lastIndexOf('/')+1));
            this.students.sort((a,b) => (a.username > b.username) ? 1 : -1);
        })
        .catch(e => console.log(e.response.data.message));
    },
    methods:
    {
        statusChanged(index)
        {
            AXIOS.post('/students/'.
                    concat(this.students[index].username).
                    concat(this.students[index].isActiveAccount ? '/reactivate' : '/deactivate'),
                    {}, {}
            ).then(r => this.$alert("Successfully changed student account status!", '', 'success'))
            .catch(e => {
            this.$alert("Error changing student account status", '', 'error');
            console.log(e.response.data.message);
        });
        }
    }
}
</script>

<style src="./Style.css" />
