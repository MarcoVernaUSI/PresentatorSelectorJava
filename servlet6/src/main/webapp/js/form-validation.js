(function() {
  'use strict';
  window.addEventListener('load', function() {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function(form) {

      form.addEventListener('submit', function(event) {
    	  if (checkFormValidity() === false) {
    		  event.preventDefault();
              event.stopPropagation();
    	  }
    	  form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();

function showFeed (el) {
	document.getElementsByClassName(el)[0].style.display = "block";
	document.getElementById(el).classList.add("is-invalid");
	document.getElementById(el).classList.remove("is-valid");

}

function hideFeed (el) {
	document.getElementsByClassName(el)[0].style.display = "none";
	document.getElementById(el).classList.add("is-valid");
	document.getElementById(el).classList.remove("is-invalid");

}

function setValidField (el) {
	document.getElementById(el).classList.add("is-valid");
	document.getElementById(el).classList.remove("is-invalid");	
}

function setInvalidField (el) {
	document.getElementById(el).classList.add("is-invalid");
	document.getElementById(el).classList.remove("is-valid");	
}

function checkFormValidity() {
	
	var letters = /^[A-Za-z]+$/;
	var digits = /^\d+$/;
	
	if ((fieldValidation("name", letters) && maxLengthValidation("name", 15)) &
	    (fieldValidation("totalseats", digits) && maxLengthValidation("totalseats", 2)) &
	    (fieldValidation("location", letters) && maxLengthValidation("location", 20)) 
	    ) {
		console.log("valid");
		return true;
	} 
	return false;
//	if (form.checkValidity() === false) {
//		console.log("check validity failed");
//		return false;
////		stopPropagation();
//		
//	} else {
//		console.log("check validity true");
//	}
}

function fieldValidation(fieldName, rule) {
	if (document.getElementById(fieldName) != null) {
	  if (document.getElementById(fieldName).value != "" && 
		 !document.getElementById(fieldName).value.match(rule)) {
		  console.log(fieldName + "doesn't match rule.");
		  showFeed(fieldName);
		  return false;
	  } else if (document.getElementById(fieldName).value == "") {
		  setInvalidField(fieldName);
		  return false;
	  } else {
		  hideFeed(fieldName);
		  setValidField(fieldName);
		  document.getElementById(fieldName).setCustomValidity("");
		  return true;
	  }
	 }
	return false;
}

function maxLengthValidation(el, maxLength) {
	if (document.getElementById(el) != null) {
		if (document.getElementById(el).value != "") {
			var text = document.getElementById(el).value;
			console.log();
			if (text.length <= maxLength) {
				hideFeed(el);
				return true;
				document.getElementById(fieldName).setCustomValidity("");
			} else {
				showFeed(el);
				return false;
			}	
		}
	}
	return false;
}

function numberPartecipiants() {
	var numberPartecipiants  = 0;
	if (document.getElementById("number") != null) {
		 numberPartecipiants = document.getElementById("number").value;
	}
	return numberPartecipiants;
}

function stopPropagation () {
	event.preventDefault();
	event.stopPropagation();
}

$(document).ready(function() {
	$('#start').datetimepicker({
	});
});


