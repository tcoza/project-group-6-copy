<template>
    <div id="signin" v-cloak>
        <div class="bar">
            <h2>Sign In</h2> 
        </div>

        <br />
        <div id="login">
            <div id="fields">
                Username<br />
                <input type="text" value="Manager" /><br /><br />
                Password<br />
                <input type="Password" placeholder="" /><br /><br />
            </div>
            <button onclick="location.href='#/home'">Sign in</button>
        </div>
    </div>
</template>

<style src="./Style.css" />
<style scoped>

#login {
    text-align: right;
    width: 400px;
    margin: 0 auto;
    padding: 10px;
    border: 3px solid #73AD21;
}

#fields {
    text-align: left;
}

</style>