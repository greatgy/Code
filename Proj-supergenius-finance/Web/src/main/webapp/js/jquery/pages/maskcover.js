
function maskCover (editContainer) {
	console.log($(".bottom").offset());
	var bottomh = $(".bottom").offset().top + $(".bottom").height()/3;

	var windowh = $(window).innerHeight();
	var editContainerh = $editContainer.height();
	var tophigh = (windowh - editContainerh)/2;
	var editContainerleft = ($(window).outerWidth() - $editContainer.width())/2;
	$("#maskcover").height(bottomh);
	$editContainer.css({ "top": tophigh, "left": editContainerleft });

}
