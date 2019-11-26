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
import AXIOS from "./Axios";

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
    AXIOS.get('/studentevaluations').then(response =>
    {
      this.studentevals = response.data._embedded.studentevaluations;
      this.studentevals.sort((a, b) => (a.username > b.username ? 1 : -1));
    })
    .then(async () =>
    {
      for (var i = 0; i < this.studentevals.length; i++) {
        var studenteval = this.studentevals[i];

        // 1) recipient username
        await AXIOS.get(studenteval._links.recipient.href).then(response =>
        {
          studenteval.username = response.data._links.self.href.substr(response.data._links.self.href.lastIndexOf("/")+1); //get student username
          studenteval.firstName = response.data.firstName;
          studenteval.lastName = response.data.lastName;
        })
        .catch(e => console.log(e.response.data.message));;
        
        // 2) eval comment ONLY IF IT EXISTS
        // const commentResponse = await fetch(studenteval._links.evaluationComment.href);
        // if (commentResponse.ok) {
        //     const myComment = await commentResponse.json();
        //     studenteval.comment = myComment.comment;
        // }
      }

      this.$forceUpdate();
    })
    .catch(e => console.log(e.response.data.message));
    
    AXIOS.get('/tutorevaluations').then(response =>
    {
      this.tutorevals = response.data._embedded.tutorevauations;
      this.tutorevals.sort((a, b) => (a.username > b.username ? 1 : -1));
    })
    .then(async () =>
    {
      for (var i = 0; i < this.tutorevals.length; i++) {
        var tutoreval = this.tutorevals[i];

        // 1) recipient username
        await AXIOS.get(tutoreval._links.recipient.href).then(response =>
        {
          tutoreval.username = response.data._links.self.href.substr(response.data._links.self.href.lastIndexOf("/")+1); //get student username
          tutoreval.firstName = response.data.firstName;
          tutoreval.lastName = response.data.lastName;
        })
        .catch(e => console.log(e.response.data.message));;
        
        // 2) eval comment ONLY IF IT EXISTS
        // const commentResponse = await fetch(studenteval._links.evaluationComment.href);
        // if (commentResponse.ok) {
        //     const myComment = await commentResponse.json();
        //     studenteval.comment = myComment.comment;
        // }
      }

      this.$forceUpdate();
    })
    .catch(e => console.log(e.response.data.message));

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