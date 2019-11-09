<template>
    <div>
        <h1>Sign In</h1>
        <img :src="image" />
    </div>
</template>

<script>
    import image from "@/assets/logo.png"


    export default {
        data: function () {
            return {
                image: image
            }
        }
    }
</script>

<style>
</style>
