<template>
    <div id="main" v-cloak>
        <div class="bar">
            <table>
                <td style="width:50%">
                    <h2> TutoringTurtles | Courses, Subjects, and Teaching Institutions </h2> 
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;" onclick="location.href='#/home'">Home</button>
                </td>
                <td style="vertical-align:top">
                    <button style="width:100px;height:60px;opacity:1;" disabled=true onclick="location.href='#/css'">Courses Subjects Schools</button>
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
            <h3> Welcome, Manager (name)! </h3>
        
        <div class="buttonPart">
            <button class="classes" v-on:click="classes()"> Create Classes</button>
            <button class="subejcts" v-on:click="subjects()"> Create Subjects</button>
            <button class="TI" v-on:click="TI()"> Create Teaching Institutions</button>
        </div>

        <div class="list">

            <table style="width: 33%" ref="table-TI">
                <tr>
                    <th scope="col" style="width: 25%">Type of school</th>
                    <th scope="col" style="width: 25%">School name</th>
                </tr>
                <tr v-for="index in TIlist.length" v-bind:key="index">
                    <td>{{ TIlist[index-1].schoolType}}</td>
                    <td>{{ TIlist[index-1].schoolName}}</td>
                </tr>
            </table>

             <table class="table-class" style="width: 33%" ref="table-class">
                    <th style="width: 30%">School name </th>
                    <th style="width: 30%">Classes Code</th>
                    <th style="width: 30%">Classes Name</th>

                <tr v-for="index in classlist.length" v-bind:key="index">
                    <td>{{ classlist[index-1].schoolName}}</td>
                    <td>{{ classlist[index-1].classCode}}</td>
                    <td>{{ classlist[index-1].className}}</td>
                </tr>
            </table>
             <table class="table-subject" style="width: 33%" ref="table-subject">
                    <th style="width: 30%">School name </th>
                    <th style="width: 30%">Subjects</th>
                <tr v-for="index in subjectlist.length" v-bind:key="index">
                    <td>{{ subjectlist[index-1].schoolname}}</td>
                    <td>{{ subjectlist[index-1].subjectName}}</td>
                </tr>
            </table>
        </div>
        
        <div class="class-popup" id="myclass">
            <div class="form-container">

                <h1>Create Class</h1>

                <label for="classCode"><b>Class Code</b></label>
                <input type="text" placeholder="Enter class code" name="classCode" v-model="classCode" required>

                <label for="className"><b>Class name</b></label>
                <input type="text" placeholder="Enter class name" name="className" v-model="className" required>

                <label for="TI"> <b>Teaching Institution </b> </label>
               
                <select v-model="schoolNameC">
                    <option v-for="institution in TIlist" v-bind:key="institution.schoolName" v-bind:value="institution.schoolName"> {{institution.schoolName}}</option>
                </select>

                <button type="submit" class="btn" v-on:click = "addclass"> Create </button>
                <button type="button" class="btn cancel" v-on:click="closeFormClass()">Close</button>
            </div>
        </div>

        <div class="subject-popup" id="mysubject">
        <div class="form-container">
                <h1>Create Subjects </h1>

                <label for="subjectName"><b>Subject Name</b></label>
                <input type="text" placeholder="Enter subject Name" name="subjectName" v-model="subjectName" required>

                <label for="TI"> <b>Teaching Institution </b> </label>
               
                <select v-model="schoolNameS">
                    <option v-for="institution in TIlist" v-bind:key="institution.schoolName" v-bind:value="institution.schoolName"> {{institution.schoolName}}</option>
                </select>

                <button type="submit" class="btn" v-on:click = "addsubject()"> Create </button>
                <button type="button" class="btn cancel" v-on:click="closeFormSubject()">Close</button>
            </div>
        </div>

        <div class="school-popup" id="myschool">
            <div class="form-container">
                <h1>Create A Teaching Institution </h1>

                <label for="School name"><b>School Name</b></label>
                <input type="text" placeholder="Enter School Name" v-model="schoolNametxt" required>

                <label for="SchoolType"> <b>Type of Teaching Institution </b> </label>
               
                <select v-model="schoolType">
                    <option value="Elementary School"> Elementary School </option>
                    <option value="High School" > High School </option>
                    <option value="Cegep" > Cegep </option>
                    <option value ="Univercity" > Univercity </option>
                </select>
                
                <button type="submit" class="btn" v-on:click = "addTI()"> Create </button>
                <button type="button" class="btn cancel" v-on:click="closeFormSchool()">Close</button>
            </div>
        </div>

    </div>
</template>

<script>
  export default {
    data: function() {
        return { TIlist: [], schoolNametxt: undefined, schoolType: undefined,
                classlist: [], schoolNameC: undefined, classCode: undefined, className: undefined,
                subjectlist: [], schoolNameS: undefined, subjectName: undefined
        } 
    },
    created: function()
    {
        const userAction = async () => {
            const response = await fetch('http://localhost:8080/subejcts');
            var myJson = await response.json(); //extract JSON from the http response
            // do something with myJson
            this.subejcts = myJson._embedded.subejcts
            this.subejcts.forEach((subject) => subject.name = subejct._links.self.href.substr(subejct._links.self.href.lastIndexOf('/')+1));
            this.subejcts.sort((a,b) => (a.name > b.name) ? 1 : -1);

            const response1 = await fetch('http://localhost:8080/teachinginstitutions');
            myJson = await response1.json(); //extract JSON from the http response
            // do something with myJson
            this.teachinginstitutions = myJson._embedded.teachinginstitutions
            this.teachinginstitutions.forEach((school) => school.name = subejct._links.self.href.substr(school._links.self.href.lastIndexOf('/')+1));
            this.teachinginstitutions.sort((a,b) => (a.name > b.name) ? 1 : -1);

            const response2 = await fetch('http://localhost:8080/courses');
            myJson = await response2.json(); //extract JSON from the http response
            // do something with myJson
            this.courses = myJson._embedded.courses
            this.courses.forEach((course) => course.name = course._links.self.href.substr(course._links.self.href.lastIndexOf('/')+1));
            this.courses.sort((a,b) => (a.name > b.name) ? 1 : -1);

        }
        userAction();
    },
    methods: {
        classes() {
            console.log(document.getElementById("myclass").style.display = "block");
        },
        subjects(){
            console.log(document.getElementById("mysubject").style.display = "block");
        },
        TI(){
            console.log(document.getElementById("myschool").style.display = "block");
        },
        closeFormClass() {
            console.log(document.getElementById("myclass").style.display = "none");
        },
        closeFormSubject() {
            console.log(document.getElementById("mysubject").style.display = "none");
        },
        closeFormSchool() {
            console.log(document.getElementById("myschool").style.display = "none");
        },
        addTI(){
            console.log("Here");
            var institution =
            {
                schoolType: this.schoolType,
                schoolName: this.schoolNametxt
            }
            this.TIlist.push(institution);
        },
            addclass(){
            console.log("Here");
            var class1=
            {
                schoolName: this.schoolNameC,
                classCode: this.classCode,
                className: this.className
            }
            this.classlist.push(class1);
        },
            addsubject(){
            console.log("Here");
            var subject=
            {
                schoolname: this.schoolNameS,
                subjectName: this.subjectName
            }
            this.subjectlist.push(subject);
        }
    }
  }
</script>

<style src="./Style.css" />
<style scoped>

button.classes {
    margin: 0px 100px 20px 0px; 
}
button.subejcts{
    margin: 0px 100px 20px 0px; 
}
button.TI {
    margin: 0px 100px 20px 0px; 
}

.list{
    display:flex;
}

.table-class{
    margin: 0px 0px 0px 30px;
}
.table-subject{
    display: table;
    margin: 0px 0px 0px 30px;
}

/* The popup form - hidden by default */
.class-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}
.subject-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}
.school-popup{
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
</style>