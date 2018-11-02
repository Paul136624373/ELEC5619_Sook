<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    
    
	<link rel="stylesheet" type="text/css" href="<c:url value='/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/vendor/animate/animate.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/vendor/select2/select2.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/vendor/perfect-scrollbar/perfect-scrollbar.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/util.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>">
    

    <!-- theme stylesheet -->
    <link href="<c:url value='/css/style.default.css'/>" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="<c:url value='/css/custom.css'/>" rel="stylesheet">

    <script src="<c:url value='/js/respond.min.js'/>"></script>

    <link rel="shortcut icon" href="<c:url value='/img/favicon.png'/>">

	<script>
	
		function tempClick(){
			document.getElementById('imgPath').click();
		}
		
		function previewImage(file) {

			
		    if (file.files && file.files[0]) {
		        var img = document.getElementById('imgArea');
		        var reader = new FileReader();
		        reader.onload = function (evt) { 
		            img.src = evt.target.result;
		        }
		        reader.readAsDataURL(file.files[0]);
		      } else {
		          file.select();
		          var src = document.selection.createRange().text;
		          var img = document.getElementById('imgArea');
		          img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		      }
		}
		
		
		function validPost() {
			if(document.getElementById("title").value == "")
			{
				alert("The title of post cannot be empty.");
    			return false;
			}
			else if(document.getElementById("title").value.length > 30)
    		{
    			alert("Sorry, the title of post cannot be more than 30 characters.");
    			return false;
    		}
			else if(document.getElementById("postContent").value == "")
    		{
    			alert("The content of post cannot be empty.");
    			return false;
    		}
	    	else
	    	{
	    		return true;
	    	}
	    }
	</script>

	<style>
		select{
			font-size:15px;
			border: solid 1px #4fbfa8;    
			appearance: none;
			-moz-appearance: none;    
			-webkit-appearance: none;   
			padding-top: 5px;
			padding-bottom: 5px;
			padding-left: 20px;
			padding-right: 15px;
		}
		select::-ms-expand {    
			display: none;
		}
		
		textarea#postContent{
		    font-family: Tahoma, sans-serif;
		
		    width: 100%;
		    height: 300px;
		    padding: 5px;
		
		    border: 3px solid #4fbfa8;
		    background-repeat: no-repeat;
		    background-position: bottom right;
		    resize: none;
		}

	</style>

</head>

<body>
    <!-- *** TOPBAR *** -->
		<c:if test="${userName != null}">
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
		<c:if test="${userName == null}">
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

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="<c:url value='/home' />">Home</a></li>
                        <li><a href="<c:url value='/forum'/>">Forum</a>
                        </li>
                        <li>Create post</li>
                    </ul>
                    
                    <div class="table100 ver1 m-b-110">
						<div class="table100-head">
							<table>
								<thead>
									<tr class="row100 head">
										<th class="cell100 column1">Create a new post</th>
									</tr>
								</thead>
							</table>
						</div>
						
						<form action="createPost" method="post" enctype="multipart/form-data" onsubmit="return validPost()">
							<div class="table100-body js-pscroll">
								<table>
									<tbody>
										<tr class="row100 body">
											<td class="cell100" width="5%" style="padding-left: 20px"><font size="4" face="Verdana"><b>Title:</b></font></td>
											<td class="cell100" width="45%">
												<span>
													<input type="text" name="title" id="title" placeholder="Type the title here...">	
												</span>
											</td>
											<td class="cell100" width="10%"><font size="4" face="Verdana"><b>Category:</b></font></td>
											<td class="cell100" width="40%">
	
	
												<div class="button custom-select">
												  <select name="type">
												    <option value="Seek" selected="selected">Seek</option>
												    <option value="Share">Share</option>
												  </select>
												</div>
	
	
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div class="table100-body js-pscroll">
								<table>
									<tbody>
										<tr class="row100 body">
											<td class="cell100 column1"><font size="4" face="Verdana"><b>Content:</b></font></td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div style="padding-left: 20px; padding-right: 20px">
								<textarea name="postContent" id="postContent" placeholder="Enter the content here..."></textarea>
							</div>
	
							<div style="padding-top: 20px">
								<input type="file" id="imgPath" name="imgPath" accept="image/png, image/jpg, image/jpeg" style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 30px;" size="1" onchange="previewImage(this)">
							</div>
							
							<div class="js-pscroll">
								<table>
									<tbody>
										<tr class="row100 body">
											<td class="cell100" style="padding-left: 30px">
												<img id="imgArea" style="height:200px; cursor:pointer;" onclick="tempClick()" src="<c:url value='/img/insertImg.png' />"></img>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div class="js-pscroll">
								<table>
									<tbody>
										<tr>
											<td width="50%" style="padding-left: 20px">
												<a href="<c:url value='/forum'/>" class="btn btn-primary navbar-btn" style="float:left"><font size="3">
							                        <span class="hidden-sm">Cancel</span></font>
							                    </a>
											</td>
											<td width="50%" style="padding-right: 20px">
												<input type="submit" value="Create Post" class="btn btn-primary navbar-btn" style="float:right">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</form>
					</div>
                </div>
                
            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


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



    </div>
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
