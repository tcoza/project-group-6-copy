<template>
    <div id="signin" v-cloak>

    <div class="bar">
        <table>
            <td style="width:30%">
                <h2>TutoringTurtles | Tutoring Sessions</h2>
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
                <button style="width:100px;height:60px;" onclick="location.href='#/evaluations'">Evaluations</button>
            </td>
            <td style="vertical-align:top">
                <button style="width:100px;height:60px;opacity:1;" onclick="location.href='#/tutoringsessions'" disabled=true>Tutoring Sessions</button>
            </td>
             <td style="vertical-align:top;">
                <button style="width:100px;height:60px; margin:0px 0px 0px 120%;" onclick="location.href='#/signin'">Sign Out</button>
            </td>
        </table>
    </div>
    <h3>View all group tutoring requests from students here. You can book/ schedule a tutoring session!</h3>

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
                   <!-- <th style="width: auto">Requested Subject</th>  -->
                    <th style="width: auto">Status</th>
                   <!-- <th style="width: auto">Request ID</th> -->
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
                   <!-- <td>{{ groupRequests[index-1].requestedSubject }}</td> -->
                    <td>{{ groupRequests[index-1].isScheduled ? "Scheduled" : "Pending" }}</td>
                   <!-- <td>{{ groupRequests[index-1].id }}</td>  -->
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
          <table style="width:100%;">
              <tr><td> Tutor Username</td> </tr>
              <tr>
                  <td>
                      <select style="width:100%;" v-model="selectedTutor">
                          <option value="" disabled selected>Select a tutor</option>
                          <option v-for="tutor in tutors" v-bind:key="tutor.username" v-bind:value="tutor.username"> {{tutor.username}}</option>
                      </select> 
                </td>
              </tr>
            <tr>
                <td> Classroom </td>
            </tr>
            <tr><td>
                <select  style="width:100%;" v-model="selectedClass">
                    <option value="C1"> Class 1 </option>
                    <option value="C2" > Class 2 </option>
                    <option value="C3" > Class 3 </option>
                </select> 
            </td></tr>
            <tr> <td> Start Time </td></tr>
            <tr><td>
                 <vue-timepicker input-width="100%" v-model="selectedTime" format="hh:mm A"></vue-timepicker>
            </td></tr>
             <tr> <td> Booking Date </td></tr>
            <tr><td >
                <date-pick style="width:100%;" class=calendar v-model="selectedDate" :format="'YYYY-MM-DD'"></date-pick>
            </td></tr>
        </table>
      </div>
      <button v-on:click="book()" >Book Session</button>
    </div>
  </div>
</template>

<script>
import DatePick from 'vue-date-pick';
import VueTimepicker from 'vue2-timepicker';
import Homepage from './Homepage.vue';

export default {
    components: {DatePick,VueTimepicker},
    name: "groupRequests",
    data: function() {
        return {
            groupRequests: [],
            tutors:[],
            selected: undefined,
            query: undefined,
            selectedTutor: undefined,
            selectedTime: {
                hh: '',
                mm: '',
                A: ''
            },
            selectedDate: new Date().toISOString().slice(0,10), //display today's date
            selectedClass: undefined
            
        };
        
    },
    created: function()
    {
        const populateRequests = async () => {
            const response = await fetch('http://localhost:8080/grouprequests');
            const myJson = await response.json(); //extract JSON from the http response
            this.groupRequests = myJson._embedded.grouprequests //get all group requests

            //each request has the following attributes/associations that need to be parsed
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
                //console.log(groupRequest._links.requestedSubject.href);
               // continue;
               // const sbjResponse = await fetch(groupRequest._links.requestedSubject.href);
               // const mySbj = await sbjResponse.json();
               // groupRequest.requestedSubject = mySubj._links.self.href.substr(mySbj._links.self.href.lastIndexOf('/')+1) //get subject
            }
           
           this.groupRequests.sort((a,b) => (a.requestedCourse > b.requestedCourse) ? 1 : -1);
        };

         const populateTutors = async () => {
            const response = await fetch('http://localhost:8080/tutors');
            const myJson = await response.json(); //extract JSON from the http response
            this.tutors = myJson._embedded.tutors //get all tutors

            //each tutor needs to have username parsed
            for (var i = 0; i < this.tutors.length; i++)
            {
                var tutor = this.tutors[i];
               tutor.username = tutor._links.self.href.substr(tutor._links.self.href.lastIndexOf('/')+1); //parse tutor username from url
            }
           
           this.tutors.sort((a,b) => (a.username > b.username) ? 1 : -1);
        };

        populateRequests();
        populateTutors();
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
            var url = 'http://localhost:8080/createscheduledgroupsession';

            const userAction = async () => {
                const response = await fetch(url,
                {
                    method: "POST",
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: "username=" + selectedTutor
                    + "&roomid=" + selectedClass
                    + "&starttime=" + selectedTime
                });
                if (!response.ok)
                    alert("Error booking a session");
                else
                 alert("Session booked!");
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


.calendar .vdpInnerWrap {
    border: 1px solid #5c9bb7 !important;
}

li{
    list-style: none;
    width:100px;
}
#login {
  position: absolute;  
  text-align: auto;
  right: 10px;
  width: 35%;
  margin: 0 right;
  padding: 10px;
  border: 3px solid #5c9bb7;
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
    width: 60%;
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
  border: 3px solid #5c9bb7;
}
.bar button{
    margin:0px 0px 0px 80%;
}
div.scroll:focus, tr:focus { outline: none; }
</style>
