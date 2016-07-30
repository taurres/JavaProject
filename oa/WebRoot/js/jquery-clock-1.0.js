(function($){
	$.fn.extend({
		getClock : function(){
			var clock = "今天是 ";
			var date = new Date();
			clock += date.getFullYear().toString() + "年";
			clock += $.twoDigits((date.getMonth() + 1).toString()) + "月";
			clock += $.twoDigits(date.getDate().toString()) + "日 ";
			clock += $.days[date.getDay()]+" ";
			clock += $.twoDigits(date.getHours().toString()) + ":";
			clock += $.twoDigits(date.getMinutes().toString()) + ":";
			clock += $.twoDigits(date.getSeconds().toString());
			
			this.html(clock);
			//t指向jquery对象，实现setTimeout方法中获取jquery对象，实现getClock方法的调用
			var t = this;
			//每隔1秒调用一次，更新时间
			window.setTimeout(function() {
				t.getClock();
			}, 1000);
		}
	});
	
	
	$.extend({
		twoDigits: function(num){
			return num > 9 ? num : "0" + num;
		},
		days: ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]
	});
})(jQuery)