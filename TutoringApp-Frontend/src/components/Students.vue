<template>
  <div class="students">
    <div class="bar">
      <h2>Students</h2>
    </div>
    <div class="scroll">
      <table
      ref="table"
      tabindex="0"
      v-on:keydown.up='selected -= selected > 1'
      v-on:keydown.down='selected += selected < students.length'
      >
        <tr>
          <th>Username</th>
          <th>First</th>
          <th>Last</th>
          <th>Status</th>
        </tr>
        <tr
            v-for="index in students.length"
            v-bind:key="index"
            v-on:mousedown="select(index)"
            v-bind:class="[selected == index ? 'highlight' : '']">
          <td class="username">{{ students[index-1].username }}</td>
          <td>{{ students[index-1].firstname }}</td>
          <td>{{ students[index-1].lastname }}</td>
          <td class="status">{{ students[index-1].status }}</td>
        </tr>
      </table>
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
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
        this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'PENDING' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'PENDING' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'PENDING' });
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
div.scroll {
  margin: auto;
  padding: 4px;
  width: 1000px;
  height: 500px;
  overflow-x: auto;
  text-align: justify;
  border: 3px solid #73ad21;
}
th {
    font-size: 18pt;
}
td {
    width: 350px;
    font-size: 16pt;
}
td.username {
    width: 200px;
}
td.status {
    width: 100px;
}
.highlight {
    background-color: greenyellow;
}
</style>
