<template>
  <div id="tutoringapp">
    <button v-on:click="counter += 1">Add 1</button>
    <p>The button above has been clicked {{ counter }} times.</p>
  </div>
</template>

<script>
  new Vue({
    el: '#tutoringapp',
    data: {
      counter: 0
    }
  });
</script>

<style>
  #tutoringapp {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>