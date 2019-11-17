<template>
    <div id="signin" v-cloak>
    <div class="bar">
      <h2>Tutoring Session</h2>
    </div>

    <br />
    <div id="login">
      <div id="fields">
        Tutor Username
        <br />
        <input type="text" placeholder />
        <br />
        <br />Classroom ID
        <br />
        <input type="text" placeholder />
        <br />
        <br />Start Time
        <br />
        <input type="text" placeholder />
        <br />
        <br />
      </div>
      <button onclick="">Book Session</button>
    </div>
  </div>
</template>

<script>
</script>

<style src="./Style.css" />
<style scoped>
#login {
  position: absolute;  
  text-align: auto;
  right: 150px;
  width: 400px;
  margin: 0 right;
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
</style>
