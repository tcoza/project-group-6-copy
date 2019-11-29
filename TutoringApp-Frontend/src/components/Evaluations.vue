<template>
  <div class="eval">
    <topbar title="Evaluations" />
    <h3>View student and tutor evaluations here. You can delete an evaluation comment!</h3>
    <div style="display: flex">
        <cooltable
            title="Evaluations for Students"
            v-bind:headers="[
                { name: 'Username', width: '18%' },
                { name: 'Date', width: '15%'},
                { name: 'Rating', width: '10%'},
                { name: 'Comment', width: '27%'},
                { name: 'Status', width: '10%' }]"
            v-bind:columns="['username', 'date', 'rating', 'evaluationComment',
            {
                property: 'commentVisible',
                onChange: statusChangedS,
                options: [
                    { value: true, display: 'Visible' },
                    { value: false, display: 'Removed' }]
            }]"
            v-bind:list="studentevals"
            width="100%"
            searchid="username"
            v-bind:badwords="['freak', 'heck', 'poop', '***']"
        />
      
      <cooltable
            title="Evaluations for Tutors"
            v-bind:headers="[
                { name: 'Username', width: '18%' },
                { name: 'Date', width: '15%'},
                { name: 'Rating', width: '10%'},
                { name: 'Comment', width: '27%'},
                { name: 'Status', width: '10%' }]"
            v-bind:columns="['username', 'date', 'rating', 'evaluationComment',
            {
                property: 'commentVisible',
                onChange: statusChangedT,
                options: [
                    { value: true, display: 'Visible' },
                    { value: false, display: 'Removed' }]
            }]"
            v-bind:list="tutorevals"
            width="100%"
            searchid="username"
            v-bind:badwords="['freak', 'heck', 'poop', '***']"
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
        
        // 2) also get author username (we do not display it but need to for eval comments)
        await AXIOS.get(studenteval._links.author.href).then(response =>
        {
          studenteval.authorUN = response.data._links.self.href.substr(response.data._links.self.href.lastIndexOf("/")+1); //get author username
        })
        .catch(e => console.log(e.response.data.message));;
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

       // 2) also get author username (we do not display it but need to for eval comments)
        await AXIOS.get(tutoreval._links.author.href).then(response =>
        {
          tutoreval.authorUN = response.data._links.self.href.substr(response.data._links.self.href.lastIndexOf("/")+1); //get author username
        })
        .catch(e => console.log(e.response.data.message));;
      }

      this.$forceUpdate();
    })
    .catch(e => console.log(e.response.data.message));

  },
  methods: {
    statusChangedS(index) {
      //remove a comment or reset it to visible!
      let params = {
        studentUN: this.studentevals[index].username,
        tutorUN: this.studentevals[index].authorUN,
        isStudentEvaluation: true,
        visibility: this.studentevals[index].commentVisible
      }
      AXIOS.post('/setcommentvisibility/',{}, {params: params})
      .then(r => this.$alert("Successfully changed comment visibility!", '', 'success'))
      .catch(e => {
        this.$alert("Error changing comment visibility", '', 'error');
        console.log(e.response.data.message);
      });
    
    
    },
    statusChangedT(index) {
      //remove a comment or reset it to visible!
      let params = {
        studentUN: this.tutorevals[index].authorUN,
        tutorUN: this.tutorevals[index].username,
        isStudentEvaluation: false,
        visibility: this.tutorevals[index].commentVisible
      }
      
      AXIOS.post('/setcommentvisibility/',{}, {params: params})
      .then(r => this.$alert("Successfully changed comment visibility!", '', 'success'))
      .catch(e => {
        this.$alert("Error changing comment visibility", '', 'error');
        console.log(e.response.data.message);
      });
    }
  }
};
</script>

<style src="./Style.css" />