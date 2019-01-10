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
	default:
		if (menu == "Home")
			break;
		$('#home').addClass('active');
		break;
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
									mRender:function(data,type,row){
										return '<img src="'+window.contextRoot + '/resources/images/'+ data +'.jpg" class="dataTableimg"/>';
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
								data : 'quantity'
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

									str += '<a href="'
											+ window.contextRoot
											+ '/cart/add/'
											+ data
											+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</a>';
									return str;
								}
							} ]
				});
	}

});