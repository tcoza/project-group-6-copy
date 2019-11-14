<template>
  <div class="students">
    <div class="bar">
      <h2>Students</h2>
    </div>
    <div class="container">
        <input list="browsers">
        <datalist id="browsers">
            <option v-for="student in students" v-bind:key="student.username" v-bind:value="student.username" />
        </datalist>
        <button v-on:click="remove()">Remove</button>
        <div class="scroll">
            <table style="width: 100%"
                ref="table"
                tabindex="0"
                v-on:keydown.up='selected -= selected > 1'
                v-on:keydown.down='selected += selected < students.length'>
                <tr>
                    <th style="width: 25%">Username</th>
                    <th style="width: 30%">First</th>
                    <th style="width: 30%">Last</th>
                    <th style="width: auto">Status</th>
                </tr>
                <tr
                    v-for="index in students.length"
                    v-bind:key="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                <td>{{ students[index-1].username }}</td>
                <td>{{ students[index-1].firstname }}</td>
                <td>{{ students[index-1].lastname }}</td>
                <td>
                    <select v-bind:value='students[index-1].status'>
                        <option value="ACTIVE">Active</option>
                        <option value="REMOVED">Removed</option>
                    </select>
                </td>
                </tr>
            </table>
        </div>
    </div>
  </div>
</template>


<script>
export default {
    name: "students",
    data: function() {
        return { students: [], selected: undefined };
    },
    created: function()
    {
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
    },
    mounted: function()
    {
        
    },
    methods:
    {
        select: function(index)
        {
            this.selected = index;
            this.$refs.table.focus();
        },
        popup: function(message)
        {
            console.log(message);
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
  height: 500px;
  padding: 5px;
  overflow-y: auto;
  text-align: justify;
  border: 3px solid #73ad21;
}

tr { margin-top: 10px; }
th { background-color: #4CAF50; font-size: 18pt; }
td { font-size: 16pt; }

.highlight {
    background-color: greenyellow;
}
</style>
