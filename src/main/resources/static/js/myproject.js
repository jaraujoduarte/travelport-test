$(function () {
  
  $.getJSON( "search/", function( data ) {
	  console.log(data);
	  
	  // Grab the template script
	  var theTemplateScript = $("#main-template").html();

	  // Compile the template
	  var theTemplate = Handlebars.compile(theTemplateScript);

	  // Pass our data to the template
	  var theCompiledHtml = theTemplate(data);

	  $('#main-cont').html(theCompiledHtml);
  });
});