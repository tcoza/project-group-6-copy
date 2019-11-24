<template>
    <div id="main" v-cloak>
        <topbar title="Courses, Subjects, and Teaching Institutions" />
        <h3>View schools, courses, and subjects here. You can add new courses, subjects or schools below!</h3>
           
        <div class="buttonPart">
            <button class="TI" v-on:click="closeFormClass();closeFormSubject();TI()"> ADD SCHOOL </button>
            <button class="classes" v-on:click="closeFormSubject();closeFormSchool();classes()"> ADD COURSE </button>
            <button class="subjects" v-on:click="closeFormClass();closeFormSchool();subjects()"> ADD SUBJECT </button>
        </div>

        <div class="list">
            <cooltable
                title="Schools"
                v-bind:headers="[
                    { name: 'Type', width: '50%' },
                    { name: 'School Name', width: '50%' }]"
                v-bind:columns="['type', 'name']"
                v-bind:list="TIlist"
                searchid="name"
                width="33%"
            />

            <cooltable
                title="Courses"
                v-bind:headers="[
                    { name: 'School', width: '30%' },
                    { name: 'Course Code', width: '30%' },
                    { name: 'Course Name', width: '40%' }]"
                v-bind:columns="['school', 'courseCode', 'name']"
                v-bind:list="classlist"
                searchid="courseCode"
                width="33%"
            />

            <cooltable
                title="Subjects"
                v-bind:headers="[
                    { name: 'School', width: '50%' },
                    { name: 'Subject', width: '50%' }]"
                v-bind:columns="['school', 'name']"
                v-bind:list="classlist"
                searchid="subjectlist"
                width="33%"
            />
        </div>
        
        <div class="class-popup" id="myclass">
            <div class="form-container">

                <h1>Add A Course</h1>

                <input type="text" placeholder="Enter course code" name="classCode" v-model="classCode" required>
                <input type="text" placeholder="Enter course name" name="className" v-model="className" required>
                
                <label for="TI"> <b> Select School </b> </label>
                <br/>
                <select style="width:100%" v-model="schoolNameC">
                    <option v-for="institution in TIlist" v-bind:key="institution.name" v-bind:value="institution.name"> {{institution.name}}</option>
                </select>
                <br/><br/>
                <button type="submit" class="btn" v-on:click = "addclass"> CREATE </button>
                <button type="button" class="btn cancel" v-on:click="closeFormAll()">CLOSE</button>
            </div>
        </div>

        <div class="subject-popup" id="mysubject">
        <div class="form-container">
                <h1> Add A Subject </h1>
                <input type="text" placeholder="Enter subject name" name="subjectName" v-model="subjectName" required>

                <label for="TI"> <b> Select School </b> </label>
                <br/>
                <select style="width:100%" v-model="schoolNameS">
                    <option v-for="institution in TIlist" v-bind:key="institution.name" v-bind:value="institution.name"> {{institution.name}}</option>
                </select>
                <br/><br/>
                <button type="submit" class="btn" v-on:click = "addsubject()"> CREATE </button>
                <button type="button" class="btn cancel" v-on:click="closeFormAll()">CLOSE</button>
            </div>
        </div>

        <div class="school-popup" id="myschool">
            <div class="form-container">
                <h1>Add A School</h1>
                
                <input type="text" placeholder="Enter School Name" v-model="schoolNametxt" required>
                
                <label for="TI"> <b> Select School Type </b> </label>
                <br/>
                <select style="width:100%" v-model="schoolType">
                    <option value="OTHER"> Elementary School / Other </option>
                    <option value="HIGHSCHOOL" > High School </option>
                    <option value="CEGEP" > CEGEP </option>
                    <option value="UNIVERSITY" > University </option>
                </select>
               <br/><br/>
                <button type="submit" class="btn" v-on:click = "addTI()"> CREATE </button>
                <button type="button" class="btn cancel" v-on:click="closeFormAll()">CLOSE</button>
            </div>
        </div>

    </div>
</template>

<script>
import cooltable from "./CoolTable";
import topbar from "./TopBar";

  export default {
    components:
    {
        cooltable,
        topbar
    },
    data: function() {
        return { TIlist: [], schoolNametxt: undefined, schoolType: undefined,
                classlist: [], schoolNameC: undefined, classCode: undefined, className: undefined,
                subjectlist: [], schoolNameS: undefined, subjectName: undefined
        } 
    },
    created: function()
    {
        this.refreshFromBackend();
    },
    methods: {
        refreshFromBackend() {
            const populateTI = async () => {
                const response = await fetch('http://localhost:8080/teachinginstitutions');
                const myJson = await response.json(); //extract JSON from the http response
                this.TIlist = myJson._embedded.teachinginstitutions //get all schools
                this.TIlist.forEach((school) => school.name = school._links.self.href.substr(school._links.self.href.lastIndexOf('/')+1));
                
                //sort by school TYPE
                this.TIlist.sort((a,b) => (a.type > b.type) ? 1 : -1);
            };

            const populateCourses = async () => {
                const response = await fetch('http://localhost:8080/courses');
                const myJson = await response.json(); //extract JSON from the http response
                this.classlist = myJson._embedded.courses //get all courses
                for (var i = 0; i < this.classlist.length; i++) {
                    var course = this.classlist[i];

                    //1) get coursecode
                    course.courseCode = course._links.self.href.substr(course._links.self.href.lastIndexOf('/')+1) //parse course code from endpoint path

                    //2) get school associated
                    const schoolResponse = await fetch(course._links.school.href);
                    const mySchool = await schoolResponse.json();
                    course.school = mySchool._links.self.href.substr(mySchool._links.self.href.lastIndexOf('/')+1)
                }
                //sort by associated school
                this.classlist.sort((a,b) => (a.school > b.school) ? 1 : -1);
            };

            const populateSubjects = async () => {
                const response = await fetch('http://localhost:8080/subjects');
                const myJson = await response.json(); //extract JSON from the http response
                this.subjectlist = myJson._embedded.subjects //get all courses

                for (var i = 0; i < this.subjectlist.length; i++){
                    var sbj = this.subjectlist[i];

                    //1) get subjectname
                    sbj.name = sbj._links.self.href.substr(sbj._links.self.href.lastIndexOf('/')+1) //parse course code from endpoint path

                    //2) get school associated
                    const schoolResponse = await fetch(sbj._links.school.href);
                    const mySchool = await schoolResponse.json();
                    sbj.school = mySchool._links.self.href.substr(mySchool._links.self.href.lastIndexOf('/')+1)
                }
                //sort by associated school
                this.subjectlist.sort((a,b) => (a.school > b.school) ? 1 : -1);
            };

            populateTI();
            populateCourses();
            populateSubjects();
        },
        classes() {
            document.getElementById("myclass").style.display = "block";
        },
        subjects(){
            document.getElementById("mysubject").style.display = "block";
        },
        TI(){
            document.getElementById("myschool").style.display = "block";
        },
        closeFormClass() {
             //reset form
            this.classCode="";
            this.className="";
            this.schoolNameC="";
            document.getElementById("myclass").style.display = "none";
        },
        closeFormSubject() {
             //reset form
            this.schoolNameS="";
            this.subjectName="";
            document.getElementById("mysubject").style.display = "none";
        },
        closeFormSchool() {
            //reset form
            this.schoolNametxt="";
            this.schoolType="";
            document.getElementById("myschool").style.display = "none";
        },
        closeFormAll() {
            this.closeFormSchool();
            this.closeFormSubject();
            this.closeFormClass();
        },
        addTI(){
            //create a new TI using REST api services
           // console.log("Here");
            var url = 'http://localhost:8080/teachinginstitutions/' + this.schoolNametxt;
            
            const userAction = async () => {
                const response = await fetch(url,
                {
                    method: "POST",
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: "type=" + this.schoolType
                });

                if (!response.ok) {
                    alert("Error adding a new school!");
                }
                else {
                    alert("School successfully added!");
                    this.refreshFromBackend();
                }
                   
                this.closeFormAll();
            }
            userAction();
        },
        addclass(){
             //create a new Course using REST api services
            // console.log("Here");
            var url = 'http://localhost:8080/courses/' + this.classCode;
            
            const userAction = async () => {
                const response = await fetch(url,
                {
                    method: "POST",
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: "name=" + this.className + "&school=" + this.schoolNameC
                });

                if (!response.ok) {
                     alert("Error adding a new course!");
                }
                 else {
                    alert("Course successfully added!");
                    this.refreshFromBackend();
                }
                
                this.closeFormAll();
            }
            userAction();
        },
        addsubject(){
             //create a new Course using REST api services
            // console.log("Here");
            var url = 'http://localhost:8080/subjects/' + this.subjectName;
            
            const userAction = async () => {
                const response = await fetch(url,
                {
                    method: "POST",
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: "school=" + this.schoolNameS
               });

                if (!response.ok) {
                     alert("Error adding a new subject!");
                }
                 else {
                    alert("Subject successfully added!");
                    this.refreshFromBackend();
                }
                
                this.closeFormAll();
            }
            userAction();
            
        }
    }
  }
</script>

<style src="./Style.css" />
<style scoped>

button.classes {
    margin: 0px 100px 20px 0px; 
}
button.subjects{
    margin: 0px 100px 20px 0px; 
}
button.TI {
    margin: 0px 100px 20px 0px; 
}

.table-title {
    background-color: #afe5fc;
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