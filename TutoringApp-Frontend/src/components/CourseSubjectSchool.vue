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
                v-bind:list="subjectlist"
                searchid="name"
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
import AXIOS from "./Axios";

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
            AXIOS.get('/teachinginstitutions').then(response =>
            {
                this.TIlist = response.data._embedded.teachinginstitutions
                this.TIlist.forEach((school) => school.name = school._links.self.href.substr(school._links.self.href.lastIndexOf('/')+1));
                this.TIlist.sort((a,b) => (a.type > b.type) ? 1 : -1);  //sort by school TYPE
            })
            .catch(e => console.log(e.response.data.message));

            AXIOS.get('/courses').then(response =>
            {
                this.classlist = response.data._embedded.courses;
                this.classlist.sort((a,b) => (a.school > b.school) ? 1 : -1);   //sort by associated school
            })
            .then(async () =>
            {
                for (var i = 0; i < this.classlist.length; i++)
                {
                    var course = this.classlist[i];
                    course.courseCode = course._links.self.href.substr(course._links.self.href.lastIndexOf('/')+1) //parse course code from endpoint path

                    await AXIOS.get(course._links.school.href)
                        .then(response => course.school = response.data._links.self.href.substr(response.data._links.self.href.lastIndexOf('/')+1))
                        .catch(e => console.log(e.response.data.message));
                }
                this.$forceUpdate();        // Little bug in vue requires this
            })
            .catch(e => console.log(e.response.data.message));

            AXIOS.get('/subjects').then(response =>
            {
                this.subjectlist = response.data._embedded.subjects;
                this.subjectlist.sort((a,b) => (a.school > b.school) ? 1 : -1);   //sort by associated school
            })
            .then(async () =>
            {
                for (var i = 0; i < this.subjectlist.length; i++)
                {
                    var subject = this.subjectlist[i];
                    subject.name = subject._links.self.href.substr(subject._links.self.href.lastIndexOf('/')+1);

                    await AXIOS.get(subject._links.school.href)
                        .then(response => subject.school = response.data._links.self.href.substr(response.data._links.self.href.lastIndexOf('/')+1))
                        .catch(e => console.log(e.response.data.message));
                }
                this.$forceUpdate();        // Little bug in vue requires this
            })
            .catch(e => console.log(e.response.data.message));
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
            AXIOS.post('/teachinginstitutions/'.concat(this.schoolNametxt),
                    {}, { params: { type: this.schoolType }})
                    .then(response =>
                    {
                        this.$alert("School successfully added!", '', 'success');
                        this.refreshFromBackend();
                    })
                    .catch(e => this.$alert("Error adding a new school!", '', 'error'));

            this.closeFormAll();
        },
        addclass(){
            AXIOS.post('/courses/'.concat(this.classCode),
                    {}, { params: { name: this.className, school: this.schoolNameC }})
                    .then(response =>
                    {
                        this.$alert("Course successfully added!", '', 'success');
                        this.refreshFromBackend();
                    })
                    .catch(e => this.$alert("Error adding a new course!", '', 'error'));

            this.closeFormAll();
        },
        addsubject(){
            AXIOS.post('/subjects/'.concat(this.subjectName),
                    {}, { params: { school: this.schoolNameS }})
                    .then(response =>
                    {
                        this.$alert("Subject successfully added!", '', 'success');
                        this.refreshFromBackend();
                    })
                    .catch(e => this.$alert("Error adding a new subject!"), '', 'error');

            this.closeFormAll();
        }
    }
  }
</script>

<style src="./Style.css" />
<style scoped>

button {
    margin: 0px 100px 20px 0px; 
}

.list{
    display:flex;
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