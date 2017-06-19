<html>
<head>
  <!-- Loading bootstrap locally -->
  <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.css">
  
  <!-- Loading jquery locally -->
  <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js" ></script>
</head>
<body>

	<!-- Main div that contains the screen content -->
	<div id="div" class="panel panel-primary">
	
		<!-- Bootstrap class to display the footer panel -->
  		<div class="panel panel-default">
	  		<div class="panel-heading">
	  			<p>Header panel</p>
	  			<p>All the Header content should be here!</p>
	  		</div>
		</div>
	
		<!-- Div to display the values typed -->
		<div id="alert-div" class="alert alert-success" role="alert" style="display: none;">
		</div>
	
		<!-- Header title -->
		<div class="panel-heading">This is the header title!</div>
		
		<!-- Bootstrap class to display the screen content -->
  		<div class="jumbotron">
    		<table id="table">
				<tr>
					<td><label>First field:</label></td>
					<td><input id="firstField" type="text"/></td>
				</tr>
				<tr>
					<td><label>Second field:</label></td>
					<td><input id="secondField" type="text"/></td>
				</tr>
			</table>
			
			<!-- Bootstrap class to display the buttons -->
			<div class="btn-group">
				<input type="button" id="sendButton" class="btn btn-primary" value="Send" onclick="onSendClick();"/>
				<input type="button" id="cancelButton" class="btn btn-default" value="Cancel" onclick="onCancelClick();"/>
			</div>
  		</div>
  		
  		<!-- Bootstrap class to display the footer panel -->
  		<div class="panel panel-info">
	  		<div class="panel-footer">
	  			<p>Footer panel</p>
	  			<p>All the footer content should be here!</p>
	  		</div>
		</div>
	</div>
	
</body>
</html>

<script type="text/javascript">

	//Send button function
	function onSendClick() {
		var firstField = $("#firstField").val();
		var secondField = $("#secondField").val();
		
		var divContent = "First field value: <b>" + firstField + "</b> </br>";
		divContent += "Second field value: <b>" + secondField + "</b> </br>";
		
		//Displaying the content inside alert-div
		$("#alert-div").html(divContent);
		$("#alert-div").show();
	}
	
	//Cancel button function
	function onCancelClick() {
		$("#firstField").val('');
		$("#secondField").val('');
		$("#alert-div").html('');
		$("#alert-div").hide('');
	}
</script>
