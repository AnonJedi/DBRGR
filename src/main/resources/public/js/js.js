(function ($) {
	$(document).ready(function () {
		$('.tab').click(function() {
			var curentTab = $(this);
			
			$('.tab').each(function() {
				$(this)
					.removeClass('activeTab')
					.addClass('deactiveTab');
			});

			curentTab
				.removeClass('deactiveTab')
				.addClass('activeTab');
		});

		$('.dropDown').change(function () {
			var url = $(this).val();

			$.ajax({
				type: 'POST',
				url: '/' + url,
				dataType: 'json',
				data: {},
			success: function(data) {
			      var $select = $('.insertResult').empty();
			      var option;

			      $('input').each(function() {$(this).attr('type', 'hidden');});
			      $('.insertDropdown').each(function() {$(this).removeClass('displayInline').addClass('displayNone');});

			      if (url === 'department') {
			      	option = '<option>name</option>';
			      	$('input[name=name]').attr('type', 'textbox');
			      }
			      if (url === 'chair') {
			      	option = '<option>name  department</option>';
			      	$('input[name=name]').attr('type', 'textbox');
			      	$('select[name=department]').removeClass('displayNone').addClass('displayInline');	
			      }
			      if (url === 'class') {
			      	option = '<option>name  department</option>';
			      	$('input[name=name]').attr('type', 'textbox');
			      	$('select[name=department]').removeClass('displayNone').addClass('displayInline');
			      }
			      if (url === 'student') {
			      	option = '<option>firstname  lastname  isHead  class  chair</option>';
			  	  	$('input[name=firstname]').attr('type', 'textbox');
			  	  	$('input[name=lastname]').attr('type', 'textbox');
			  	  	$('select[name=isHead]').removeClass('displayNone').addClass('displayInline');
			  	  	$('select[name=chair]').removeClass('displayNone').addClass('displayInline');
			  	  	$('select[name=class]').removeClass('displayNone').addClass('displayInline');
			  	  }
			      if (url === 'course') {
			      	option = '<option>name  lecturer</option>';
			      	$('input[name=name]').attr('type', 'textbox');
			      	$('select[name=lecturer]').removeClass('displayNone').addClass('displayInline');
			      }
			      if (url === 'lecturer') {
			      	option = '<option>firstname  lastname  chair</option>';
			      	$('input[name=firstname]').attr('type', 'textbox');
			      	$('input[name=lastname]').attr('type', 'textbox');
			      	$('select[name=chair]').removeClass('displayNone').addClass('displayInline');
			      }
			      if (url === 'student_course') {
			      	option = '<option>name  lastname</option>';
			      	$('input[name=lastname]').attr('type', 'textbox');
			      	$('input[name=name]').attr('type', 'textbox');
			      }

			      $select.append(option);

			      $(data).each(function () {
			      	if (url === 'department') {
			      	  option = '<option>' + this.name + '</option>';
			      	  $select.append(option);	
			      	}
			      	
			      	if (url === 'chair') {
			      	  option = '<option>' + this.name + '  ' + this.departmentName + '</option>';
			      	  $select.append(option);	
			      	}

			      	if (url === 'class') {
			      		option = '<option>' + this.name + '  ' + this.departmentName + '</option>';
			      		$select.append(option);
			      	}

			      	if (url === 'student') {
			      		option = '<option>' + this.firstname + '  ' + this.lastname + '  ' + this.head + '  ' + this.className + '  ' + this.chairName + '</option>';
			      		$select.append(option);
			      	}

			      	if (url === 'course') {
			      		option = '<option>' + this.name + '  ' + this.lecturerName + '</option>';
			      		$select.append(option);
			      	}

			      	if (url === 'lecturer') {
			      		option = '<option>' + this.firstname + '  ' + this.lastname + '  ' + this.chairName + '</option>';
			      		$select.append(option);
			      	}

			      	if (url === 'student_course') {
			      		option = '<option>' + this.name + '  ' + this.lastname + '</option>';
			      		$select.append(option);
			      	}

			      });
				}
			});
		});
		

		$('.actionBtn').click(function() {
			
			var table = $('.dropDown').val();
			var sendData;
			var selected;
			var option;

			if ($(this).text() === 'Insert') {
				sendData = {
					table: $('.dropDown').val(),
					name: $('input[name=name]').val(),
					firstname: $('input[name=firstname]').val(),
					lastname: $('input[name=lastname]').val(),
					isHead: $('select[name=isHead]').hasClass('displayInline') ? $('select[name=isHead').val() : 0,
					department: $('select[name=department]').hasClass('displayInline') ? $('select[name=department]').val() : 0,
					chair: $('select[name=chair]').hasClass('displayInline') ? $('select[name=chair]').val() : 0,
					lecturer: $('select[name=lecturer]').hasClass('displayInline') ? $('select[name=lecturer]').val() : 0,
					clas: $('select[name=class]').hasClass('displayInline') ? $('select[name=class]').val() : 0,
					student: $('select[name=student]').hasClass('displayInline') ? $('select[name=student]').val() : 0,
					course: $('select[name=course]').hasClass('displayInline') ? $('select[name=course]').val() : 0
				};

				$.ajax({
					type: 'POST',
					dataType: 'json',
					data: sendData,
					url: '/insert',
					success: function(data) {
						if (data) {
							option = '<option>';
						    $('input[type="textbox"]').each(function() {
							    option += $(this).val() + '  ';
							    $(this).val('');
						    });
						    $('.insertDropdown').each(function() {
							    option += $(this).hasClass('displayInline') ? $(this).val() + '  ' : '';
						    });

						    $('.insertResult').append(option);
						    $('.actionBtn').removeClass('error');
						} else {
							$('.actionBtn').addClass('error');
						}
						
					},
					error: function() {
						$('.actionBtn').addClass('error');
					}
					
				});
			} else if ($(this).text() === 'Delete') {
				selected = String($('.insertResult').val()).split('  ');

				if (selected[0] !== 'name' && selected[0] !== 'firstname') {

					if (table === 'department') {
						sendData = {
							table: table,
							name: selected[0]
						};
					}
					if (table === 'chair') {
						sendData = {
							table: table,
							name: selected[0],
							department: selected[1]
						};
					}
					if (table === 'lecturer') {
						sendData = {
							table: table,
							firstname: selected[0],
							lastname: selected[1],
							chair: selected[2]
						};
					}
					if (table === 'class') {
						sendData = {
							table: table,
							name: selected[0],
							department: selected[1]
						};
					}
					if (table === 'student') {
						sendData = {
							table: table,
							firstname: selected[0],
							lastname: selected[1],
							clas: selected[3],
							chair: selected[4]
						};
					}
					if (table === 'course') {
						sendData = {
							table: table,
							name: selected[0],
							firstname: selected[1]
						};
					}
					if (table === 'student_course') {
						sendData = {
							table: table,
							name: selected[0],
							lastname: selected[1]
						};
					}

					$.ajax({
						data: sendData,
						dataType: 'json',
						url: '/delete',
						type: 'POST',
						success: function() {
							$('.insertResult option').each(function() {
								if (String($(this).val()) ===
									String($('.insertResult').val())) {
									$(this).remove();
								}
							});
							$('.actionBtn').removeClass('error');
						},
						error: function() {
							$('.actionBtn').addClass('error');
						}
					});

				} else {
					return null;
				}
			} else if ($(this).text() === 'Update') {

				selected = String($('.insertResult').val()).split('  ');
				sendData = {
					table: table,
					name: '',
					firstname: '',
					lastname: '',
					isHead: '',
					department: '',
					chair: '',
					clas: '',
					oldName: '',
					oldFirstname: '',
					oldIsHead: '',
					oldDepartment: '',
					oldChair: '',
					oldClas: ''
				};

				if (selected[0] !== 'name' && selected[0] !== 'firstname') {

					if (table === 'department') {
						sendData.name = $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val();
						sendData.oldName = selected[0];
					}
					if (table === 'chair') {
						sendData.name = $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val();
						sendData.department = $('select[name="department"]').val();
						sendData.oldName = selected[0];
						sendData.oldDepartment = selected[1];
					}
					if (table === 'lecturer') {
						sendData.firstname = $('input[name="firstname"]').val() === '' ? selected[0] : $('input[name="firstname"]').val();
						sendData.lastname = $('input[name="lastname"]').val() === '' ? selected[1] : $('input[name="lastname"]').val();
						sendData.chair = $('select[name="chair"]').val();
						sendData.oldFirstname = selected[0];
						sendData.oldLastname = selected[1];
						sendData.oldChair = selected[2];
					}
					if (table === 'class') {
						sendData.name = $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val();
						sendData.department = $('select[name="department"]').val();
						sendData.oldName = selected[0];
						sendData.oldDepartment = selected[1];
					}
					if (table === 'student') {
						sendData.firstname = $('input[name="firstname"]').val() === '' ? selected[0] : $('input[name="firstname"]').val();
						sendData.lastname = $('input[name="lastname"]').val() === '' ? selected[1] : $('input[name="lastname"]').val();
						sendData.isHead = $('select[name="isHead"]').val();
						sendData.clas = $('select[name="class"]').val();
						sendData.chair = $('select[name="chair"]').val();
						sendData.oldFirstname = selected[0];
						sendData.oldLastname = selected[1];
						sendData.oldIsHead = selected[2];
						sendData.oldClas = selected[3];
						sendData.oldChair = selected[4];
					}
					if (table === 'course') {
						sendData.name = $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val();
						sendData.lastname = $('select[name="lecturer"]').val();
						sendData.oldName = selected[0];
						sendData.oldLastname = selected[1];
					}
					if (table === 'student_course') {					
						sendData.name = $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val();
						sendData.lastname = $('input[name="lastname"]').val() === '' ? selected[1] : $('input[name="lastname"]').val();
						sendData.oldName = selected[0];
						sendData.oldLastname = selected[1];
					}

					$.ajax({
						type: 'POST',
						data: sendData,
						dataType: 'json',
						url: '/update',
						success: function(data) {
							if (data) {
								location.reload();
							} else {
								$('.actionBtn').addClass('error');
							}
							
						},
						error: function() {
							$('.actionBtn').addClass('error');
						}
					});
				}
			}
		});


		$('.tab').click(function() {
			$('.actionBtn').text($(this).attr('name'));
		});

		$('input').keydown(function(event)	{
	        if (event.keyCode == 32) {
	        	return false;
	        }
	    });
	});
})(jQuery);