$(function() {

	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#home').addClass('active');
		break;
	}
	//to tackle csrf token
	var token=$('meta[name="_csrf"]').attr('content');
	var header=$('meta[name="_csrf_header"]').attr('content');
	if(token.length > 0 && header.length > 0){
		//set the token for the ajax request
		$(document).ajaxSend(function(e,xhr,options) {
			xhr.setRequestHeader(header,token);
			
		});
	}
	// Code for Datatable

	var $table = $('#productListTable');
	// execute thw below code only we have this table
	if ($table.length) {
		// console.log('Inside the table!');
		var jsonURL = '';
		if (window.categoryId == '') {
			jsonURL = window.contextRoot + '/json/data/all/products';
		} else {
			jsonURL = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';

		}

		$table
				.DataTable({
					lengthMenu : [
							[ 3, 5, 10, -1 ],
							[ '3 records', '5 records', '10 records',
									'All records' ] ],
					pageLength : 5,
					// data:products
					ajax : {
						url : jsonURL,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableimg"/>';
								}
							},

							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product"  class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span>View</a> &#160;';
									if(userRole =='ADMIN'){
										str += '<a href="'
											+ window.contextRoot
											+ '/manages/'
											+ data
											+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-shopping-cart">Edit</span></a>';
									}
									else{
									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart">Add to Cart</span></a>';
									} else {
										
											
										
											str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart">Add to Cart</span></a>';
									}
										
									}

									return str;
								}

							} ]
				});
	}

	/*------*/
	/* for fading out the alert message after 3 seconds */
	$alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 6000);
	}
	// -------------------------------------------------

	/** * Data table for admin *** */

	var $adminProductsTable = $('#productsTable');
	// execute thw below code only we have this table
	if ($adminProductsTable.length) {
		 console.log('********************************************************************Inside the table!');
		var jsonURL = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 records', '30 records', '50 records', 'All' ] ],
					pageLength : 30,
					// data:products
					ajax : {
						url : jsonURL,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},

							{
								data : 'name'
							},
							{
								data : 'brand'
							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;
								}
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},
							{
								data : 'active',
								bsortable : false,
								mRender : function(data, type, row) {
									var str = '';

									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '" />';
									} else {
										str += '<input type="checkbox"  value="'
												+ row.id + '" />';
									}
									str += '<div class="slider"></div></label>';

									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'+window.contextRoot+'/manages/'
											+ data
											+ '/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span>Edit</a>&#160;';

									return str;
								}

							} ],
					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {
											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You want to activate madotha ee  the product?'
													: 'You want to deactivate asfhsthe Product?';
											var value = checkbox.prop('value');
											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation and De Activation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);
																var activationUrl = window.contextRoot
																		+ '/manages/product/'
																		+ value
																		+ '/activation';
																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																					.alert({
																						size : 'medium',
																						title : 'information',
																						message : data

																					});

																				});

																
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);

															}
														}

													});

										});

					}

				});
	}

})

// -----------------------------------------------------
//validation code for category
var $categoryForm = $('#categoryForm');

if($categoryForm.length){
	
	$categoryForm.validate({
		
		rules : {
			name : {
				required : true,
				minlength :2
				
			},
			description :{
			required :true
			}
		},
		messages :{
			name : {
				required : 'Please add the category name',
				minlength :'Category name should not be less than 2 characters'
			},
			description : {
				required : 'Please add description for this category!'
			}
			
		},
		errorElement: 'em',
		errorplacement : function(error,element){
			//add the class for help block
			error.addClass('help-block');
			error.insertAfter(element);
			
		}
	});
	
}
//----------------------------------
//validation code for Login
var $loginForm = $('#loginForm');

if($loginForm.length){
	
	$loginForm.validate({
		
		rules : {
			username : {
				required : true,
				email : true
				
			},
			password :{
			required :true
			}
		},
		messages :{
			username : {
				required : 'Please enter username',
				email :'Please enter valid email adress'
			},
			password : {
				required : 'Please enter the password!'
			}
			
		},
		errorElement: 'em',
		errorplacement : function(error,element){
			//add the class for help block
			error.addClass('help-block');
			error.insertAfter(element);
			
		}
	});
	
}
//----------------------------------
/* handle refresh cart*/	
$('button[name="refreshCart"]').click(function(){
	var cartLineId = $(this).attr('value');
	var countElement = $('#count_' + cartLineId);
	var originalCount = countElement.attr('value');
	var currentCount = countElement.val();
	// do the checking only the count has changed
	if(currentCount !== originalCount) {
		bootbox.alert("Hello world!");
		// check if the quantity is within the specified range
		if(countElement.val() < 1 || countElement.val() > 3) {
			console.log("value less than 1 and 3");
			// set the field back to the original field
			countElement.val(originalCount);
			alert("Product Count should be minimum 1 and maximum 3!");
			
			console.log("popup displayed`");
		}
	
		else {
			// use the window.location.href property to send the request to the server
			var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + countElement.val();
			window.location.href = updateUrl;
		}
	}
});	

//----------------------------------