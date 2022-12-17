/*
   Author: Martin Atanacio T00684924
   Date:   7th June 2022

   Filename: comments.js

*/

function submitRate() {

   // user values
   var user = document.getElementById("username").value;
   var comment = document.getElementById("comment").value;
   var stars = document.getElementById("stars").value;

   // review elements to be added
   var block = document.createElement("blockquote");
   var star_paragraph = document.createElement("p");

   // loop decides how many star images to append
   for (var i = 0; i < stars; i++) {
      var starImg = document.createElement("img");
      starImg.src = "images/star.png";
      star_paragraph.appendChild(starImg);
   }
   block.appendChild(star_paragraph);

   // adding comment to the blockquote
   var comment_paragraph = document.createElement("p");
   var commentsNode = document.createTextNode(comment);
   comment_paragraph.appendChild(commentsNode);
   block.appendChild(comment_paragraph);

   // adding user to the blockquote
   var user_paragraph = document.createElement("p");
   var signature = "- ";
   var signatureNode = document.createTextNode(signature);
   user_paragraph.appendChild(signatureNode);

   var userNode = document.createTextNode(user);
   user_paragraph.appendChild(userNode);
   block.appendChild(user_paragraph);

   // adding date to the blockquote
   var date_paragraph = document.createElement("p");
   var today = new Date().toLocaleDateString();
   var dateNode = document.createTextNode(today);
   date_paragraph.appendChild(dateNode);
   block.appendChild(date_paragraph);

   // prepends it all to the review section of the html file
   if (user != "" && comment != "") {
      document.getElementById("reviews").prepend(block);
   }

   // clears textfields
   document.getElementById("username").value = "";
   document.getElementById("comment").value = "";

}

// runs script after page has finished loading
window.onload = function () {

   // creates event listener when button is pressed
   document.getElementById("addReview").addEventListener("click", function () {
      submitRate();
   });
}
