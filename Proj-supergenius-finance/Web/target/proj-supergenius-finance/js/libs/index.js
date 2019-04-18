(function() {
	$(document).ready(function() {
		new Broadcast($('#lunbo_pc'), {
			init : function() {
				var self = this;
				this.element.find('.banner_list [data-index="0"]').css('left', "15px").addClass('active').show();
			},
			select : function(index, before) {
				for (var i = 0; i < this.count; i++) {
					if (i !== this.currentIndex) {
						this.element.find('.banner_list [data-index="' + i + '"]').hide();
					}
				}
				this.element.find('.banner_list [data-index="' + index + '"]').css({
					'left' : "15px"
				}).show();
				this.element.find('.banner_list [data-index="' + this.currentIndex + '"]').animate({
					"left" : "-100%"
				}, 1000).hide();

			},
			count: $("#lunbo_pc li").length,
			interval: 5
		});

	});
})();
