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
                    { value: true, display: 'Active' },
                    { value: false, display: 'Removed' }]
            }]"
            v-bind:list="students"
            searchid="username"
        />
  </div>
</template>


<script>
import cooltable from "./CoolTable";
import topbar from "./TopBar";

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
        const userAction = async () => {
            const response = await fetch('http://localhost:8080/students');
            const myJson = await response.json(); //extract JSON from the http response
            // do something with myJson
            this.students = myJson._embedded.students
            this.students.forEach((student) => student.username = student._links.self.href.substr(student._links.self.href.lastIndexOf('/')+1));
            this.students.sort((a,b) => (a.username > b.username) ? 1 : -1);
        }
        userAction();
    },
    methods:
    {
        statusChanged(index)
        {
            var url = 'http://localhost:8080/students/' + this.students[index].username;
            url += this.students[index].isActiveAccount == "true" ? '/reactivate' : '/deactivate';

            const userAction = async () => {
                const response = await fetch(url, { method: "POST" });
                if (!response.ok)
                    console.log(response);
            }
            userAction();
        }
    }
}
</script>

<style src="./Style.css" />
