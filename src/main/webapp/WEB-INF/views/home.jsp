<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Obaju Your Fashion Shop">
    <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
    <meta name="keywords" content="">

    <title>
        <fmt:message key="title" />
    </title>

	<link rel="icon" href="<c:url value='/img/logo.ico'/>" type="image/x-icon">
    <link rel="shortcut icon" href="<c:url value='/img/logo.ico'/>" type="image/x-icon">
    <meta name="keywords" content="">

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="<c:url value='/css/font-awesome.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/owl.theme.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/owl.carousel.css'/>" rel="stylesheet">
    
    
    
<%-- 	<link rel="stylesheet" type="text/css" href="<c:url value='/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<c:url value='/css/vendor/animate/animate.css'/>"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<c:url value='/css/vendor/select2/select2.min.css'/>"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<c:url value='/css/vendor/perfect-scrollbar/perfect-scrollbar.css'/>"> --%>
    

    <!-- theme stylesheet -->
    <link href="<c:url value='/css/style.default.css'/>" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="<c:url value='/css/custom.css'/>" rel="stylesheet">

    <script src="<c:url value='/js/respond.min.js'/>"></script>

    <link rel="shortcut icon" href="<c:url value='/img/favicon.png'/>">

	<style type="text/css">
            *{padding: 0;margin: 0;
            }
            .banner {
                width: 100%;
                min-width: 1200px;
                height: 488px;
                overflow: hidden;
            }

            .banner_index {
                position: relative;
            }

            .banner_slide_1 {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                display: block;
                transition: opacity 0.5s;
                opacity: 1;
                z-index: 5;
                background: no-repeat;
                background-position: center 0;
            }

            .banner_slide_2 {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                display: none;
                transition: opacity 0.5s;
                opacity: 0;
                z-index: 1;
                background: no-repeat;
                background-position: center 0;
            }

            .banner_slide_3 {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                display: none;
                transition: opacity 0.5s;
                opacity: 0;
                z-index: 1;
                background: no-repeat;
                background-position: center 0;
            }
        </style>
        <script type="text/javascript">
            window.onload=function bannerSlide() {
                if (!document.getElementById) return false;
                if (!document.getElementsByTagName) return false;
                if (!document.getElementById("bannerBox")) return false;
                var i = 0;
                var box = document.getElementById("bannerBox");
                var banner = box.getElementsByTagName("div");
                sli = setInterval(slide, 3000); 
                function slide() {
                    if (i == banner.length - 1) {
                        banner[0].style.display = "block";
                        banner[0].style.opacity = "1";
                        banner[i].style.opacity = "0";
                        setTimeout(function() {
                                banner[i].style.display = "none";
                                banner[i].style.zIndex = "1";
                                banner[0].style.zIndex = "5";
                                i = 0;
                            }, 500) 
                    } else {
                        banner[i + 1].style.display = "block";
                        banner[i + 1].style.opacity = "1";
                        banner[i].style.opacity = "0";
                        setTimeout(function() {
                            banner[i + 1].style.zIndex = "5";
                            banner[i].style.display = "none";
                            banner[i].style.zIndex = "1";
                            i++;
                        }, 500)
                    }
                }
            }
	</script>



</head>

<body>

	<!-- *** TOPBAR *** -->
		<c:if test="${ToHomeModel.userName != null}">  
			<div id="top">
				<div class="container">
					<div class="col-md-6 col-sm-5 col-sm-offset-6" data-animate="fadeInDown">
						<ul class="menu">
							<li><a href="<c:url value='/myInfo'/>">My Account</a></li>
							<li><a href="<c:url value='/myInfo/logout'/>" >Log Out</a></li>                   
						</ul>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${ToHomeModel.userName == null}">  
			<div id="top">
				<div class="container">
					<div class="col-md-6 col-sm-5 col-sm-offset-6" data-animate="fadeInDown">
						<ul class="menu">
							<li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a></li>
							<li><a href="<c:url value='/register'/>" > Register</a></li>
						</ul>
					</div>
				</div>
				<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="Login">Customer login</h4>
							</div>
							<div class="modal-body">
								<form action="<c:url value='/register/Login/Box'/>" method="post" onsubmit="return validLoginInput()">
									<div class="form-group">
										<input type="text" class="form-control" id="emailLogin1" placeholder="email" name="email1">
									</div>
									<div class="form-group">
										<input type="password" class="form-control" id="passwordLogin1" placeholder="password" name="password1">
									</div>
									<p class="text-center">
										<button class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
									</p>
								</form>
								<p class="text-center text-muted">Not registered yet?</p>
								<p class="text-center text-muted"><a href="<c:url value='/register'/>"><strong>Register now</strong></a>! It is easy and gives you a different book buying experience!</p>
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
				<a class="navbar-brand home" href="<c:url value='/home' />" data-animate-hover="bounce">
                    <img src="<c:url value='/img/Sook.png'/>" alt="Sook logo" class="hidden-xs">
                    <img src="<c:url value='/img/Sook_small.png'/>" alt="Sook logo" class="visible-xs"><span class="sr-only">Sook - go to homepage</span>
                </a>
                <div class="navbar-buttons">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-align-justify"></i>
                    </button>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                    <a class="btn btn-default navbar-toggle" href="<c:url value='/shoppingCart' />">
                        <i class="fa fa-shopping-cart"></i>  <span class="hidden-xs">Shopping Cart</span>
                    </a>
                    <a class="btn btn-default navbar-toggle" href="<c:url value='/toSell' />">
                        <i class="fa fa-book"></i><span class="hidden-xs">I want to SELL</span>
                    </a>
                </div>
            </div>
            <!--/.navbar-header -->

            <div class="navbar-collapse collapse" id="navigation">
                <ul class="nav navbar-nav navbar-left">
                    <li class=""><a href="<c:url value='/home' />">Home</a></li>														
					<li class="dropdown yamm-fw">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">All Books <b class="caret"></b></a>
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
                                                <li><a href="<c:url value='/viewProducts/Children' />">Children's Book</a>
                                                </li>
                                                <li><a href="<c:url value='/viewProducts/Science' />">Science & Technology</a>
                                                </li>
                                                <li><a href="<c:url value='/viewProducts/Literature' />">Literature</a>
                                                </li>             
                                            </ul>
                                        </div>
                                        <div class="col-sm-6">
                                            <ul>
                                                <li><a href="<c:url value='/viewProducts/Politics' />">Politics, Philosophy & Social Science</a>
                                                </li>
                                                <li><a href="<c:url value='/viewProducts/Fictions' />">Fictions</a>
                                                </li>
                                                <li><a href="<c:url value='/viewProducts/Comics' />">Comics</a>
                                                </li>
                                                <li><a href="<c:url value='/viewProducts/Businesses' />">Businesses & Economics</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class=""><a href="<c:url value='/forum' />">FORUM</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
            <div class="navbar-buttons">
                <div class="navbar-collapse collapse right" id="basket-overview">
                    <a href="<c:url value='/shoppingCart' />" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm">Shopping Cart</span></a>
                </div>
                <div class="navbar-collapse collapse right" id="sell-overview">
                    <a href="<c:url value='/toSell' />" class="btn btn-primary navbar-btn"><i class="fa fa-book"></i><span class="hidden-sm">I want to sell</span></a>
                </div>
                <div class="navbar-collapse collapse right" id="search-not-mobile">
                    <button style="height:33px" type="button" class="btn navbar-btn btn-primary" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
            <div class="collapse clearfix" id="search">
                <form class="navbar-form" role="search" method="post"  action="<c:url value='/viewProducts/search'/>">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="search">
                        <span class="input-group-btn">
			                <button type="submit" class="btn btn-primary" style="margin-left:10px"><i class="fa fa-search"></i></button>
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

	<div class="box">
	
		<div class="container">
           <div class="col-md-12">
				<div id="main-slider">
                   <div class="item">
                       <img src="<c:url value='/img/background1.jpg' />" alt="" class="img-responsive">
                   </div>
                   <div class="item">
                       <img class="img-responsive" src="<c:url value='/img/background2.jpg'/>" alt="">
                   </div>
                   <div class="item">
                       <img class="img-responsive" src="<c:url value='/img/background3.jpg'/>" alt="">
                   </div>
                   <div class="item">
                       <img class="img-responsive" src="<c:url value='/img/background4.jpg'/>" alt="">
                   </div>
               </div>
           </div>
       </div>
		
		

		<div align="center">

			<div>
				<div id="hot">

					<div class="box" align="center">
						<div class="container" align="center">
							<div class="col-md-12">
								<a><h1>New Arrival</h1></a>
							</div>
						</div>
					</div>

					<div class="container">
						<!--                     <div class="product-slider"> -->
						<!--                         <div class="item"> -->
						<!--                             <div class="product"> -->
						<!--                                 <div class="flip-container"> -->
						<!--                                     <div class="flipper"> -->
						<!--                                         <div class="front"> -->
						<!--                                             <a href="detail.html"> -->
						<%--                                                 <img src="<c:url value='/img/Sook.png'/>" alt="" class="img-responsive"> --%>
						<!--                                             </a> -->
						<!--                                         </div> -->
						<!--                                         <div class="back"> -->
						<!--                                             <a href="detail.html"> -->
						<!--                                                 <img src="img/product1_2.jpg" alt="" class="img-responsive"> -->
						<!--                                             </a> -->
						<!--                                         </div> -->
						<!--                                     </div> -->
						<!--                                 </div> -->
						<!--                                 <a href="detail.html" class="invisible"> -->
						<!--                                     <img src="img/product1.jpg" alt="" class="img-responsive"> -->
						<!--                                 </a> -->
						<!--                                 <div class="text"> -->
						<!--                                     <h3><a href="detail.html">The Little Prince</a></h3> -->
						<!--                                     <p class="price">$100.00</p> -->
						<!--                                     <a href="addtocart.jsp" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm">Add to cart</span></a> -->
						<!--                                 </div> -->
						<!--                                 /.text -->
						<!--                             </div> -->
						<!--                             /.product -->
						<!--                         </div> -->


						<!--                     /.product-slider -->
						<div class="row">
							<!--unit product-->
							<c:forEach items="${ToHomeModel.newArrival}" var="oneArrival">
								<div class="col-sm-3">
									<img style="width: 400px; height: 300px; overflow: hidden" src="<c:url value='/product_pic/${oneArrival.picture}'/>"
										alt="" class="img-responsive" height="300px">
									<div class="text">
										<h3>
											<a href="<c:url value='/detail/${oneArrival.productID}'/>"><c:out
													value="${oneArrival.name}" /></a>
										</h3>
										<p class="price">
											$
											<c:out value="${oneArrival.price}" />
										</p>
										<a
											href="<c:url value='/viewProducts/seek/${oneArrival.productID}'/>"
											class="btn btn-primary navbar-btn"><i
											class="fa fa-shopping-cart"></i><span class="hidden-sm">Add
												to cart</span></a>
									</div>
								</div>
							</c:forEach>
							<!--unit product end-->
						</div>
					</div>
					<!-- /.container -->

				</div>
			</div>
		</div>
	</div>

	<!-- /#content end-->

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
								<li><a href="<c:url value='/viewProducts/Children' />">Children's Book</a></li>
								<li><a href="<c:url value='/viewProducts/Science' />">Science & Technology</a></li>
								<li><a href="<c:url value='/viewProducts/Literature' />">Literature</a></li>
	                       </ul>
                    	</div>
						<div class="col-md-4">
							<ul>
							    <li><a href="<c:url value='/viewProducts/Politics' />">Politics, Philosophy & Social Science</a>
							    </li>
							    <li><a href="<c:url value='/viewProducts/Fictions' />">Fictions</a>
							    </li>
							    <li><a href="<c:url value='/viewProducts/Comics' />">Comics</a>
							    </li>
							    <li><a href="<c:url value='/viewProducts/Businesses' />">Businesses & Economics</a>
							    </li>
							</ul>
                    	</div>
                    </div>
                    <div class="col-md-3">
                        <h4>Stay in touch</h4>
                        <p class="social">
                            <a href="#" class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>
                            <a href="#" class="twitter external" data-animate-hover="shake"><i class="fa fa-twitter"></i></a>
                            <a href="#" class="instagram external" data-animate-hover="shake"><i class="fa fa-instagram"></i></a>
                            <a href="#" class="gplus external" data-animate-hover="shake"><i class="fa fa-google-plus"></i></a>
                            <a href="#" class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
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
                    <p class="pull-left">© 2018 Sook Ltd.</p >

                </div>
                <div class="col-md-6">
                    <p class="pull-right">Template by <a href=" ">Bootstrapious.com</a >
                    </p >
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
