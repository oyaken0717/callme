$(function(){
	$(document).ready(function () {
		hsize = $(window).height();
		$(".form").css("height", hsize + "px");
	});
	
	$(window).resize(function () {
		hsize = $(window).height();
		$(".form").css("height", hsize + "px");
	});
});