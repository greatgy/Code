(function() {
	Broadcast = function(element, options) {
		this.element = element;
		if (options) {
			$.extend(this, options);
		}
		this.interval = this.interval ? this.interval : 2;
		this.count = this.count ? this.count : 3;
		this.currentIndex = 0;
		var self = this;
		this.select = function(index, before) {
			index = self.realIndex(index);
			before = before === undefined ? index < self.currentIndex : before;
			var select = function(index, before) {
				index = self.realIndex(index);
				self.element.find('*').stop(true, true);
				if (options.select) {
					options.select.call(self, index, before);
				}
				self.currentIndex = index;
			};
			if (index !== self.currentIndex) {
				if (before) {
					var start = index < self.currentIndex ? self.currentIndex - 1 : self.currentIndex + self.count - 1;
					for (var i = start; i >= index; i--) {
						select(i, before);
					}
				} else {
					var start = index > self.currentIndex ? self.currentIndex + 1 : self.currentIndex - self.count + 1;
					for (var i = start; i <= index; i++) {
						select(i, before);
					}
				}
			}
		};
		if (this.init) {
			this.init();
		}
		this.play();
	};

	Broadcast.prototype.realIndex = function(index) {
		return index >= 0 ? index % this.count : this.count - Math.abs(index) % this.count;
	};

	Broadcast.prototype.beforeIndex = function(index) {
		var realIndex = this.realIndex(index);
		if (realIndex < this.currentIndex) {
			return realIndex;
		} else {
			return realIndex - this.count;
		}
	};

	Broadcast.prototype.afterIndex = function(index) {
		var realIndex = this.realIndex(index);
		if (realIndex > this.currentIndex) {
			return realIndex;
		} else {
			return realIndex + this.count;
		}
	};

	Broadcast.prototype.play = function() {
		var self = this;
		var poll = function() {
			if (!self.paused) {
				self.next();
			}
			setTimeout(poll, self.interval * 1000);
		};
		setTimeout(poll, self.interval * 1000);
	};

	Broadcast.prototype.pause = function() {
		this.paused = true;
	};

	Broadcast.prototype.goahead = function() {
		this.paused = false;
	};

	Broadcast.prototype.next = function() {
		this.select(this.currentIndex + 1, false);
	};

	Broadcast.prototype.previous = function() {
		this.select(this.currentIndex === 0 ? this.count - 1 : this.currentIndex - 1, true);
	};
})();
