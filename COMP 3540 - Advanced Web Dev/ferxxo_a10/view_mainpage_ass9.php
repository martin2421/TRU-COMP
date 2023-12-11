
<?php


if (isset($rows)) {
    $html = "<table>";
    $html .= "<tbody>";
    foreach ($rows as $user) {
        $html .= "<tr><td>{$user['Username']}</td></tr>";
    }
    $html .= "</tbody></table>";
    
}

?>


<!DOCTYPE html>
<html>


<head>
  <style>
    #content {
      position: absolute;
      left: 3em;
      top: 2em;
    }

    .menu-pane {
      position: absolute;
      top: 0;
      left: 0;
      float: left;
      width: 20%;
      text-align: center;
    }

    #logo {
      top: 2em;
      position: absolute;
      width: 200px;
      height: 100px;
      left: calc((100% - 200px)/2);
    }

    #search {
      position: absolute;
      width: 50px;
      height: 50px;
      right: 2em;
      top: 11em;
    }

    #send {
      position: absolute;
      width: 50px;
      height: 50px;
      right: 2em;
      top: 15em;
    }

    #read {
      position: absolute;
      width: 50px;
      height: 50px;
      right: 2em;
      top: 19em;
    }

    #signout {
      position: absolute;
      width: 50px;
      height: 50px;
      right: 2em;
      top: 26em;
    }

    .main-result-pane {
      position: absolute;
      top: 0;
      left: 20%;
      width: 70%;
    }

    .vertical-line {
      border-left: 2px solid gray;
      border-right: 2px solid gray;
      width: 60%;
      height: 100%;
      position: absolute;
      left: 20%;
      top: 0;
    }
  </style>
</head>

<body>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

<div class="menu-pane">
    <img id="logo" src="TRU_Logo.png">
    <img id="search" src="search.png">
    <img id="send" src="send.png">
    <img id="read" src="read.png">
    <img id="signout" src="user.png">
</div>

<div class="vertical-line"></div>

<div class="main-result-pane">
    <div id="content">
        <h1>Friends</h1>
        <div id="result"></div>
        <br>
        <h1>Messages</h1>
        <h2>Unread Messages</h2>
        <div id="unread-messages"></div>
        <h2>Read Messages</h2>
        <div id="read-messages"></div>
    </div>
</div>

<form id="form1" action="controller_ass9.php" method="post" style="display: none;">
    <input type="hidden" name="page" value="main_page">
    <input type="hidden" name="command" value="signOut">
    <input type="submit" id="signout-submit">
</form>

<div class="modal fade" id="e1-modal-search-friends">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="controller_ass9.php">
                <div class="modal-header">
                    <h2 class="modal-title">Search Friends</h2>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <input type="hidden" name="page" value="main_page">
                        <input type="hidden" name="command" value="SearchFriends">
                        <label class="control-label" for="search-term">Search:</label> 
                        <input type="text" class="form-control" id="search-term" name="term" placeholder="Enter username or part of username">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-outline-primary">Submit</button>
                </div>  
            </form>             
        </div>  
    </div>    
</div>   

<!-- Send Message Modal -->
<div class="modal fade" id="send-message-modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title">Send a Message</h2>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="input-group">
          <label class="control-label" for="receiver-username">Receiver:</label>
          <input type="text" class="form-control" name="receiver-username" id="receiver-username" placeholder="Enter the receiver's username">
        </div>
        <div class="input-group">
          <label class="control-label" for="message">Message:</label>
          <textarea class="form-control" name="message" id="message" placeholder="Enter a message to send"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <div class="input-group">
          <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
          <button type="button" class="btn btn-outline-primary" id="send-message-submit">Submit</button>
        </div>
      </div>
    </div>
  </div>
</div>


<script>
  // When the last image in the menu pane is clicked,
  // the form with the "signout" command is submitted to the controller
  document.getElementById("signout").addEventListener("click", function() {
    document.getElementById("form1").submit();
  });

  $(document).ready(function() {
    $("#search").click(function() {
      $("#e1-modal-search-friends").modal("show");
    });

    // Close modal when cancel button is clicked
    $("#e1-modal-search-friends .modal-footer button[data-bs-dismiss='modal']").click(function() {
      $("#e1-modal-search-friends").modal("hide");
    });
  });


  $(document).ready(function() {
    $("#send").click(function() {
      $("#send-message-modal").modal("show");
    });

    // Close modal when cancel button is clicked
    $("#send-message-modal .modal-footer button[data-bs-dismiss='modal']").click(function() {
      $("#send-message-modal").modal("hide");
    });
  });

  // Start the timer when the user logs in or performs an activity
  var timer = setTimeout(function() {
    // Send the logout command to the server
    document.getElementById("form1").submit();
  }, 10000); // 10 seconds timeout

  // Reset the timer whenever the user performs an activity
  document.addEventListener("mousemove", function() {
    clearTimeout(timer);
    timer = setTimeout(function() {
      // Send the logout command to the server
      document.getElementById("form1").submit();
    }, 10000); // 10 seconds timeout
  });

  // Submit the send message command using AJAX
  $("#send-message-submit").click(function() {
    // Get the input values from the modal
    var receiver = $("#receiver-username").val();
    var message = $("#message").val();

    // Send the AJAX request to the controller
    $.ajax({
      url: "controller_ass9.php",
      type: "POST",
      data: {
        page: "main_page",
        command: "SendMessage",
        receiver: receiver,
        message: message
      },
      success: function(result) {
        // Display the result using alert()
        alert("Message sent successfully to: " + receiver);

        // Hide the modal window

            // Hide the modal window
            $('#send-message-modal').modal('hide');
        },
        error: function() {
            alert("Error occurred while submitting the SendMessage command");
        }
    });
});

// Read messages command
$("#read").click(function() {
    // Send the AJAX request to the controller
    $.ajax({
        url: "controller_ass9.php",
        type: "POST",
        data: {
            page: "main_page",
            command: "ReadMessages"
        },
        success: function(result) {
            // Display the result in the "Unread Messages" pane
            $('#unread-messages').html(result);
        },
        error: function() {
            alert("Error occurred while submitting the ReadMessages command");
        }
    });
});
  </script>

</body>



<script>
//using Jquery
$(document).ready(function() {
    // get the new HTML content
    var newHtml = "<?php echo $html; ?>";
    
    // replace the content of the 'result' div with the new HTML
    $('#result').html(newHtml);
});
</script>







</html>
