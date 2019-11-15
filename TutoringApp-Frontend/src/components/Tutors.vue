<template>
  <div class="tutors">
    <div class="bar">
      <h2>Tutors</h2>
    </div>
    <h3>Welcome, Manager!</h3>
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
                    <th style="width: 25%">Username</th>
                    <th style="width: 30%">First</th>
                    <th style="width: 30%">Last</th>
                    <th style="width: auto">Status</th>
                </tr>
                <tr
                    v-for="index in tutors.length"
                    v-bind:key="index"
                    v-bind:tabindex="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                    <td>{{ tutors[index-1].username }}</td>
                    <td>{{ tutors[index-1].firstName }}</td>
                    <td>{{ tutors[index-1].lastName }}</td>
                    <td>
                        <select
                        v-model='tutors[index-1].status'
                        v-on:change="statusChanged(index-1)">
                            <option value="PENDING">Pending</option>
                            <option value="VERIFIED">Verified</option>
                            <option value="TERMINATED">Terminated</option>
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
    name: "tutors",
    data: function() {
        return { tutors: [], selected: undefined, query: undefined };
    },
    created: function()
    {
        const userAction = async () => {
            const response = await fetch('http://localhost:8080/tutors');
            const myJson = await response.json();
            this.tutors = myJson._embedded.tutors
            this.tutors.forEach(function (tutor)
            {
                tutor.username = tutor._links.self.href.substr(tutor._links.self.href.lastIndexOf('/')+1);
            });
        }
        userAction();
    },
    methods:
    {
        select: function(index)
        {
            if (index < 1 || index > this.tutors.length)
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
        statusChanged(index)
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
            for (var i = 0; i < this.tutors.length; i++)
            {
                if (this.tutors[i].username.startsWith(this.query))
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

div.container {
    width: 1000px;
    text-align: right;
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
