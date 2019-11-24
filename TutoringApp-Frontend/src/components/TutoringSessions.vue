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
                  <option value="C1">Class 1</option>
                  <option value="C2">Class 2</option>
                  <option value="C3">Class 3</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>Start Time</td>
            </tr>
            <tr>
              <td>
                <vue-timepicker input-width="100%" v-model="selectedTime" format="hh:mm A"></vue-timepicker>
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
import Homepage from "./Homepage.vue";
import cooltable from "./CoolTable";
import topbar from "./TopBar";

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
        A: ""
      },
      selectedDate: new Date().toISOString().slice(0, 10), //display today's date
      selectedClass: undefined
    };
  },
  created: function() {
    const populateRequests = async () => {
      const response = await fetch("http://localhost:8080/grouprequests");
      const myJson = await response.json(); //extract JSON from the http response
      this.groupRequests = myJson._embedded.grouprequests; //get all group requests

      //each request has the following attributes/associations that need to be parsed
      for (var i = 0; i < this.groupRequests.length; i++) {
        var groupRequest = this.groupRequests[i];

        // 1)
        groupRequest.id = groupRequest._links.self.href.substr(
          groupRequest._links.self.href.lastIndexOf("/") + 1
        ); //parse id from endpoint path

        // 2)
        const requestorResponse = await fetch(
          groupRequest._links.requestor.href
        );
        const myRequestor = await requestorResponse.json();
        groupRequest.requestor = myRequestor._links.self.href.substr(
          myRequestor._links.self.href.lastIndexOf("/") + 1
        ); //get student username

        // For 3 and 4, check if either response is empty

        // 3)
        const courseResponse = await fetch(
          groupRequest._links.requestedCourse.href
        );
        const myCourse = await courseResponse.json();
        groupRequest.requestedCourse = myCourse._links.self.href.substr(
          myCourse._links.self.href.lastIndexOf("/") + 1
        ); //get courseCode

        //4) requestedSubject
        //console.log(groupRequest._links.requestedSubject.href);
        // continue;
        // const sbjResponse = await fetch(groupRequest._links.requestedSubject.href);
        // const mySbj = await sbjResponse.json();
        // groupRequest.requestedSubject = mySubj._links.self.href.substr(mySbj._links.self.href.lastIndexOf('/')+1) //get subject
      }

      this.groupRequests.sort((a, b) =>
        a.requestedCourse > b.requestedCourse ? 1 : -1
      );
    };

    const populateTutors = async () => {
      const response = await fetch("http://localhost:8080/tutors");
      const myJson = await response.json(); //extract JSON from the http response
      this.tutors = myJson._embedded.tutors; //get all tutors

      //each tutor needs to have username parsed
      for (var i = 0; i < this.tutors.length; i++) {
        var tutor = this.tutors[i];
        tutor.username = tutor._links.self.href.substr(
          tutor._links.self.href.lastIndexOf("/") + 1
        ); //parse tutor username from url
      }

      this.tutors.sort((a, b) => (a.username > b.username ? 1 : -1));
    };

    populateRequests();
    populateTutors();
  },
  methods: {
    book() {
      var url = "http://localhost:8080/createscheduledgroupsession";

      const userAction = async () => {
        const response = await fetch(url, {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body:
            "username=" +
            selectedTutor +
            "&roomid=" +
            selectedClass +
            "&starttime=" +
            selectedTime
        });
        if (!response.ok) alert("Error booking a session");
        else alert("Session booked!");
      };
      userAction();
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
