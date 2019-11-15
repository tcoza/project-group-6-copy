<template>
  <div class="students">
    <div class="bar">
      <h2>Students</h2>
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
                    v-for="index in students.length"
                    v-bind:key="index"
                    v-bind:tabindex="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                    <td>{{ students[index-1].username }}</td>
                    <td>{{ students[index-1].firstName }}</td>
                    <td>{{ students[index-1].lastName }}</td>
                    <td>
                        <select
                        v-model='students[index-1].isActiveAccount'
                        v-on:change="statusChanged(index-1)">
                            <option value="true">Active</option>
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
        v-on:focusout="unsearch()" />
    </div>
  </div>
</template>


<script>
export default {
    name: "students",
    data: function() {
        return { students: [], selected: undefined, query: undefined };
    },
    created: function()
    {
        const userAction = async () => {
            const response = await fetch('http://localhost:8080/students');
            const myJson = await response.json(); //extract JSON from the http response
            // do something with myJson
            this.students = myJson._embedded.students
            this.students.forEach((student) => student.username = student._links.self.href.substr(student._links.self.href.lastIndexOf('/')+1));
            this.students.sort((a,b) => (a.username > b.username) ? 1 : -1);
        }
        userAction();
    },
    methods:
    {
        select: function(index)
        {
            if (index < 1 || index > this.students.length)
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
            var url = 'http://localhost:8080/students/' + this.students[index].username;
            url += this.students[index].isActiveAccount == "true" ? '/reactivate' : '/deactivate';

            const userAction = async () => {
                const response = await fetch(url, { method: "POST" });
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
            for (var i = 0; i < this.students.length; i++)
            {
                if (this.students[i].username.startsWith(this.query))
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
