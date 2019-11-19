<template>
  <div id="signin" v-cloak>
    <div class="bar">
      <h2>TutoringTurtles | Sign In</h2>
    </div>
    <br />
    <div id="login">
      <div id="fields">
        Username
        <br />
        <input type="text" placeholder="Enter (any) username" value="" v-model="manager" />
        <br />
        <br />Password
        <br />
        <input type="Password" placeholder="Enter (any) password" />
        <br />
        <br />
      </div>
      <button onclick="location.href='#/home'">Sign In</button>
    </div>
  </div>
</template>
<script>
    import Homepage from './Homepage.vue'
    export default {
      data: function() {
        return {
          manager: undefined
        };
      },
      components: { Homepage },
    }
</script>
<style src="./Style.css" />
<style scoped>
#login {
  text-align: right;
  width: 400px;
  margin: 0 auto;
  padding: 10px;
  border: 3px solid #73ad21;
}

input {
  width: 374px;
  padding: 5px;
  margin: 5px 0;
}

#fields {
  text-align: left;
}
</style>