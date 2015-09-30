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
			var temp;
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
					statusCode: {
						200: function() {
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
						},
						400: function() {
							$('.actionBtn').addClass('error');
						}
					} 
				});
			} else if ($(this).text() === 'Delete') {
				temp = String($('.insertResult').val()).split(" ");
				selected = [];

				$(temp).each(function() {
					if (String(this) !== "") {
						selected.push(String(this));
					}
				});

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
							departmentId: selected[1]
						};
					}
					if (table === 'lecturer') {
						sendData = {
							table: table,
							firstname: selected[0],
							lastname: selected[1],
							chairId: selected[2]
						};
					}
					if (table === 'class') {
						sendData = {
							table: table,
							name: selected[0],
							departmentId: selected[1]
						};
					}
					if (table === 'student') {
						sendData = {
							table: table,
							firstname: selected[0],
							lastname: selected[1],
							classId: selected[3],
							chairId: selected[4]
						};
					}
					if (table === 'course') {
						sendData = {
							table: table,
							name: selected[0],
							lecturerId: selected[1]
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
						statusCode: {
							200: function() {
								$('.insertResult option').each(function() {
									if (String($(this).val()) ===
										String($('.insertResult').val())) {
										$(this).remove();
									}
								});
								$('.actionBtn').removeClass('error');
							},
							400: function() {
								$('.actionBtn').addClass('error');
							}
						}
					});

				} else {
					return null;
				}
			} else if ($(this).text() === 'Update') {

				temp = String($('.insertResult').val()).split(" ");
				selected = [];

				$(temp).each(function() {
					if (String(this) !== "") {
						selected.push(String(this));
					}
				});

				if (selected[0] !== 'name' && selected[0] !== 'firstname') {

					if (table === 'department') {
						sendData = {
							table: table,
							name: $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val(),
							oldName: selected[0]
						};
					}
					if (table === 'chair') {
						sendData = {
							table: table,
							name: $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val(),
							departmentId: $('input[name="departmentId"]').val() === '' ? selected[1] : $('input[name="departmentId"]').val(),
							oldName: selected[0],
							oldDepartmentId: selected[1]
						};
					}
					if (table === 'lecturer') {
						sendData = {
							table: table,
							firstname: $('input[name="firstname"]').val() === '' ? selected[0] : $('input[name="firstname"]').val(),
							lastname: $('input[name="lastname"]').val() === '' ? selected[1] : $('input[name="lastname"]').val(),
							chairId: $('input[name="chairId"]').val() === '' ? selected[2] : $('input[name="chairId"]').val(),
							oldFirstname: selected[0],
							oldLastname: selected[1],
							oldChairId: selected[2]
						};
					}
					if (table === 'class') {
						sendData = {
							table: table,
							name: $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val(),
							departmentId: $('input[name="departmentId"]').val() === '' ? selected[1] : $('input[name="departmentId"]').val(),
							oldName: selected[0],
							oldDepartmentId: selected[1]
						};
					}
					if (table === 'student') {
						sendData = {
							table: table,
							firstname: $('input[name="firstname"]').val() === '' ? selected[0] : $('input[name="firstname"]').val(),
							lastname: $('input[name="lastname"]').val() === '' ? selected[1] : $('input[name="lastname"]').val(),
							isHead: $('input[name="isHead"]').val() === '' ? selected[2] : $('input[name="isHead"]').val(),
							classId: $('input[name="classId"]').val() === '' ? selected[3] : $('input[name="classId"]').val(),
							chairId: $('input[name="chairId"]').val() === '' ? selected[4] : $('input[name="chairId"]').val(),
							oldFirstname: selected[0],
							oldLastname: selected[1],
							oldIsHead: selected[2],
							oldClassId: selected[3],
							oldChairId: selected[4]
						};
					}
					if (table === 'course') {
						sendData = {
							table: table,
							name: $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val(),
							lecturerId: $('input[name="lecturerId"]').val() === '' ? selected[1] : $('input[name="lecturerId"]').val(),
							oldName: selected[0],
							oldLecturerId: selected[1]
						};
					}
					if (table === 'student_course') {
						sendData = {
							table: table,
							name: $('input[name="name"]').val() === '' ? selected[0] : $('input[name="name"]').val(),
							lastname: $('input[name="lastname"]').val() === '' ? selected[1] : $('input[name="lastname"]').val(),
							oldName: selected[0],
							oldLastname: selected[1]
						};
					}

					$.ajax({
						type: 'POST',
						data: sendData,
						dataType: 'json',
						url: '/update',
						statusCode: {
							200: function() {
								location.reload();
							},
							400: function() {
								$('.actionBtn').addClass('error');
							}
						}
					});
				}
			}
		});


		$('.tab').click(function() {
			$('.actionBtn').text($(this).attr('name'));
		});
	});
})(jQuery);