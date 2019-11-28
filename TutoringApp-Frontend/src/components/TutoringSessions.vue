<template>
  <div id="sessions" v-cloak>
    
      <topbar title="Tutoring Sessions" />
      <h3>View all group tutoring requests from students here. You can book/ schedule a tutoring session!</h3>
    <div style="display: flex">
      <cooltable
        v-bind:headers="[
                    { name: 'Date Created', width: '25%' },
                    { name: 'Requestor', width: '30%' },
                    { name: 'Requested Course', width: '30%' },
                    { name: 'Status', width: '15%' }]"
        v-bind:columns="['dateCreated', 'requestor', 'requestedCourse',
                    function() { return this.isScheduled ? 'Scheduled' : 'Pending' }]"
        v-bind:list="groupRequests"
        searchid="requestor"
        width="70%"
      />

      <div id="login" style="width: 30%">
        <div id="fields">
          <table style="width:100%;">
            <tr>
              <td>Tutor Username</td>
            </tr>
            <tr>
              <td>
                <select style="width:100%;" v-model="selectedTutor">
                  <option value disabled selected>Select a tutor</option>
                  <option
                    v-for="tutor in tutors"
                    v-bind:key="tutor.username"
                    v-bind:value="tutor.username"
                  >{{tutor.username}}</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>Classroom</td>
            </tr>
            <tr>
              <td>
                <select style="width:100%;" v-model="selectedClass">
                  <option value="1">Class 1</option>
                  <option value="2">Class 2</option>
                  <option value="3">Class 3</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>Start Time</td>
            </tr>
            <tr>
              <td>
                <vue-timepicker input-width="100%" v-model="selectedTime" format="hh:mm:ss"></vue-timepicker>
              </td>
            </tr>
            <tr>
              <td>Booking Date</td>
            </tr>
            <tr>
              <td>
                <date-pick
                  style="width:100%;"
                  class="calendar"
                  v-model="selectedDate"
                  :format="'YYYY-MM-DD'"
                ></date-pick>
              </td>
            </tr>
          </table>
        </div>
        <button v-on:click="book()">Book Session</button>
      </div>
    </div>
  </div>
</template>

<script>
import DatePick from "vue-date-pick";
import VueTimepicker from "vue2-timepicker";
import cooltable from "./CoolTable";
import topbar from "./TopBar";
import AXIOS from "./Axios"

export default {
  components: { DatePick, VueTimepicker, cooltable, topbar },
  name: "groupRequests",
  data: function() {
    return {
      groupRequests: [],
      tutors: [],
      selected: undefined,
      query: undefined,
      selectedTutor: undefined,
      selectedTime: {
        hh: "",
        mm: "",
        ss: ""
      },
      selectedDate: new Date().toISOString().slice(0, 10), //display today's date
      selectedClass: undefined
    };
  },
  created: function() {

    AXIOS.get('/grouprequests').then(response =>
    {
      this.groupRequests = response.data._embedded.grouprequests; //get all group requests
      this.groupRequests.sort((a, b) => a.requestedCourse > b.requestedCourse ? 1 : -1);
    }).then(async () =>
    {
      for (var i = 0; i < this.groupRequests.length; i++) {
        var groupRequest = this.groupRequests[i];

        // 1)
        groupRequest.id = groupRequest._links.self.href.substr(groupRequest._links.self.href.lastIndexOf("/") + 1); //parse id from endpoint path

        // 2)
        await AXIOS.get(groupRequest._links.requestor.href).then(response =>
          groupRequest.requestor = response.data._links.self.href.substr(
            response.data._links.self.href.lastIndexOf("/") + 1))
            .catch(e => console.log(e.response.data.message));; //get student username

        // For 3 and 4, check if either response is empty

        // 3)
        await AXIOS.get(groupRequest._links.requestedCourse.href).then(response => 
          groupRequest.requestedCourse = response.data._links.self.href.substr(
            response.data._links.self.href.lastIndexOf("/") + 1))
            .catch(e => console.log(e.response.data.message));; //get courseCode

        //4) requestedSubject
        //console.log(groupRequest._links.requestedSubject.href);
        // continue;
        // const sbjResponse = await fetch(groupRequest._links.requestedSubject.href);
        // const mySbj = await sbjResponse.json();
        // groupRequest.requestedSubject = mySubj._links.self.href.substr(mySbj._links.self.href.lastIndexOf('/')+1) //get subject
      }

      this.$forceUpdate();
    })
    .catch(e => console.log(e.response.data.message));

    AXIOS.get('/tutors').then(response =>
    {
      this.tutors = response.data._embedded.tutors; //get all tutors
      this.tutors.forEach(tutor => tutor.username = tutor._links.self.href.substr(tutor._links.self.href.lastIndexOf("/") + 1));
      this.tutors.sort((a, b) => (a.username > b.username ? 1 : -1));
    })
    .catch(e => console.log(e.response.data.message));
  },
  methods: {
    book() {
      var time = parseInt(this.selectedTime.mm, 10);
      time += parseInt(this.selectedTime.hh, 10) * 60;
      if (this.selectedTime.A == "PM")
         time += 12 * 60;

      AXIOS.post('/createscheduledgroupsession', {}, { params:
      {
        username: this.selectedTutor,
        roomid: this.selectedClass,
        starttime: time
      }})
      .then(response => alert("Session booked!"))
      .catch(e => alert("Error booking a session"));
    }
  }
};
</script>

<style src="./Style.css" />
<style scoped>
@import "~vue2-timepicker/dist/VueTimepicker.css";

.calendar .vdpInnerWrap {
  border: 1px solid #5c9bb7 !important;
}

li {
  list-style: none;
  width: 100px;
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
</style>
