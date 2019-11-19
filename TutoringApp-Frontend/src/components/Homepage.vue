<template>
   <div id="main" v-cloak>
        <div class="bar">
            <h2> TutoringApp Manager View - Home Page </h2> 
            <button class="logout" onclick="location.href='#/signin'">Log out</button>
        </div>

        <h3> Welcome, Manager {{username}}! </h3>
        <h3 :displayName = "username"/>
        <li v-for="a in articles"  v-bind:key="a">
            <a v-bind:href="a.url" target="_self"> <img :src="a.image" /> </a>
            <p>{{a.title}}</p>
        </li>

    </div>
</template>

<script>
    import image1 from "../assets/Youtube.jpg"
    import image2 from "../assets/money.jpg"
    import image3 from "../assets/evaluation.jpg"
    import image4 from "../assets/session.jpg"
    import image5 from "../assets/courses.png"

    export default {
        props: {
            username: {
                type : String,
                default : ""
            }
        },
        data: function(){
            return{
                articles: [{
                    "image": image1,
                    "url":"http://127.0.0.1:8087/#/tutors",
                    "title":"Tutors"
                },{
                    "image": image2,
                    "url":"http://127.0.0.1:8087/#/students",
                    "title":"Students"
                },{
                    "image": image3,
                    "url":"http://127.0.0.1:8087/#/evaluations",
                    "title":"Evaluations"
                },{
                    "image": image4,
                    "url":"http://127.0.0.1:8087/#/tutoringsessions",
                    "title":"Tutoring Sessions"
                },{
                    "image": image5,
                    "url":"http://127.0.0.1:8087/#/css",
                    "title":"Courses, Subjects, Schools"
                }
                ]
            }
        },
    
  }
</script>

<style src="./Style.css" />
<style scoped>
.bar button{
    margin:0px 0px 0px 60%;
}

li{
    list-style: none;
    width: 315px;
    margin: 0 auto;
    text-align: left;
    padding: 10px;
    float:left;
}
li img{
    width:250px;
    height:250px;
    object-fit: cover;
    display:block;
    border:none;
}
li p{
    margin-left: 5%;
    font-weight: normal;
    color:#000000;
    font-size: 20px;
    
}
</style>
