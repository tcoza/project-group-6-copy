<template>
    <div id="signin" v-cloak>
    <div class="bar">
      <h2>Tutoring Sessions</h2>
    </div>

    <div class="container">
        <div
            tabindex="0"
            ref="scroll"
            class="scroll"
            v-on:keydown.up='$event.preventDefault(); select(selected-1)'
            v-on:keydown.down='$event.preventDefault(); select(selected+1)'
            v-on:keypress='search()'>
            <table style="width: 100%" ref="table">
                <tr>
                    <th style="width: 25%">Date Created</th>
                    <th style="width: 30%">Requestor</th>
                    <th style="width: 30%">RequestedCourse</th>
                    <th style="width: auto">Requested Subject</th>
                    <th style="width: auto">Status</th>
                    <th style="width: auto">Request ID</th>
                </tr>
                <tr
                    v-for="index in groupRequests.length"
                    v-bind:key="index"
                    v-bind:tabindex="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                    <td>{{ groupRequests[index-1].dateCreated }}</td>
                    <td>{{ groupRequests[index-1].requestor }}</td>
                    <td>{{ groupRequests[index-1].requestedCourse }}</td>
                    <td>{{ groupRequests[index-1].requestedSubject }}</td>
                    <td>{{ groupRequests[index-1].isScheduled ? "Scheduled" : "Pending" }}</td>
                    <td>{{ groupRequests[index-1].id }}</td>
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


    <br />
    <div id="login">
      <div id="fields">
        Tutor Username
        <br />
        <select v-model="schoolNameS">
            <option v-for="tutor in tutors" v-bind:key="tutor.username" v-bind:value="tutor.username"> {{tutor.username}}</option>
        </select>        
        <br />
        <br />Classroom
        <br />
        <select v-model="classroom">
            <option value="C1"> Class 1 </option>
            <option value="C2" > Class 2 </option>
            <option value="C3" > Class 3 </option>
        </select>         
        <br />
        <br />Start Time
        <br />
        <vue-timepicker v-model="time" format="hh:mm A"></vue-timepicker>
        <br />
        <br />Booking Date
        <br />
        <date-pick class="calendar" v-model="date" :format="'YYYY-MM-DD'"></date-pick>
        <br />
        <br />
      </div>
      <button v-on:click="book()" >Book Session</button>
    </div>
  </div>
</template>

<script>
import DatePick from 'vue-date-pick';
import VueTimepicker from 'vue2-timepicker';


export default {
    components: {DatePick,VueTimepicker},
    name: "groupRequests",
    data: function() {
        return {
            groupRequests: [],
            selected: undefined,
            query: undefined,
            username: undefined,
            classId: undefined,
            startTime: undefined,
            date: '2019-01-01',
            tutors:[],
            time: {
                hh: '',
                mm: '',
                A: ''
            },
        };
        
    },
    created: function()
    {
        const userAction = async () => {
            const response = await fetch('http://localhost:8080/grouprequests');
            const myJson = await response.json(); //extract JSON from the http response
            this.groupRequests = myJson._embedded.grouprequests //get all group requests

            //each request has the following attributes/associations:
            
            //2) dateCreated 
            //3) isScheduled
            //4) requestedCourse - "href": "http://localhost:8080/grouprequests/1756888288/requestedCourse"
              // "requestedSubject": "href": "http://localhost:8080/grouprequests/1756888288/requestedSubject"
              //requestor "href": "http://localhost:8080/grouprequests/1756888288/requestor"
              //"scheduledGroupSession": "href": "http://localhost:8080/grouprequests/1756888288/scheduledGroupSession"

            //for each groupRequest in all groupRequests, parse the id, requestor username, requested course/subject
            //other attributes can be accessed directly (not associations or requiring parsing of endpoint)
             
            for (var i = 0; i < this.groupRequests.length; i++)
            {
                var groupRequest = this.groupRequests[i];

                // 1)
                groupRequest.id = groupRequest._links.self.href.substr(groupRequest._links.self.href.lastIndexOf('/')+1); //parse id from endpoint path

                // 2)
                const requestorResponse = await fetch(groupRequest._links.requestor.href);
                const myRequestor = await requestorResponse.json();
                groupRequest.requestor = myRequestor._links.self.href.substr(myRequestor._links.self.href.lastIndexOf('/')+1) //get student username

                // For 3 and 4, check if either response is empty

                // 3)
                const courseResponse = await fetch(groupRequest._links.requestedCourse.href);
                const myCourse = await courseResponse.json();
                groupRequest.requestedCourse = myCourse._links.self.href.substr(myCourse._links.self.href.lastIndexOf('/')+1) //get courseCode

                //4) requestedSubject 
                console.log(groupRequest._links.requestedSubject.href);
                continue;
                const sbjResponse = await fetch(groupRequest._links.requestedSubject.href);
                const mySbj = await sbjResponse.json();
                groupRequest.requestedSubject = mySubj._links.self.href.substr(mySbj._links.self.href.lastIndexOf('/')+1) //get subject
            }
           
           this.groupRequests.sort((a,b) => (a.requestedCourse > b.requestedCourse) ? 1 : -1);
        };

        userAction();
    },
    methods:
    {
        select: function(index)
        {
            if (index < 1 || index > this.grouprequests.length)
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
        book()
        {
            var url = 'http://localhost:8080/tutors/' + this.tutors[index].username + "/setstatus";

            const userAction = async () => {
                const response = await fetch(url,
                {
                    method: "POST",
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: "status=" + this.tutors[index].status
                });
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
            for (var i = 0; i < this.grouprequests.length; i++)
            {
                if (this.grouprequests[i].requestedCourse.startsWith(this.query))
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
<style scoped>
@import '~vue2-timepicker/dist/VueTimepicker.css';

#login {
  position: absolute;  
  text-align: auto;
  right: 10px;
  width: 25%;
  margin: 0 right;
  padding: 10px;
  border: 3px solid #73ad21;
}
input {
  width: 100%;
  padding: 5px;
  margin: 5px 0;
}
#fields {
  text-align: left;
}

div.container {
    position: absolute;  
    text-align: auto;
    width: 70%;
    padding: 10px;
}

div.scroll {
  margin-top: 20px;
  margin: auto;
  height: 500px;
  width: 100%;
  padding: 5px;
  text-align: left;
  overflow-y: auto;
  border: 3px solid #73ad21;
}

div.scroll:focus, tr:focus { outline: none; }
</style>
