<template>
  <div class="count">
    <h1>Button clicked {{ count }} times.</h1>
    <button v-on:click="count += 1">Click me!</button>
    <button v-on:click="greet()">Greet!</button>
    <br />
    <br />
    <input type='text' v-model='msg'>
  </div>
</template>

<script>
export default {
  name: 'count',
  data() {
    return { count: 0, msg: 'F' }
  },
  methods: {
      greet: function () {
          alert(this.msg);
      }
  }
}
</script>