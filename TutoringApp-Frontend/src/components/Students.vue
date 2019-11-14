<template>
  <div class="students">
    <div class="bar">
      <h2>Students</h2>
    </div>
    <h3>Welcome, Manager!</h3>
    <div class="scroll">
        <table style="width: 100%"
            ref="table"
            v-on:keydown.up='$event.preventDefault(); select(selected-1)'
            v-on:keydown.down='$event.preventDefault(); select(selected+1)'>
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
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
         this.students.push({ username: 'traian', firstname: 'Traian', lastname: 'Coza', status: 'ACTIVE' });
        this.students.push({ username: 'arianit', firstname: 'Arianit', lastname: 'Vavla', status: 'ACTIVE' });
        this.students.push({ username: 'odero', firstname: 'Odero', lastname: 'Otieno', status: 'REMOVED' });
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
            if (index < 1 || index > this.students.length)
                return;
            this.selected = index;
            this.$refs.table.rows[index].focus();
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
  margin-top: 20px;
  margin: auto;
  height: 500px;
  width: 1000px;
  padding: 5px;
  overflow-y: auto;
  text-align: justify;
  border: 3px solid #73ad21;
}

th { background-color: #4CAF50; font-size: 18pt; }
td { font-size: 16pt; }

.highlight {
    background-color: greenyellow;
}
</style>
