<template>
  <div class="eval">
    <div class="bar">
      <table>
        <td style="width:30%">
          <h2>TutoringTurtles | Evaluations</h2>
        </td>
        <td style="vertical-align:top">
          <button style="width:100px;height:60px;" onclick="location.href='#/home'">Home</button>
        </td>
        <td style="vertical-align:top">
          <button
            style="width:100px;height:60px;"
            onclick="location.href='#/css'"
          >Courses Subjects Schools</button>
        </td>
        <td style="vertical-align:top">
          <button style="width:100px;height:60px;" onclick="location.href='#/tutors'">Tutors</button>
        </td>
        <td style="vertical-align:top">
          <button style="width:100px;height:60px;" onclick="location.href='#/students'">Students</button>
        </td>
        <td style="vertical-align:top">
          <button
            style="width:100px;height:60px;opacity:1;"
            disabled="true"
            onclick="location.href='#/evaluations'"
          >Evaluations</button>
        </td>
        <td style="vertical-align:top">
          <button
            style="width:100px;height:60px;"
            onclick="location.href='#/tutoringsessions'"
          >Tutoring Sessions</button>
        </td>
        <td style="vertical-align:top;">
          <button
            style="width:100px;height:60px; margin:0px 0px 0px 120%;"
            onclick="location.href='#/signin'"
          >Sign Out</button>
        </td>
      </table>
    </div>
    <h3>View student and tutor evaluations here. You can delete an evaluation comment!</h3>
    <div style="display:flex;">
    
        <table style="width: 45%;margin: 0px 0px 0px 30px;" ref="tableS"
        tabindex="0"
        class="tableS border-class"
        v-if="studentevals"
        v-on:keydown.up="$event.preventDefault(); selectS(selectedS-1)"
        v-on:keydown.down="$event.preventDefault(); selectS(selectedS+1)"
        v-on:keypress="search()">
          <tr>
            <th >Username</th>
            <th >First</th>
            <th >Last</th>
            <th >Date</th>
            <th>Rating</th>
            <th >Comment</th>
            <th >Status</th>
          </tr>
          <tr
            v-for="indexS in studentevals.length"
            v-bind:key="indexS"
            v-bind:tabindex="indexS"
            v-on:mousedown="selectS(indexS)"
            v-bind:class="[selectedS == indexS ? 'highlight' : '']"
          >
            <td>{{ studentevals[indexS-1].username }}</td>
            <td>{{ studentevals[indexS-1].firstName }}</td>
            <td>{{ studentevals[indexS-1].lastName }}</td>
            <td>{{ studentevals[indexS-1].date }}</td>
            <td>{{ studentevals[indexS-1].rating }}</td>
            <td>{{ studentevals[indexS-1].comment }}</td>
            <td>
              <select
                v-model="studentevals[indexS-1].commentStatus"
                v-on:change="statusChangedS(indexS-1)"
              >
                <option value="true">Visible</option>
                <option value="false">Removed</option>
              </select>
            </td>
          </tr>
        </table>
      
        <table style="width: 45%;margin: 0px 0px 0px 30px;" ref="tableT"
        tabindex="0"
        class="tableT border-class"
        v-if="tutorevals"
        v-on:keydown.up="$event.preventDefault(); selectT(selectedT-1)"
        v-on:keydown.down="$event.preventDefault(); selectT(selectedT+1)"
        v-on:keypress="search()">
          <tr>
            <th style="width: auto">Username</th>
            <th style="width: auto">First</th>
            <th style="width: auto">Last</th>
            <th style="width: auto">Date</th>
            <th style="width: auto">Rating</th>
            <th style="width: auto">Comment</th>
            <th style="width: auto">Status</th>
          </tr>
          <tr
            v-for="indexT in tutorevals.length"
            v-bind:key="indexT"
            v-bind:tabindex="indexT"
            v-on:mousedown="selectT(indexT)"
            v-bind:class="[selectedT == indexT ? 'highlight' : '']"
          >
            <td>{{ tutorevals[indexT-1].username }}</td>
            <td>{{ tutorevals[indexT-1].firstName }}</td>
            <td>{{ tutorevals[indexT-1].lastName }}</td>
            <td>{{ tutorevals[indexT-1].date }}</td>
            <td>{{ tutorevals[indexT-1].rating }}</td>
            <td>{{ tutorevals[indexT-1].comment }}</td>
            <td>
              <select
                v-model="tutorevals[indexT-1].commentStatus"
                v-on:change="statusChangedT(indexT-1)"
              >
                <option value="true">Visible</option>
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
        v-on:focusout="unsearch()"
      />
    </div>
  
</template>
<script>
export default {
  name: "evaluations",
  data: function() {
    return {
      studentevals: [],
      tutorevals: [],
      selectedS: 0,
      selectedT: 0,
      query: 0
    };
  },
  created: function() {
    const parseStudentEvals = async () => {
      const response = await fetch("http://localhost:8080/studentevaluations");

      if (response.ok) {
        var myJson = await response.json(); //extract JSON from the http response
        this.studentevals = myJson._embedded.studentevaluations;
      }

      //rating and date automatically parsed
      for (var i = 0; i < this.studentevals.length; i++) {
        var studenteval = this.studentevals[i];

        // 1) recipient username
        const recipientResponse = await fetch(
          studenteval._links.recipient.href
        );
        const myRecipient = await recipientResponse.json();
        studenteval.username = myRecipient._links.self.href.substr(
          myRecipient._links.self.href.lastIndexOf("/") + 1
        ); //get student username
        studenteval.firstName = myRecipient.firstName;
        studenteval.lastName = myRecipient.lastName;

        // 2) eval comment ONLY IF IT EXISTS
        // const commentResponse = await fetch(studenteval._links.evaluationComment.href);
        // if (commentResponse.ok) {
        //     const myComment = await commentResponse.json();
        //     studenteval.comment = myComment.comment;
        // }
      }
      this.studentevals.sort((a, b) => (a.username > b.username ? 1 : -1));
    };
    const parseTutorEvals = async () => {
      const response = await fetch("http://localhost:8080/tutorevaluations");

      if (response.ok) {
        var myJson = await response.json(); //extract JSON from the http response
        this.tutorevals = myJson._embedded.tutorevauations; //NOTE THIS IS MISPELLED IN MODEL, KEEP THIS SPELLING
      }

      //rating and date automatically parsed
      for (var i = 0; i < this.tutorevals.length; i++) {
        var tutoreval = this.tutorevals[i];

        // 1) recipient username
        const recipientResponse = await fetch(tutoreval._links.recipient.href);
        const myRecipient = await recipientResponse.json();
        tutoreval.username = myRecipient._links.self.href.substr(
          myRecipient._links.self.href.lastIndexOf("/") + 1
        ); //get student username
        tutoreval.firstName = myRecipient.firstName;
        tutoreval.lastName = myRecipient.lastName;

        // 2) eval comment ONLY IF IT EXISTS
        // const commentResponse = await fetch(studenteval._links.evaluationComment.href);
        // if (commentResponse.ok) {
        //     const myComment = await commentResponse.json();
        //     studenteval.comment = myComment.comment;
        // }
      }
      this.tutorevals.sort((a, b) => (a.username > b.username ? 1 : -1));
    };

    parseStudentEvals();
    parseTutorEvals();
  },
  methods: {
    // selectS: function(index)
    // {
    //     if (index < 1 || index > this.studentevals.length)
    //         return;
    //     this.selectedS = index;
    //     this.$refs.table.rows[index].focus();
    // },
    // selectT: function(index)
    // {
    //     if (index < 1 || index > this.tutorevals.length)
    //         return;
    //     this.selectedT = index;
    //     this.$refs.table.rows[index].focus();
    // },
    // search: function (query)
    // {
    //     this.$refs.searchbox.style.display = "initial";
    //     this.query = query;
    //     this.$refs.searchbox.focus();
    // },
    // unsearch: function()
    // {
    //     this.$refs.searchbox.style.display = "none";
    // },
    statusChangedS(index) {
      // //remove a comment
      // var url = 'http://localhost:8080/createstudentevaluation/' + this.studentevals[index].username;
      // url += this.studentevals[index].isActiveAccount == "true" ? '/reactivate' : '/deactivate';
      // const userAction = async () => {
      //     const response = await fetch(url, { method: "POST" });
      //     if (!response.ok)
      //         console.log(response);
      // }
      // userAction();
    },
    statusChangedT(index) {
      // var url = 'http://localhost:8080/createtutorevaluation/' + this.tutorevals[index].username;
      // url += this.tutorevals[index].isActiveAccount == "true" ? '/reactivate' : '/deactivate';
      // const userAction = async () => {
      //     const response = await fetch(url, { method: "POST" });
      //     if (!response.ok)
      //         console.log(response);
      // }
      // userAction();
    },
    popup: function(message) {
      console.log(message);
    }
  }
};
</script>

<style src="./Style.css" />
<style scoped>
.scroll {
  display: flex;
}

.title {
  display: flex;
}
h4 {
  font-size: 30px;
  margin-right: 530px;
  font-weight: bold;
  text-align: left;
  color: #4caf50;
  font-family: Times;
}
</style>