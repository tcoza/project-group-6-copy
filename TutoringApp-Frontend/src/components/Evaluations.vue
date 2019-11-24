<template>
  <div class="eval">
    <topbar title="Evaluations" />
    <h3>View student and tutor evaluations here. You can delete an evaluation comment!</h3>
    <div style="display: flex">
        <cooltable
            v-bind:headers="[
                { name: 'Username', width: '18%' },
                { name: 'Date', width: '15%'},
                { name: 'Rating', width: '10%'},
                { name: 'Comment', width: '27%'},
                { name: 'Status', width: '10%' }]"
            v-bind:columns="['username', 'date', 'rating', 'comment',
            {
                property: 'commentStatus',
                onChange: statusChangedS,
                options: [
                    { value: true, display: 'Visible' },
                    { value: false, display: 'Removed' }]
            }]"
            v-bind:list="studentevals"
            width="100%"
            searchid="username"
        />
      
      <cooltable
            v-bind:headers="[
                { name: 'Username', width: '18%' },
                { name: 'Date', width: '15%'},
                { name: 'Rating', width: '10%'},
                { name: 'Comment', width: '27%'},
                { name: 'Status', width: '10%' }]"
            v-bind:columns="['username', 'date', 'rating', 'comment',
            {
                property: 'commentStatus',
                onChange: statusChangedT,
                options: [
                    { value: true, display: 'Visible' },
                    { value: false, display: 'Removed' }]
            }]"
            v-bind:list="tutorevals"
            width="100%"
            searchid="username"
        />
    </div>
  </div>
</template>
<script>
import cooltable from "./CoolTable";
import topbar from "./TopBar";

export default {
  name: "evaluations",
  components:
  {
    cooltable,
    topbar
  },
  data: function() {
    return {
      studentevals: [],
      tutorevals: []
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
    }
  }
};
</script>

<style src="./Style.css" />