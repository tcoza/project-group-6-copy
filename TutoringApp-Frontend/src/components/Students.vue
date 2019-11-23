<template>
  <div class="students">
    <div class="bar">
        <table>
                <td style="width:30%">
                    <h2> TutoringTurtles | Students </h2> 
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/home'">Home</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/css'">Courses Subjects Schools</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/tutors'">Tutors</button>
                </td >
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;opacity:1;" onclick="location.href='#/students'"  disabled=true>Students</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/evaluations'">Evaluations</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/tutoringsessions'">Tutoring Sessions</button>
                </td>
                <td style="vertical-align:top;">
                    <button style="width:100px;height:60px; margin:0px 0px 0px 120%;" onclick="location.href='#/signin'">Sign Out</button>
                </td>
        </table>
    </div>
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

export default {
    name: "students",
    components:
    {
        cooltable
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
        },
        popup: function(message)
        {
            console.log(message);
        }
    },
    watch:
    {
        query: function (val)
        {
            this.selected = 0;
            if (this.query == "")
                this.query = undefined;
            for (var i = 0; i < this.students.length; i++)
            {
                if (this.students[i].username.startsWith(this.query))
                {
                    this.select(i+1);
                    this.search(this.query);
                    break;
                }
            }
        }
    }
}
</script>

<style src="./Style.css" />
