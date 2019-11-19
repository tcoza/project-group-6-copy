<template>
   <div id="main" v-cloak>
        <div class="bar">
            <table>
                <td style="width:50%">
                    <h2> TutoringTurtles | Home </h2> 
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;opacity:1;" disabled=true onclick="location.href='#/home'">Home</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/css'">Courses Subjects Schools</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/tutors'">Tutors</button>
                </td >
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/students'">Students</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/evaluations'">Evaluations</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/tutoringsessions'">Tutoring Sessions</button>
                </td>
                <td style="vertical-align:top;">
                    <button style="width:100px;height:60px; margin:0px 0px 0px 120%;" onclick="location.href='#/signin'">Sign Out</button>
                </td>
            </table>
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
                },
                {
                    "image": image5,
                    "url":"http://127.0.0.1:8087/#/css",
                    "title":"Courses, Subjects, Schools"
                },
                {
                    "image": image3,
                    "url":"http://127.0.0.1:8087/#/evaluations",
                    "title":"Evaluations"
                },{
                    "image": image4,
                    "url":"http://127.0.0.1:8087/#/tutoringsessions",
                    "title":"Tutoring Sessions"
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
</style>
