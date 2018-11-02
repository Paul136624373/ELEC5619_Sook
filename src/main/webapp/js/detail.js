//==========================================================================================================================================================
	// Use Ajax to display information of selected product dynamically.
//==========================================================================================================================================================
function detail_load()
{
	// Get parameter from url
	var parameters = new Object();
	parameters = get_parameters();
	var p = parameters["name"];
	//Get the parameter and replace "%20" with " ", as space is changed to "%20" in the process of passing value
	var name = p.replace(/%20/g, " ");
	var gender = parameters["gender"];
	if (gender == "Male")
	{
		document.getElementById('gender').innerHTML = '<a href="category-man.html">Men</a>';
		document.getElementById('man_active').className = "active";
	}
	else
	{
		document.getElementById('gender').innerHTML = '<a href="category-lady.html">Ladies</a>';
		document.getElementById('lady_active').className = "active";
	}
	document.getElementById('product_name').innerHTML = name;
	var xmlhttp;
	if (window.XMLHttpRequest) 
	{ // code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} 
	else 
	{ // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function () 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) 
		{
			var xmlDoc = xmlhttp.responseXML;
			var all = xmlDoc.getElementsByTagName("PRODUCT"); //Used to store all product
			var img = "";
			var infor = "";
			var detail = "";
			var Armani_num = 0;
			var Versace_num = 0;
			var Carlo_Bruni_num = 0;
			var Jack_Honey_num = 0;
			for (var i = 0; i < all.length; i++) 
			{
				// Get and filter information from detail.xml. Then display on the page.
				if (all[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue == name)
				{
					if (all[i].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani") 
					{
						Armani_num = Armani_num + 1;
					} 
					else if (all[i].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace") 
					{
						Versace_num = Versace_num + 1;
					} 
					else if (all[i].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni") 
					{
						Carlo_Bruni_num = Carlo_Bruni_num + 1;
					} 
					else if (all[i].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey") 
					{
						Jack_Honey_num = Jack_Honey_num + 1;
					}
					img = img + "<img src='";
					img = img + all[i].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' width='450px'>";
					
					infor = infor + '<h1 class="text-center">';
					infor = infor + all[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
					infor = infor + '</h1><p class="goToDescription"><a href="#product_details" class="scroll-to">Scroll to product details, material & care and sizing</a></p><p class="price">';
					infor = infor + all[i].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
					infor = infor + '</p><p class="text-center buttons"><a href="basket.html" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Add to cart</a></p>';
					
					detail = detail + '<p><h4>Product details</h4><p>';
					detail = detail + all[i].getElementsByTagName("EXTERIOR")[0].childNodes[0].nodeValue;
					detail = detail + '</p><h4>Material & care</h4><ul>';
					try 
					{
						detail = detail + '<li>' + all[i].getElementsByTagName("MATERIAL")[0].childNodes[0].nodeValue + '</li>';
					} catch (er) {}
					try 
					{
						detail = detail + '<li>' + all[i].getElementsByTagName("CARE")[0].childNodes[0].nodeValue + '</li>';
					} catch (er) {}
					detail = detail + '</ul><h4>Size & Fit</h4><ul>';
					try 
					{
						detail = detail + '<li>' + all[i].getElementsByTagName("FIT")[0].childNodes[0].nodeValue + '</li>';
					} catch (er) {}
					try 
					{
						detail = detail + '<li>' + all[i].getElementsByTagName("SIZE")[0].childNodes[0].nodeValue + '</li>';
					} catch (er) {}
					detail = detail + '</ul><blockquote><p><em>';
					detail = detail + all[i].getElementsByTagName("NOTE")[0].childNodes[0].nodeValue;
					detail = detail + '</em></p></blockquote><hr><div class="social"><h4>Show it to your friends</h4><p><a href="#" class="external facebook" data-animate-hover="pulse"><i class="fa fa-facebook"></i></a><a href="#" class="external gplus" data-animate-hover="pulse"><i class="fa fa-google-plus"></i></a><a href="#" class="external twitter" data-animate-hover="pulse"><i class="fa fa-twitter"></i></a><a href="#" class="email" data-animate-hover="pulse"><i class="fa fa-envelope"></i></a></p></div>';
					
					break;
				}
			}
			document.getElementById('mainImage').innerHTML = img;
			document.getElementById('product_information').innerHTML = infor;
			document.getElementById('product_details').innerHTML = detail;
			document.getElementById('Armani_num').innerHTML = "<input type='checkbox' id='Armani_ck'>Armani (" + Armani_num + ")";
			document.getElementById('Versace_num').innerHTML = "<input type='checkbox' id='Versace_ck'>Versace (" + Versace_num + ")";
			document.getElementById('Carlo_Bruni_num').innerHTML = "<input type='checkbox' id='Carlo_Bruni_ck'>Carlo Bruni (" + Carlo_Bruni_num + ")";
			document.getElementById('Jack_Honey_num').innerHTML = "<input type='checkbox' id='Jack_Honey_ck'>Jack Honey (" + Jack_Honey_num + ")";
		}
	};
	xmlhttp.open("GET", "detail.xml", true);
	xmlhttp.send();
}

//==========================================================================================================================================================
	// Get parameters from url.
//==========================================================================================================================================================
function get_parameters() 
{
	var url = location.search;
	var parameters = new Object();
	if (url.indexOf("?") != -1) 
	{
		var s = url.substr(1);
		ss = s.split("&");
		for(var i = 0; i < ss.length; i ++) 
		{
	 		parameters[ss[i].split("=")[0]]=(ss[i].split("=")[1]);
		}
	}
	return parameters;
}