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
                    <td>{{ students[index-1].firstname }}</td>
                    <td>{{ students[index-1].lastname }}</td>
                    <td>
                        <select v-model='students[index-1].status'>
                            <option value="ACTIVE">Active</option>
                            <option value="REMOVED">Removed</option>
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
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'ACTIVE' });
        this.students.push({ username: 'alba', firstname: 'Alba', lastname: 'Talelli', status: 'ACTIVE' });
        this.students.push({ username: 'helen', firstname: 'Helen', lastname: 'Lin', status: 'ACTIVE' });
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
