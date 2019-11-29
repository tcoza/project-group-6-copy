<template>
   <div id="main" v-cloak>
        <topbar title="Home" />
        <h3> Welcome, {{ name }}! </h3>
        <li v-for="a in articles"  v-bind:key="a.url">
            <a v-bind:href="a.url" target="_self"> <img :src="a.image" /> </a>
            <p>{{a.title}}</p>
        </li>

    </div>
</template>


<script>
    import image1 from "../assets/tutor.jpg"
    import image2 from "../assets/students.jpg"
    import image3 from "../assets/evaluation.jpg"
    import image4 from "../assets/session.jpg"
    import image5 from "../assets/courses.jpg"
    import topbar from "./TopBar"
    import VueCookies from "vue-cookies"

    export default {
        components: { topbar, VueCookies},
        data: function(){
            return{
                name: VueCookies.get('ManagerName'),
                articles: [{
                    image: image1,
                    url: "http://127.0.0.1:8087/#/tutors",
                    title: "Tutors"
                },{
                    image: image2,
                    url: "http://127.0.0.1:8087/#/students",
                    title: "Students"
                },
                {
                    image: image5,
                    url: "http://127.0.0.1:8087/#/css",
                    title: "Courses, Subjects, Schools"
                },
                {
                    image: image3,
                    url: "http://127.0.0.1:8087/#/evaluations",
                    title: "Evaluations"
                },{
                    image: image4,
                    url: "http://127.0.0.1:8087/#/tutoringsessions",
                    title:"Tutoring Sessions"
                }
                ]
            }
        }
        
  }
</script>

<style src="./Style.css" />
<style scoped>

/*-------------------------
    Grid layout
--------------------------*/
li{
    list-style: none;
    width: 50%;
    margin: 0 auto;
    text-align: left;
    padding: 10px;
    float:left;
}
li img {
    width: 100%;
    height: 250px;
    object-fit: cover;
    object-position: 50% 5%;
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
