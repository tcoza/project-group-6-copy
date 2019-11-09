<template>
  <div class="count">
    <h1>Button clicked {{ count }} times.</h1>
    <button v-on:click="count += 1">Click me!</button>
  </div>
</template>

<script>
export default {
  name: 'count',
  data () {
    return { count: 0 }
  }
}
</script>