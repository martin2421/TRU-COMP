/*
   Author: Martin Atanacio T00684924
   Date:   7th June 2022

   Filename: comments.js

*/

// runs script after page has finished loading
window.onload = function () {

   // clears username & comment fields before/after reloading page
   document.getElementById("username").value = "";
   document.getElementById("comment").value = "";

   // creates event listener when button is pressed
   document.getElementById("addReview").addEventListener("click", function () {
      submitReview();
   });

}

function submitReview() {

   // values from user
   let username = document.getElementById("username").value;
   let comment = document.getElementById("comment").value;
   let stars = document.getElementById("stars").value;

   // review will be added to a blockquote tag
   let blockquote = document.createElement("blockquote");

   // declaring elements to be added to the blockquote
   let stars_block = document.createElement("p");
   let comment_block = document.createElement("p");
   let user_block = document.createElement("p");
   let date_block = document.createElement("p");

   let comments_node = document.createText_node(comment);
   let signature_node = document.createText_node(" from: @");
   let user_node = document.createText_node(username);
   let date_node = document.createText_node(new Date().toLocaleString());

   // loop decides how many star images to append
   for (let i = 0; i < stars; i++) {
      let stars_img = document.createElement("img");
      stars_img.src = "images/star.png";
      stars_block.appendChild(stars_img);
   }
   blockquote.appendChild(stars_block);

   // adding comment to the blockquote
   comment_block.appendChild(comments_node);
   blockquote.appendChild(comment_block);

   // adding signature + username to the blockquote
   user_block.appendChild(signature_node);
   user_block.appendChild(user_node);
   blockquote.appendChild(user_block);

   // adding date to the blockquote
   date_block.appendChild(date_node);
   blockquote.appendChild(date_block);

   // prepends it all to the review section of the html file
   if (username != "" && comment != "") {
      document.getElementById("reviews").prepend(blockquote);
   }

   // clears textfields
   username.value = "";
   comment.value = "";

}
