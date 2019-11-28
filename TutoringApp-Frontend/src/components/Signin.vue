<template>
  <div id="signin" v-cloak>
    <topbar title="Sign in" />
    
    <div id="login">
      <div id="fields">
        Username<br />
        <input type="text" placeholder="Enter (any) username" v-model="manager" /><br />
        <br />Password<br />
        <input type="password" placeholder="Enter (any) password" /><br />
        <br />
      </div>
      <button v-on:click="signin()">Sign In</button>
    </div>
  </div>
</template>

<script>
    import topbar from './TopBar'
    import VueCookies from 'vue-cookies'

    export default {
      components: { topbar, VueCookies },
      data: function()
      {
        return { manager: 'Marwan' };
      },
      created: function()
      {
        VueCookies.remove('ManagerName');
      },
      methods:
      {
        signin: function()
        {
          VueCookies.set('ManagerName', this.manager);
          location.href='#/home'
        }
      }
    }
</script>
<style src="./Style.css" />
<style scoped>
#login {
  text-align: right;
  width: 400px;
  margin: 0 auto;
  padding: 10px;
  border: 3px solid #21ad9a;
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
