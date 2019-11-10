<template>
  <div class="count">
    <h1>Button clicked {{ count }} times.</h1>
    <button v-on:click="count += 1">Click me!</button>
    <button v-on:click="greet()">Greet!</button>
    <br />
    <br />
    <ul>
      <li v-for="message in messages" v-bind:key="message" v-on:click="msg = message">
        <a href='#'>{{ message }}</a>
      </li>
    </ul>
    <input ref='input' type='text' v-model='msg' v-on:keyup.enter='$refs.add.click(); $refs.input.select();'>
    <button ref='add' v-on:click="addToList()">Add</button>
  </div>
</template>

<script>
  export default {
    name: 'count',
    data() {
      return { count: 0, msg: 'F', messages: [] }
    },
    methods: {
        greet: function () {
            alert(this.msg);
        },
        addToList: function () {
            this.messages.push(this.msg);
        }
    }
  }
</script>

<style scoped>
  ul {
    list-style-type: none
  }
</style>