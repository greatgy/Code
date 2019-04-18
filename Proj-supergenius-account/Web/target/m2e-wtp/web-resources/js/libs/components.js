// 定义通用的组件
(function() {
	$.ellipsisText = function(text, length) {
		if(text.length > length) {
			return text.substring(0, length) + '...';
		} else {
			return text;
		}
	};
	Components = {};
	//轮播面板
	Components.BroadcastPanel = function(element, options) {
		this.element = element;
		this.options = options ? options : {};
		this.options.interval = this.options.interval ? this.options.interval : 5;
		this.options.hotsCount = this.options.hotsCount ? this.options.hotsCount : 4;
		this.render();
	};
	Components.BroadcastPanel.prototype.render = function() {
		this.element.empty();
		var self = this;
		this.element.html(new EJS({text: $('#broadcast-template').html()}).render(self.options));
		this.element.find('[index]').mouseenter(function(e) {
			self.pause();
			self.select(parseInt($(e.currentTarget).attr('index')));
		});
		this.element.find('[index]').mouseleave(function() {
			self.goahead();
		});
		this.element.find('.broadcast-next').click(function() {
			self.pause();
			self.next();
			self.goahead();
		});
		this.element.find('.broadcast-previous').click(function() {
			self.pause();
			self.previous();
			self.goahead();
		});
		this.select(0);
		this.play();
	};

	Components.BroadcastPanel.prototype.setHots = function(hots) {
		this.options.hots = hots;
		this.render();
	};

	Components.BroadcastPanel.prototype.play = function() {
		var self = this;
		var poll = function() {
			if (!self.paused) {
				self.next();
			}
			setTimeout(poll, self.options.interval * 1000);
		};
		setTimeout(poll, self.options.interval * 1000);
	};

	Components.BroadcastPanel.prototype.pause = function() {
		this.paused = true;
	};

	Components.BroadcastPanel.prototype.goahead = function() {
		this.paused = false;
	};

	Components.BroadcastPanel.prototype.next = function() {
		this.select((this.currentIndex + 1) % this.options.hotsCount);
	};

	Components.BroadcastPanel.prototype.previous = function() {
		var index = this.currentIndex === 0 ? this.options.hotsCount - 1 : this.currentIndex - 1;
		this.select(index % this.options.hotsCount);
	};

	Components.BroadcastPanel.prototype.select = function(index) {
		this.element.find('[index="' + this.currentIndex + '"]' + ' .broadcast-cover').css({
			'top' : '0px'
		});
		this.currentIndex = index;
		var hot = this.options.hots[index] ? this.options.hots[index] : {
			link : '',
			img : '',
			title : '无'
		};
		this.element.find('.broadcast-center').attr('href', hot.link);
		this.element.find('.broadcast-center > img').attr('src', hot.img);
		var shortTitle = $.ellipsisText(hot.title, 23);
		this.element.find('.broadcast-link > a').attr('title', hot.title).attr('href', hot.link).html(shortTitle);
		this.element.find('[index="' + index + '"]' + ' .broadcast-cover').css({
			'top' : '100%'
		});
	};

	//天平面板
	Components.BalancePanel = function(element, options) {
		this.element = element;
		this.options = options;
		var self = this;
		this.element.html(new EJS({text : $('#balance-template').html()}).render(options));
		this.lean();
		this.initEvents();
	};
	
	Components.BalancePanel.prototype.initEvents = function() {
		$(".debate_vote").click(function(){
			if(hasvote != -1){// 已经投过票，不请求后台
				alertvote(hasvote);
				return;
			}
			var support = $(this).data("support");
			if(support == "red") {
				isred = true;
			}else{
				isred = false;
			}
			var curobj = this;
			$.get(g.base + "/debate/vote/" + debateuid, {isred:isred}, function(data){
				if(data.success == true){
					var curvote = $(curobj).html();
					$(curobj).html(parseInt(curvote) + 1);
					new Components.BalancePanel($('.tp_midd'), {
						left : parseInt($(".left-number").html()),
						right : parseInt($(".right-number").html())
					});	
					handleHasvote(isred);
				}else{
					if(data.voted == true){
						alertvote(data.isred);
						alertisred(isred);
					}else{
						alert("未知错误，投票失败，请刷新重试！");
					}
				}
			});
		});
	}

	Components.BalancePanel.prototype.setValues = function(left, right) {
		this.options.left = left;
		this.options.right = right;
		this.element.find('.left-number').html(left);
		this.element.find('.right-number').html(right);
		this.lean();
	};

	Components.BalancePanel.prototype.lean = function() {
		var width = this.element.find('.balance-lever').width() / 2;
		var height = this.element.find('.balance-pivot').height();
		var totalAngle = Math.atan(height / width);
		var angle = 0, changedLeftHeight = 0, changedRightHeight = 0;
		if (this.options.left > this.options.right) {
			var diffPercent = (this.options.left - this.options.right) / this.options.left;
			angle = -totalAngle * diffPercent;
			changedLeftHeight = (width - this.element.find('.left-data').width()) * Math.tan(angle);
			changedRightHeight = width * Math.tan(angle);
		} else if (this.options.left < this.options.right) {
			var diffPercent = (this.options.right - this.options.left) / this.options.right;
			angle = totalAngle * diffPercent;
			changedLeftHeight = width * Math.tan(angle);
			changedRightHeight = (width - this.element.find('.left-data').width()) * Math.tan(angle);
		}
		if ($.support.leadingWhitespace) {
			this.element.find('.balance-lever').css('transform', 'rotate(' + angle / Math.PI * 180 + 'deg)');
			this.element.find('.left-data').css('top', (65 - changedLeftHeight) + 'px');
			this.element.find('.right-data').css('top', (65 + changedRightHeight) + 'px');
		}
		this.element.find('.left-lever').css('width', (this.options.left / (this.options.left + this.options.right) * 79 + 10) + '%');
		var rightWidth = this.options.right / (this.options.left + this.options.right) * 79 + 10;
		this.element.find('.right-lever').css('width', rightWidth + '%').css('margin-left', (100 - rightWidth) + '%');
	};
})();
