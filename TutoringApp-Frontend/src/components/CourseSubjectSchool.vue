<template>
    <div id="main" v-cloak>
        <div class="bar">
            <h2> TutoringApp Manager View - Courses, Subjects, and Teaching Institutions </h2> 
        </div>
            <h3> Welcome, Manager (name)! </h3>
        
        <div class="buttonPart">
            <button class="classes" v-on:click="classes()"> Create Classes</button>
            <button class="subejcts" v-on:click="subjects()"> Create Subjects</button>
            <button class="TI" v-on:click="TI()"> Create Teaching Institutions</button>
        </div>

        <div class="list">

            <table style="width: 100%" ref="table">
                <tr>
                    <th style="width: 30%">Teaching institution</th>
                    <th style="width: 20%">Classes Code</th>
                    <th style="width: 20%">Classes Name</th>
                    <th style="width: 20%">Subjects</th>
                </tr>
            </table>
        </div>
        
        <div class="class-popup" id="myclass">
            <form action="/action_page.php" class="form-container">
                <h1>Create Class</h1>

                <label for="classCode"><b>Class Code</b></label>
                <input type="text" placeholder="Enter class code" name="classCode" required>

                <label for="className"><b>Class name</b></label>
                <input type="text" placeholder="Enter class name" name="className" required>

                <label for="TI"> <b>Teaching Institution </b> </label>
               ///////////
               
                <select>
                    <option v-for="institution in TInstitutions" v-bind:key="institution.name" v-bind:value="institution.name"> {{institution.name}}</option>
                </select>
                //////////////

                <button type="submit" class="btn"> Create </button>
                <button type="button" class="btn cancel" v-on:click="closeFormClass()">Close</button>
            </form>
        </div>

        <div class="subject-popup" id="mysubject">
            <form action="/action_page.php" class="form-container">
                <h1>Create Subjects </h1>

                <label for="subjectName"><b>Subject Name</b></label>
                <input type="text" placeholder="Enter subject Name" name="subjectName" required>

                <label for="TI"> <b>Teaching Institution </b> </label>
               ///////////
               
                <select>
                    <option v-for="institution in TInstitutions" v-bind:key="institution.name" v-bind:value="institution.name"> {{institution.name}}</option>
                </select>
                //////////////

                <button type="submit" class="btn"> Create </button>
                <button type="button" class="btn cancel" v-on:click="closeFormSubject()">Close</button>
            </form>
        </div>

        <div class="school-popup" id="myschool">
            <form action="/action_page.php" class="form-container">
                <h1>Create A Teaching Institution </h1>

                <label for="School name"><b>School Name</b></label>
                <input type="text" placeholder="Enter subject Name" name="subjectName" required>

                <label for="SchoolType"> <b>Type of Teaching Institution </b> </label>
               
                <select>
                    <option> Elementary School </option>
                    <option> High School </option>
                    <option> Cegep </option>
                    <option> Univercity </option>
                </select>
                
                <button v-show="!editIndex" @click="add" class="btn"> Create</button>
                <button type="button" class="btn cancel" v-on:click="closeFormSchool()">Close</button>
            </form>
        </div>

    </div>
</template>

<script>
  export default {
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
        add() {
            this.originalData = null
            this.items.push({ TeachingInstitution: ''})
            this.editIndex = this.items.length - 1
            this.originalData = null
            this.editIndex = null
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