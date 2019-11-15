<template>
    <form id="main" v-cloak>
        <div class="bar">
            <h2> TutoringApp Manager View - Courses, Subjects, and Teaching Institutions </h2> 
        </div>
            <h3> Welcome, Manager (name)! </h3>
        <div>
        
        <div class="buttonPart">
            <button class="classes" v-on:click="classes()"> Create Classes</button>
            <button class="subejcts" v-on:click="subjects()"> Create Subjects</button>
            <button class="TI" v-on:click="TI()"> Create Teaching Institutions</button>
        </div>

        <div class="list">
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
                <button type="button" class="btn cancel" v-on:click="closeForm()">Close</button>
            </form>
        </div>

     </div>

    </form>
</template>

<script>
  export default {
    methods: {
        classes() {
            console.log(document.getElementById("myclass").style.display = "block");
        },
        closeForm() {
            console.log(document.getElementById("myclass").style.display = "none");
        },
        addToList: function () {
            this.messages.push(this.msg);
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
