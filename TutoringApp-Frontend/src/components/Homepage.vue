<template>
   <form id="main" v-cloak>
        <div class="bar">
            <h2> TutoringApp Manager View - Home Page </h2> 
        </div>

        <h3> Welcome, Manager (name)! </h3>
        <li v-for="a in articles"  v-bind:key="a">
            <a v-bind:href="a.url" target="_self"> <img :src="a.image" /> </a>
            <p>{{a.title}}</p>
        </li>

    </form>
</template>

<script>
    import image1 from "../assets/Youtube.jpg"
    import image2 from "../assets/money.jpg"
    import image3 from "../assets/evaluation.jpg"
    import image4 from "../assets/session.jpg"
    import image5 from "../assets/courses.png"

    export default {
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
        }
    }
</script>

<style src="./Style.css" />
