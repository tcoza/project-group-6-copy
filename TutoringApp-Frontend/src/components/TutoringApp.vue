<template>
    <div id="example-1">
        <button v-on:click="counter += 1">Add 1</button>
        <p>The button above has been clicked {{ counter }} times.</p>
    </div>
</template>

<script>
    var app = new Vue({
        el: '#example-1',
        data: {
            counter: 0
        }
    })
</script>