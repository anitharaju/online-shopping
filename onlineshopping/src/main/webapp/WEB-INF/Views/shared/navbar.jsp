<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>



<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="#">Online Shopping</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active" id="home"><a class="nav-link"
				href="${contextRoot}/home">Home <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item" id="about"><a class="nav-link"
				href="${contextRoot}/about">About Us</a></li>
			<li class="nav-item" id="listproducts"><a class="nav-link"
				href="${contextRoot}/show/all/products">View Products</a></li>
			<li class="nav-item" id="contact"><a class="nav-link"
				href="${contextRoot}/contact">Contact Us</a></li>
				<security:authorize access="hasAuthority('ADMIN')">
			<li class="nav-item" id="manageProducts"><a class="nav-link"
				href="${contextRoot}/manages/products">Manage Products</a></li>
				</security:authorize>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<security:authorize access="isAnonymous()">
				<li class="nav-item" id="register"><a class="nav-link"
					href="${contextRoot}/register">Sign Up</a></li>
				<li class="nav-item" id="login"><a class="nav-link"
					href="${contextRoot}/login">Login</a></li>
				<li class="nav-item" id="login">
					<h4>Arun</h4>
				</li>
			</security:authorize>

			<security:authorize access="isAuthenticated()">

				<li class="dropdown"><a class="btn btn-default dropdown-toggle"
					href="javascript:void(0)" id="dropdownMenu1" data-toggle="dropdown">
						${userModel.fullName} <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<security:authorize access="hasAuthority('USER')">
							<li id="cart"><a href="${contextRoot}/cart/show"> <span
									class="glyphicon glyphicon-shopping-cart"></span> <span
									class="badge">${userModel.cart.cartLines}</span> - &#8377;
									${userModel.cart.grandTotal}
							</a></li>
							<li role="separator" class="divider"></li>
						</security:authorize>
						<li><a href="${contextRoot}/perform-logout">Logout</a></li>
					</ul></li>
			</security:authorize>
		</ul>

		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="text" placeholder="Search">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
</nav>

<script>

window.userRole = '${userModel.role}';


</script>