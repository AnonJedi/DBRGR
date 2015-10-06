(function($) {
	$(document).ready(function(){
		$('input').keydown(function(event)	{
	        if (event.keyCode == 32) {
	        	return false;
	        } else if (event.keyCode == 13) {
	        	var $that = $(this);
	        	var sendData = {
	        		query: $that.attr('name'),
	        		str: $that.val()
	        	};

	        	$('select').empty();

	        	$.ajax({
	        		data: sendData,
	        		dataType: 'json',
	        		url: '/special',
	        		type: 'POST'
	        	}).done(function(data) {
	        		$that.removeClass('errorBorder');

	        		$(data).each(function() {
	        			var option = '<option>';
	        			if (sendData.query !== '3') {
	        				option += this.firstname + '  ' + this.lastname + '  ' + this.home;
	        			} else {
	        				option += this.home;
	        			}
	        			$('select').append(option);
	        		});
	        	}).fail(function(data) {
	        		$that.addClass('errorBorder');
	        	});
	        }
	    });
	});
})(jQuery);