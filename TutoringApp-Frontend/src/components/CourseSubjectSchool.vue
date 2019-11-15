<template>
    <div class ="main">
        <div class="bar">
            <h2> TutoringApp Manager View - Course Subject and Teaching Institutions </h2> 
        </div>

        <div class="intro">
            <h3> Welcome, Manager (name)! </h3>
        </div>

        <button type="button"> Create School </button>
        <button type="button"> Create Course </button>
        <button type="button"> Create Subject </button>

       <h2>Popup</h2>

        <div class="popup" onclick="myFunction()">Click me to toggle the popup!
            <span class="popuptext" id="myPopup">A Simple Popup! </span>
        </div>
    </div>

</template>

<script>
function myFunction() {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}
</script>

<style src="./Style.css">

/* Popup container */
.popup {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

/* The actual popup (appears on top) */
.popup .popuptext {
  visibility: hidden;
  width: 160px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

/* Toggle this class when clicking on the popup container (hide and show the popup) */
.popup .show {
  visibility: visible;
  -webkit-animation: fadeIn 1s;
  animation: fadeIn 1s
}

/* Add animation (fade in the popup) */
@-webkit-keyframes fadeIn {
  from {opacity: 0;}
  to {opacity: 1;}
}

@keyframes fadeIn {
  from {opacity: 0;}
  to {opacity:1 ;}
}
</style>
