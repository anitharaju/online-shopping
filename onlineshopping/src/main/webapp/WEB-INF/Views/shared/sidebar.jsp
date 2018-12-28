

<h1 class="my-4">Shop Name Arun</h1>


<div class="list-group">
	 <c:forEach items="${categories}" var="category">
		 <a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item">${category.name}</a>
		
	</c:forEach> 
	 <%-- <a href="${contextroot}/onlineshopping/show/category/1/products" class="list-group-item">TV</a> --%>

</div> 
