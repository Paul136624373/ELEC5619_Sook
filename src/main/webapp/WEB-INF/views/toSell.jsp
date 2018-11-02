<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="robots" content="all,follow">
<meta name="googlebot" content="index,follow,snippet,archive">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Obaju Your Fashion Shop">
<meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
<meta name="keywords" content="">

<title><fmt:message key="title" /></title>

<link rel="icon" href="<c:url value='/img/logo.ico'/>"
	type="image/x-icon">
<link rel="shortcut icon" href="<c:url value='/img/logo.ico'/>"
	type="image/x-icon">
<meta name="keywords" content="">

<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100'
	rel='stylesheet' type='text/css'>

<!-- styles -->
<link href="<c:url value='/css/font-awesome.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/owl.theme.css'/>" rel="stylesheet">


<link rel="stylesheet" type="text/css"
	href="<c:url value='/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/vendor/animate/animate.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/vendor/select2/select2.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/vendor/perfect-scrollbar/perfect-scrollbar.css'/>">


<!-- theme stylesheet -->
<link href="<c:url value='/css/style.default.css'/>" rel="stylesheet"
	id="theme-stylesheet">

<!-- your stylesheet with modifications -->
<link href="<c:url value='/css/custom.css'/>" rel="stylesheet">

<script src="<c:url value='/js/respond.min.js'/>"></script>

<link rel="shortcut icon" href="<c:url value='/img/favicon.png'/>">

<style>
.inputfile {
	opacity: 0;
}
</style>
<script>
	function validateInput() {

		var price = document.getElementById("price");
		var name = document.getElementById("title");
		var city = document.getElementById("city");
		var picFile = document.getElementById("file");
		var desciption = document.getElementById("desciption");

		if (!/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/.test(price.value)) {
			alert("Please input a valid price!");
			return false;
		}
		if (name.value == "" || city.value == "" || picFile.value == ""
				|| desciption.value == "") {
			alert("Please provide all reqiured infomration!");
			return false;
		} else {
			return true;
		}

	}
</script>

</head>

<body>

	<!-- *** TOPBAR ***
 _________________________________________________________ -->
	<!-- *** TOPBAR *** -->
	<c:if test="${userName != null}">
		<div id="top">
			<div class="container">
				<div class="col-md-6 col-sm-5 col-sm-offset-6"
					data-animate="fadeInDown">
					<ul class="menu">
						<li><a href="<c:url value='/myInfo'/>">My Account</a></li>
						<li><a href="<c:url value='/myInfo/logout'/>">Log Out</a></li>
					</ul>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${userName == null}">
		<div id="top">
			<div class="container">
				<div class="col-md-6 col-sm-5 col-sm-offset-6"
					data-animate="fadeInDown">
					<ul class="menu">
						<li><a href="#" data-toggle="modal"
							data-target="#login-modal">Login</a></li>
						<li><a href="<c:url value='/register'/>"> Register</a></li>
					</ul>
				</div>
			</div>
			<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
				aria-labelledby="Login" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="Login">Customer login</h4>
						</div>
						<div class="modal-body">
							<form action="<c:url value='/register/Login/Box'/>" method="post"
								onsubmit="return validLoginInput()">
								<div class="form-group">
									<input type="text" class="form-control" id="emailLogin1"
										placeholder="email" name="email1">
								</div>
								<div class="form-group">
									<input type="password" class="form-control" id="passwordLogin1"
										placeholder="password" name="password1">
								</div>
								<p class="text-center">
									<button class="btn btn-primary">
										<i class="fa fa-sign-in"></i> Log in
									</button>
								</p>
							</form>
							<p class="text-center text-muted">Not registered yet?</p>
							<p class="text-center text-muted">
								<a href="<c:url value='/register'/>"><strong>Register
										now</strong></a>! It is easy and gives you a different book buying
								experience!
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<!-- *** TOP BAR END *** -->


	<!-- *** NAVBAR *** -->

	<div class="navbar navbar-default yamm" role="navigation" id="navbar">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand home" href="<c:url value='/home' />"
					data-animate-hover="bounce"> <img
					src="<c:url value='/img/Sook.png'/>" alt="Sook logo"
					class="hidden-xs"> <img
					src="<c:url value='/img/Sook_small.png'/>" alt="Sook logo"
					class="visible-xs"><span class="sr-only">Sook - go to
						homepage</span>
				</a>
				<div class="navbar-buttons">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navigation">
						<span class="sr-only">Toggle navigation</span> <i
							class="fa fa-align-justify"></i>
					</button>
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#search">
						<span class="sr-only">Toggle search</span> <i class="fa fa-search"></i>
					</button>
					<a class="btn btn-default navbar-toggle"
						href="<c:url value='/shoppingCart' />"> <i
						class="fa fa-shopping-cart"></i> <span class="hidden-xs">Shopping
							Cart</span>
					</a> <a class="btn btn-default navbar-toggle"
						href="<c:url value='/toSell' />"> <i class="fa fa-book"></i><span
						class="hidden-xs">I want to SELL</span>
					</a>
				</div>
			</div>
			<!--/.navbar-header -->

			<div class="navbar-collapse collapse" id="navigation">
				<ul class="nav navbar-nav navbar-left">
					<li class=""><a href="<c:url value='/home' />">Home</a></li>
					<li class="dropdown yamm-fw"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-delay="200">All Books <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<div class="yamm-content">
									<div class="row">
										<div class="col-sm-6">
											<ul>
												<li><a href="<c:url value='/viewProducts/Education' />">Education</a>
												</li>
												<li><a href="<c:url value='/viewProducts/Lifestyle' />">LifeStyle</a>
												</li>
												<li><a href="<c:url value='/viewProducts/Children' />">Children's
														Book</a></li>
												<li><a href="<c:url value='/viewProducts/Science' />">Science
														& Technology</a></li>
												<li><a
													href="<c:url value='/viewProducts/Literature' />">Literature</a>
												</li>
											</ul>
										</div>
										<div class="col-sm-6">
											<ul>
												<li><a href="<c:url value='/viewProducts/Politics' />">Politics,
														Philosophy & Social Science</a></li>
												<li><a href="<c:url value='/viewProducts/Fictions' />">Fictions</a>
												</li>
												<li><a href="<c:url value='/viewProducts/Comics' />">Comics</a>
												</li>
												<li><a
													href="<c:url value='/viewProducts/Businesses' />">Businesses
														& Economics</a></li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
					<li class=""><a href="<c:url value='/forum' />">FORUM</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
			<div class="navbar-buttons">
				<div class="navbar-collapse collapse right" id="basket-overview">
					<a href="<c:url value='/shoppingCart' />"
						class="btn btn-primary navbar-btn"><i
						class="fa fa-shopping-cart"></i><span class="hidden-sm">Shopping
							Cart</span></a>
				</div>
				<div class="navbar-collapse collapse right" id="sell-overview">
					<a href="<c:url value='/toSell' />"
						class="btn btn-primary navbar-btn"><i class="fa fa-book"></i><span
						class="hidden-sm">I want to sell</span></a>
				</div>
				<div class="navbar-collapse collapse right" id="search-not-mobile">
					<button style="height: 33px" type="button"
						class="btn navbar-btn btn-primary" data-toggle="collapse"
						data-target="#search">
						<span class="sr-only">Toggle search</span> <i class="fa fa-search"></i>
					</button>
				</div>
			</div>
			<div class="collapse clearfix" id="search">
				<form class="navbar-form" role="search" method="post"
					action="<c:url value='/viewProducts/search'/>">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search"
							name="search"> <span class="input-group-btn">
							<button type="submit" class="btn btn-primary"
								style="margin-left: 10px">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /#navbar -->
	<!-- *** NAVBAR END *** -->




	<!-- /#content -->
	<div>

		<div class="container">
			<div class="box">
				<a><h1>Sell your book</h1></a>
				<form action="<c:url value='/toSell/selling'/>" method="post"
					enctype="multipart/form-data" onsubmit="return validateInput()">
					<tr>
						<td>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="title">Title:</label> <input type="text"
											class="form-control" id="title" name="title">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-6">
										<label for="price">Price:</label> <input type="text"
											class="form-control" id="price" name="price">
									</div>

								</div>
							</div>
						</td>
					</tr>
					<tr>


						<td><div class="row">
								<div class="col-sm-4">
									<label for="means">Means of trasaction:</label> 
									<select class="form-control" name="mean">
										<option value="offline">offline</option>
										<option value="online">online</option>
									</select>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="category">Category:</label> 
											<select class="form-control" name="category">
											<option value="Education">Education</option>
											<option value="LifeStyle">LifeStyle</option>
											<option value="Children">Children's Book</option>
											<option value="Science">Science & Technology</option>
											<option value="Literature">Literature</option>
											<option value="Politics">Politics, Philosophy&Social
												Science</option>
											<option value="Fictions">Fictions</option>
											<option value="Comics">Comics</option>
											<option value="Businesses">Businesses & Economics</option>
										</select>

									</div>

								</div>
								
								<div class="col-sm-4">
										<div class="form-group">
											<label for="city">City:</label>
											<c:if test='${theUser.city == null || theUser.city == ""}'>
												<select class="form-control" id="city" name="city">
													<option value="" selected>-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Sydney"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney" selected>Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Albury"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury" selected>Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Armidale"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale" selected>Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Bathurst"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst" selected>Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Cessnock"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock" selected>Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Dubbo"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo" selected>Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Gosford"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford" selected>Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Newcastle"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle" selected>Newcastle</option>
													<option value="Wollongong">Wollongong</option>
												</select>
											</c:if>
											<c:if test='${theUser.city == "Wollongong"}'>
												<select class="form-control" id="city" name="city">
													<option value="">-- City --</option>
													<option value="Sydney">Sydney</option>
													<option value="Albury">Albury</option>
													<option value="Armidale">Armidale</option>
													<option value="Bathurst">Bathurst</option>
													<option value="Cessnock">Cessnock</option>
													<option value="Dubbo">Dubbo</option>
													<option value="Gosford">Gosford</option>
													<option value="Newcastle">Newcastle</option>
													<option value="Wollongong" selected>Wollongong</option>
												</select>
											</c:if>
										</div>
									</div>
							</div>
						</td>
					</tr>

					<tr>
						<td><div class="row">
								<div class="form-group">
									<div class="col-sm-6">
										<h5>Description:</h5>
										<textarea rows="10" cols="80" name="description"></textarea>
									</div>
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<input id="file" name="picFile" class="inputfile" onchange="changepic(this)" accept="image/png, image/jpg, image/jpeg" type="file">
										
										<label for="file" class="btn btn-primary">Choose a cover</label><br>
										
									</div>
									<div class="col-sm-3">
										<input id="file" name="picFile" class="inputfile" onchange="changepic(this)" accept="image/png, image/jpg, image/jpeg" type="file">

										<img src="" id="show" width="200">
										<script>
											function changepic() {
												var reads = new FileReader();
												f = document
														.getElementById('file').files[0];
												reads.readAsDataURL(f);
												reads.onload = function(e) {
													document
															.getElementById('show').src = this.result;
												};
											}
										</script>
									</div>
									
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td><br>
							<div class="row">
								<div class="col-sm-12 text-center">
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-check-circle"></i> Sell
									</button>
									<a href="<c:url value='/home'/>" class="btn btn-primary"><i class="fa fa-times-circle"></i> Cancel</a>
								</div>
							</div></td>
					</tr>

				</form>

				<!-- /.row -->


			</div>
			<!-- /#content end -->
		</div>
	</div>
	<!-- *** FOOTER *** -->
	<div id="footer" data-animate="fadeInUp">
		<div class="container">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-8">
					<h2>Top Categories</h2>
					<div class="col-sm-4">
						<ul>
							<li><a href="<c:url value='/viewProducts/Education' />">Education</a></li>
							<li><a href="<c:url value='/viewProducts/Lifestyle' />">LifeStyle</a></li>
							<li><a href="<c:url value='/viewProducts/Children' />">Children's
									Book</a></li>
							<li><a href="<c:url value='/viewProducts/Science' />">Science
									& Technology</a></li>
							<li><a href="<c:url value='/viewProducts/Literature' />">Literature</a></li>
						</ul>
					</div>
					<div class="col-md-4">
						<ul>
							<li><a href="<c:url value='/viewProducts/Politics' />">Politics,
									Philosophy & Social Science</a></li>
							<li><a href="<c:url value='/viewProducts/Fictions' />">Fictions</a>
							</li>
							<li><a href="<c:url value='/viewProducts/Comics' />">Comics</a>
							</li>
							<li><a href="<c:url value='/viewProducts/Businesses' />">Businesses
									& Economics</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-3">
					<h4>Stay in touch</h4>
					<p class="social">
						<a href="#" class="facebook external" data-animate-hover="shake"><i
							class="fa fa-facebook"></i></a> <a href="#" class="twitter external"
							data-animate-hover="shake"><i class="fa fa-twitter"></i></a> <a
							href="#" class="instagram external" data-animate-hover="shake"><i
							class="fa fa-instagram"></i></a> <a href="#" class="gplus external"
							data-animate-hover="shake"><i class="fa fa-google-plus"></i></a>
						<a href="#" class="email external" data-animate-hover="shake"><i
							class="fa fa-envelope"></i></a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- *** FOOTER END *** -->

	<!-- *** COPYRIGHT *** -->
	<div id="copyright">
		<div class="container">
			<div class="col-md-6">
				<p class="pull-left">© 2018 Sook Ltd.</p>

			</div>
			<div class="col-md-6">
				<p class="pull-right">
					Template by <a href=" ">Bootstrapious.com</a>
				</p>
			</div>
		</div>
	</div>
	<!-- *** COPYRIGHT END *** -->




	<!-- /#all -->




	<!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
	<script src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/js/jquery.cookie.js'/>"></script>
	<script src="<c:url value='/js/waypoints.min.js'/>"></script>
	<script src="<c:url value='/js/modernizr.js'/>"></script>
	<script src="<c:url value='/js/bootstrap-hover-dropdown.js'/>"></script>
	<script src="<c:url value='/js/owl.carousel.min.js'/>"></script>
	<script src="<c:url value='/js/front.js'/>"></script>

</body>

</html>
