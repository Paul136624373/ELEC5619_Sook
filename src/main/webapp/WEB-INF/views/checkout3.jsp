<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <link href="<c:url value='/css/font-awesome.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/owl.carousel.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/owl.theme.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/style.default.css'/>" rel="stylesheet" id="theme-stylesheet">
    <link href="<c:url value='/css/custom.css'/>" rel="stylesheet">
    <script src="<c:url value='/js/respond.min.js'/>"></script>
    <link rel="shortcut icon" href="favicon.png">
    
    <script>
		function validCard() {
			
			var radio = document.getElementsByName("payment");
			var paymentType;
			var count = 0;
			for (i=0; i<radio.length; i++) {
				if (radio[i].checked) {
					paymentType = radio[i].value;
				}
			}

			if(paymentType == "cash")
			{
				return true;
			}
				
			if(paymentType == "card")
			{
				if(document.getElementById("cardname").value == "")
	    		{
	    			alert("Name on card cannot be empty.");
	    			return false;
	    		}
				else
				{
					var reg = new RegExp("^[ A-Za-z]*$");
					if(!reg.test(document.getElementById("cardname").value))
					{
						alert("Please write the correct format of name on card.");
						return false;
					}
					else
						count ++;
				}
				
				if(document.getElementById("cardnumber").value == "")
	    		{
	    			alert("Card Number cannot be empty.");
	    			return false;
	    		}
				else
				{
					var reg = new RegExp("^[0-9]{16}$");
					if(!reg.test(document.getElementById("cardnumber").value))
					{
						alert("Please write the correct format of card number.");
						return false;
					}
					else
						count ++;
				}
				
				if(document.getElementById("expirymonth").value == "")
	    		{
	    			alert("Expiry month cannot be empty.");
	    			return false;
	    		}
				
				if(document.getElementById("expiryyear").value == "")
	    		{
	    			alert("Expiry year cannot be empty.");
	    			return false;
	    		}
				
				if(document.getElementById("expirymonth").value != "" && document.getElementById("expiryyear").value != "")
				{
					var regMonth = new RegExp("^[0-9]{1,2}$");
					if(!regMonth.test(document.getElementById("expirymonth").value))
					{
						alert("Please write the correct format of expiry month.");
						return false;
					}
					var regYear = new RegExp("^[0-9]{4}$");
					if(!regYear.test(document.getElementById("expiryyear").value))
					{
						alert("Please write the correct format of expiry year.");
						return false;
					}
					if(document.getElementById("expiryyear").value == "2018")
					{
						if(document.getElementById("expirymonth").value == "11" || document.getElementById("expirymonth").value == "12")
							count ++;
						else
						{
							alert("The card has expired..");
							return false;
						}
					}
					if(document.getElementById("expiryyear").value < 2018)
					{
					
						alert("The card has expired..");
						return false;
						
					}
					
				}
				
				if(document.getElementById("cvv").value == "")
	    		{
	    			alert("CVV cannot be empty.");
	    			return false;
	    		}
				else
				{
					var reg = new RegExp("^[0-9]{3}$");
					if(!reg.test(document.getElementById("cvv").value))
					{
						alert("Please write the correct format of cvv.");
						return false;
					}
					else
						count ++;
				}
				
				if(count == 6)
					return true;
			}
			
	    }
	</script>
	
</head>

<body>

   <!-- *** TOPBAR *** -->
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
                        <li><a href="<c:url value='/home' />">Home</a>
                        </li>
                        <li>Checkout - Payment method</li>
                    </ul>
                </div>
                <div class="col-md-12" id="checkout">
                    <div class="box">
                        <form method="post" action="checkout3" onsubmit="return validCard()">
                            <h1>Checkout - Payment method</h1>
                            <ul class="nav nav-pills nav-justified">
                                <li class="disabled"><a href="#"><i class="fa fa-map-marker"></i><br>Address</a>
                                </li>
                                <li class="disabled"><a href="#"><i class="fa fa-truck"></i><br>Delivery Method</a>
                                </li>
                                <li class="active"><a href="#"><i class="fa fa-money"></i><br>Payment Method</a>
                                </li>
                                <li class="disabled"><a href="#"><i class="fa fa-eye"></i><br>Order Review</a>
                                </li>
                            </ul>
                            <div class="content">
                            	<div class="row" style="display: none;"> 
                                     <input type="text" id="userID" name="userID" value="${buyerID}">
                               </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="box payment-method">

                                            <h4>Cash on delivery</h4>

                                            <p>You pay when you get it.</p>

                                            <div class="box-footer text-center">

                                                <input type="radio" id="payment" name="payment" value="cash" checked>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="box payment-method">

                                            <h4>Payment gateway</h4>

                                            <p>VISA and Mastercard only.</p>

											<ul class="list-unstyled list-inline">
												<li class="list-inline-item"><img src="<c:url value='/img/visa.svg' />" alt="visa" width="50"></li>
												<li class="list-inline-item"><img src="<c:url value='/img/mastercard.svg' />" alt="mastercard" width="50"></li>
											</ul>
											<div class="row">
											  <div class="col-sm-10 form-group">
												<input type="text" id="cardname" placeholder="Name On Card"  class="form-control">
											  </div>
											  <div class="col-sm-10 form-group">
												<input type="text" id="cardnumber" placeholder="Card Number" maxlength="16"  class="form-control">
											  </div>
											  <div class="col-sm-4 form-group">
												<input type="text" id="expirymonth" placeholder="Expiry Month"  class="form-control">
											  </div>
											  <div class="col-sm-4 form-group">
												<input type="text" id="expiryyear" placeholder="Expiry Year"  class="form-control">
											  </div>
											  <div class="col-sm-4 form-group">
												<input type="text" id="cvv" placeholder="CVV" maxlength="3"  class="form-control">
											  </div>
											</div>
											<div class="box-footer text-center">

                                                <input type="radio" id="payment" name="payment" value="card">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary">Continue to Order review<i class="fa fa-chevron-right"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
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

  
        
            <!-- *** SCRIPTS TO INCLUDE *** -->
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