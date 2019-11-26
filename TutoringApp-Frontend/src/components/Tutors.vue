<template>
  <div class="tutors">
    <topbar title="Tutors" />
    <h3>View all tutors here. You can verify a tutor, or terminate them below!</h3>
    <cooltable
            v-bind:headers="[
                { name: 'Username', width: '25%' },
                { name: 'First', width: '30%' },
                { name: 'Last', width: '30%' },
                { name: 'Status', width: '15%' }]"
            v-bind:columns="['username', 'firstName', 'lastName',
            {
                property: 'status',
                onChange: statusChanged,
                options: [
                    { value: 'PENDING', display: 'Pending' },
                    { value: 'VERIFIED', display: 'Verified' },
                    { value: 'TERMINATED', display: 'Terminated' }]
            }]"
            v-bind:list="tutors"
            searchid="username"
        />
  </div>
</template>


<script>
import cooltable from "./CoolTable";
import topbar from "./TopBar";
import AXIOS from "./Axios";

export default {
    name: "tutors",
    components:
    {
        cooltable,
        topbar
    },
    data: function() {
        return { tutors: [] };
    },
    created: function()
    {
        AXIOS.get('/tutors').then(response =>
        {
            this.tutors = response.data._embedded.tutors
            this.tutors.forEach((tutor) => tutor.username = tutor._links.self.href.substr(tutor._links.self.href.lastIndexOf('/')+1));
            this.tutors.sort((a,b) => (a.username > b.username) ? 1 : -1);
        })
        .catch(e => console.log(e.response.data.message));
    },
    methods:
    {
        statusChanged(index)
        {
            AXIOS.post('/tutors/'.concat(this.tutors[index].username).concat('/setstatus'),
                    {}, { params: { status: this.tutors[index].status }})
                    .catch(e => console.log(e.response.data.message));
        }
    }
}
</script>

<style src="./Style.css" />