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
                    <td>{{ tutors[index-1].firstname }}</td>
                    <td>{{ tutors[index-1].lastname }}</td>
                    <td>
                        <select v-model='tutors[index-1].status'>
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
        this.tutors.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.tutors.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.tutors.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'VERIFIED' });
        this.tutors.push({ username: 'alba', firstname: 'Alba', lastname: 'Talelli', status: 'TERMINATED' });
        this.tutors.push({ username: 'helen', firstname: 'Helen', lastname: 'Lin', status: 'TERMINATED' });
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
