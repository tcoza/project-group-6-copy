<template>
    <div id="main" v-cloak>
        <div class="bar">
            <h2> TutoringApp Manager View - Courses, Subjects, and Teaching Institutions </h2> 
        </div>
            <h3> Welcome, Manager (name)! </h3>
        <div class ="tables">
            <table class ="tutor" style="width: 100%" ref="table">
                <tr>
                    <th style="width: 30%">Student Name </th>
                    <th style="width: 20%"> Number of evaluations</th>
                </tr>
            </table>

            <table class= "student" style="width: 100%" ref="table">
                <tr>
                    <th style="width: 30%">Student Name </th>
                    <th style="width: 20%"> Number of evaluations</th>
                </tr>
            </table>
        </div>
    </div>
</template>
<script>
</script>
<style src="./Style.css" />
