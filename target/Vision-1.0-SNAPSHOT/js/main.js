	if (!Date.now) {
	  Date.now = function() {
	    return new Date().getTime();
	  }
	}
	var theDate = Date.now();

	document.getElementById('date').innerText = getTheDate(theDate)

	document.getElementById('prev').addEventListener("click", function() {
	  theDate -= 86400000;
	  document.getElementById('date').innerText = getTheDate(theDate)
	})
	document.getElementById('next').addEventListener("click", function() {
	  theDate += 86400000;
	  document.getElementById('date').innerText = getTheDate(theDate)
	})

	function getTheDate(getDate) {
	  var days = ["Sunday", "Monday", "Tuesday",
	    "Wednesday", "Thursday", "Friday", "Saturday"
	  ];
	  var months = ["Jan", "Feb", "Mar",
	    "Apr", "May", "June", "Jul", "Aug",
	    "Sep", "Oct", "Nov", "Dec"
	  ];
	  var theCDate = new Date(getDate);
	  //return days[theCDate.getDay()] + ', ' + theCDate.getDate() + '-' + months[theCDate.getMonth()] + '-' + theCDate.getFullYear();
	  return days[theCDate.getDay()] + ', ' + theCDate.getDate() + ' ' + months[theCDate.getMonth()] + '. ' + theCDate.getFullYear();
	}
