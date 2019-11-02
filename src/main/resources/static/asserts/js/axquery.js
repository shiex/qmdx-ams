var Ax = {};

/* log */
Ax.log = function(msg)
{
	 try {   console.log(msg);     } catch (err) {}
};

// Error code preprocessing:
// return true to indicate filtered processing;Return false to indicate unfiltered
Ax.restErrFilter = function(statusCode, message)
{	
	return false;
}
// Default error handling
Ax.restErrHandler = function(statusCode, message)
{	
	alert(message);
};
// rest there is no return 200 ( May return 500 wait error)
Ax.httpErrHandler = function()
{
	// alert("server error");
};
// serviceUri：serviceName, req：Request parameter object
// dataHandler：Reply data processing function
Ax.rest = function (serviceUri, req, isAsync, dataHandler, restErrHandler)
{
	jQuery.ajax({				
		url: serviceUri, 			
		method: "POST", 
		processData: false,	
		data: JSON.stringify(req), 
		dataType: 'json',
		async: isAsync,
		contentType: "application/json; charset=utf-8",
		success: function(ans){
			if(ans.error != 0)
			{
				// A uniform filter is applied first
				if( Ax.restErrFilter(ans.error, ans.reason)) return;
				
				// Use the fourth parameter (handled by the user's own processor)
				if(restErrHandler != null)
					restErrHandler( ans.error, ans.reason);
				else
				// If the fourth parameter is not provided, go to default processing (alert)
					Ax.restErrHandler (ans.error, ans.reason);
			}				
			else
			{
				dataHandler(ans.data);
			}				
		},
		error: function( jqXHR, textStatus, errorThrown){
			Ax.httpErrHandler();
		},
	});	
}

/* JSONP */
Ax.jsonp = function(URI, req, resultHanlder)
{
	jQuery.ajax({				
			url: URI,	
			method: "GET",
			dataType: "jsonp", // 1: jsonp 
			//jsonpCallback: "callback",
			data: req, // parameter
			success: resultHanlder
	});	
}



	