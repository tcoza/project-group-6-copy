<template>
  <div class="eval">
    <div class="bar">
      <table>
                <td style="width:50%">
                    <h2> TutoringTurtles | Evaluations </h2> 
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
                    <button style="width:100px;height:60px;" onclick="location.href='#/students'">Students</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;opacity:1;" disabled=true onclick="location.href='#/evaluations'">Evaluations</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/tutoringsessions'">Tutoring Sessions</button>
                </td>
                <td style="vertical-align:top;">
                    <button style="width:100px;height:60px; margin:0px 0px 0px 120%;" onclick="location.href='#/signin'">Sign Out</button>
                </td>
            </table>
    </div>
    <h3>Welcome, Manager!</h3>
    <div>
    <div class = "title">
        <h4> Students </h4>
        <h4> Tutors </h4>
    </div>
        <div 
            tabindex="0"
            ref="scroll"
            class="scroll"
            v-on:keydown.up='$event.preventDefault(); select(selected-1)'
            v-on:keydown.down='$event.preventDefault(); select(selected+1)'
            v-on:keypress='search()'>
            <table class= "TableS" style="width: 50%" ref="table">
                <tr>
                    <th style="width: 10%">First</th>
                    <th style="width: 10%">Last</th>
                    <th style="width: 10%">Rating</th>
                    <th style="width: auto">Comment</th>
                    <th style="width: 10%">Status</th>
                </tr>
                <tr
                    v-for="index in students.length"
                    v-bind:key="index"
                    v-bind:tabindex="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                    <td>{{ students[index-1].firstName }}</td>
                    <td>{{ students[index-1].lastName }}</td>
                    <td>{{ students[index-1].rating }}</td>
                    <td>{{ students[index-1].comment }}</td>
                    <td>
                        <select
                        v-model='students[index-1].isActiveAccount'
                        v-on:change="statusChangedS(index-1)">
                            <option value="true">Accept</option>
                            <option value="false">Removed</option>
                        </select>
                    </td>
                </tr>
            </table>
            <table class="tableT" style="width: 50%" ref="table">
                <tr>
                    <th style="width: 10%">First</th>
                    <th style="width: 10%">Last</th>
                    <th style="width: 10%">Rating</th>
                    <th style="width: auto">Comment</th>
                    <th style="width: 10%">Status</th>
                </tr>
                <tr
                    v-for="index in tutors.length"
                    v-bind:key="index"
                    v-bind:tabindex="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                    <td>{{ tutors[index-1].firstName }}</td>
                    <td>{{ tutors[index-1].lastName }}</td>
                    <td>{{ tutors[index-1].rating }}</td>
                    <td>{{ tutors[index-1].comment }}</td>
                    <td>
                        <select
                        v-model='tutors[index-1].isActiveAccount'
                        v-on:change="statusChangedS(index-1)">
                            <option value="true">Accept</option>
                            <option value="false">Removed</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <input
        ref="searchbox"
        style="display: none"
        v-model="query"
        v-on:keydown.esc="$refs.scroll.focus()"
        v-on:focusout="unsearch()" />
    </div>
  </div>
</template>
<script>
export default {
    name: "students",
    name: "tutors",
    data: function() {
        return { students: [], selected: undefined, query: undefined, tutors: [] };
    },
    created: function()
    {
        const userAction = async () => {
            const response = await fetch('http://localhost:8080/studentevaluations');
            var myJson = await response.json(); //extract JSON from the http response
            // do something with myJson
            this.students = myJson._embedded.studentevaluations
            this.students.forEach((student) => student.username = student._links.recipient.href.substr(student._links.recipient.href.lastIndexOf('/')+1));
            this.students.sort((a,b) => (a.username > b.username) ? 1 : -1);

            const response1 = await fetch('http://localhost:8080/tutorevaluations');
            myJson = await response1.json(); //extract JSON from the http response
            // do something with myJson
            this.tutors = myJson._embedded.tutorevaluations
            this.tutors.forEach((tutor) => tutor.username = tutor._links.recipient.href.substr(tutor._links.recipient.href.lastIndexOf('/')+1));
            this.tutors.sort((a,b) => (a.username > b.username) ? 1 : -1);
        }
        userAction();
    },
    methods:
    {
        select: function(index)
        {
            if (index < 1 || index > this.students.length)
                return;
            this.selected = index;
            this.$refs.table.rows[index].focus();
        },
        search: function (query)
        {
            this.$refs.searchbox.style.display = "initial";
            this.query = query;
            this.$refs.searchbox.focus();
        },
        unsearch: function()
        {
            this.$refs.searchbox.style.display = "none";
        },
        statusChangedS(index)
        {
            var url = 'http://localhost:8080/createstudentevaluation/' + this.students[index].username;
            url += this.students[index].isActiveAccount == "true" ? '/reactivate' : '/deactivate';

            const userAction = async () => {
                const response = await fetch(url, { method: "POST" });
                if (!response.ok)
                    console.log(response);
            }
            userAction();
        },
        statusChangedT(index)
        {
            var url = 'http://localhost:8080/createtutorevaluation/' + this.tutors[index].username;
            url += this.tutors[index].isActiveAccount == "true" ? '/reactivate' : '/deactivate';

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
    }
}
</script>

<style src="./Style.css" />
<style scoped>

.scroll{
    display:flex;
}

.title{
    display:flex;
}
h4{
    font-size: 30px;
    margin-right: 530px;
    font-weight: bold;
    text-align: left;
    color: #4CAF50;
    font-family:Times;
}


</style>