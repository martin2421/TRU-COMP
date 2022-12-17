
/* 
     Form Validation
*/

function validateForm() {
     let x = document.forms["myForm"]["fName"].value;
     (x == "") ? alert("Name must be filled out") : alert("Form has been sent");
}

/* 
     Mobile Navigation
*/

const primaryNav = document.querySelector(".primary-nav");
const navToggle = document.querySelector(".mobile-nav-toggle");

navToggle.addEventListener("click", () => {
     const visibility = primaryNav.getAttribute('data-visible');

     if (visibility === "false") {
          primaryNav.setAttribute("data-visible", true);
          navToggle.setAttribute("aria-expanded", true);
     } else if (visibility === "true") {
          primaryNav.setAttribute("data-visible", false);
          navToggle.setAttribute("aria-expanded", false);
     }
})

/* 
     Play Sound Effect on Hover
*/

var audio = new Audio("../resources/click.mp3");
let counter = 0;


function playSound() {
     audio.play();
     counter++;
     console.log(audio);
     console.log("played ", counter, " times");
}

/* 
     Show Current Date
*/

function showDate() {

     let today = new Date();
     const lastModified = document.getElementById("last-modified");
     const currentDate = document.getElementById("current-date");
     const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

     currentDate.innerHTML = today.toLocaleDateString("en-US", options);

     if (lastModified.innerHTML === '') {
          lastModified.innerHTML = today.toLocaleDateString("en-US", options);
     }

}

showDate();

