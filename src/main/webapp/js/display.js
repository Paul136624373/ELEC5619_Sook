//Get the gender of displayed products.
var g = document.getElementById("gender").innerHTML;
var gender;
if(g == "Men")
{
	gender = "Male";
}
else
{
	gender = "Female";
}

//==========================================================================================================================================================
	// Clear the checkboxes for all brands.
//==========================================================================================================================================================
function clear()
{
	document.getElementById("Armani_ck").checked = false;
	document.getElementById("Versace_ck").checked = false;
	document.getElementById("Carlo_Bruni_ck").checked = false;
	document.getElementById("Jack_Honey_ck").checked = false;
}


//==========================================================================================================================================================
	// Record the brands that are checked
//==========================================================================================================================================================
function brand_checked()
{
	var product_brand = "";		// Used to store brands that checked.
	if(document.getElementById("Armani_ck").checked)
	{
		product_brand = product_brand + "A";
	}

	if(document.getElementById("Versace_ck").checked)
	{
		product_brand = product_brand + "V";
	}

	if(document.getElementById("Carlo_Bruni_ck").checked)
	{
		product_brand = product_brand + "C";
	}

	if(document.getElementById("Jack_Honey_ck").checked)
	{
		product_brand = product_brand + "J";
	}

	if (product_brand == "")
	{
		alert("Please choose at least one brand.");
	}
	else
	{
		change_two_parameter("brand_apply", product_brand, "page", "1");
	}
}

//==========================================================================================================================================================
	// Use Ajax to display information of products dynamically.
//==========================================================================================================================================================
function display_products()
{
	// Get param from url
	var parameters = new Object();
	parameters = get_parameters();
	var name = parameters["name"];
	var npp = parameters["num"];
	var brand_apply = parameters["brand_apply"];
	// Get sorting method.
	var s = parameters["select"];
	if (s != null)
	{
		s = s.replace(/%20/g, " ");
	}
	var product_brand = new Array();
	if (brand_apply !=null)
	{
		for (var i=0;i<brand_apply.length;i++)
		{
			if (brand_apply.charAt(i) == "A")
			{
				product_brand.push("Armani");
			}
			else if (brand_apply.charAt(i) == "V")
			{
				product_brand.push("Versace");
			}
			else if (brand_apply.charAt(i) == "C")
			{
				product_brand.push("Carlo Bruni");
			}
			else if (brand_apply.charAt(i) == "J")
			{
				product_brand.push("Jack Honey");
			}
		}
	}

	var num_per_page;				// Store the number of products per page.
	if (npp == null || npp == "all")
	{
		num_per_page = 10000;	// Show all products by default.
	}
	else
	{
		num_per_page = parseInt(npp);
	}
	if (num_per_page == 6)
	{
		document.getElementById("num_6").className = "btn btn-default btn-sm btn-primary";
		document.getElementById("num_12").className = "btn btn-default btn-sm";
		document.getElementById("num_all").className = "btn btn-default btn-sm";
	}
	else if(num_per_page == 12)
	{
		document.getElementById("num_6").className = "btn btn-default btn-sm";
		document.getElementById("num_12").className = "btn btn-default btn-sm btn-primary";
		document.getElementById("num_all").className = "btn btn-default btn-sm";
	}
	else
	{
		document.getElementById("num_6").className = "btn btn-default btn-sm";
		document.getElementById("num_12").className = "btn btn-default btn-sm";
		document.getElementById("num_all").className = "btn btn-default btn-sm btn-primary";
	}
	// Use ajax to dynamically generate web pages
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
			var txt = "";
			var total_num = 0;
			var page_num = 0;
			var current_page = 0;
			var Armani_num = 0;
			var Versace_num = 0;
			var Carlo_Bruni_num = 0;
			var Jack_Honey_num = 0;
			var sort_id = new Array();

			// Get the number of current page.
			if (parameters["page"] != null)
			{
				current_page = parseInt(parameters["page"]);
			}
			else
			{
				current_page = 1;
			}
			for (var i=0;i<all.length;i++)
			{
				sort_id.push(i);
			}
			if (s == "Price: low-high" || s == null)
			{
				for (var i=0;i<all.length;i++)
				{

				  for (var j=0;j<(all.length-i-1);j++)
				  {
					var price1_string = all[sort_id[j]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
					var price1 = parseInt(price1_string.replace(/[^0-9]/ig,""));
					// Get the number of price, ignore the unit.

					var price2_string = all[sort_id[j+1]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
					var price2 = parseInt(price2_string.replace(/[^0-9]/ig,""));
					// Get the number of price, ignore the unit.

					if(price1 > price2)
					{
					  var t = sort_id[j];
					  sort_id[j] = sort_id[j+1];
					  sort_id[j+1] = t;
					}
				  }

				}
				$("#sort_by").val("Price: low-high"); 
			}
			else
			{
				for (var i=0;i<all.length;i++)
				{

				  for (var j=0;j<(all.length-i-1);j++)
				  {
					var price1_string = all[sort_id[j]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
					var price1 = parseInt(price1_string.replace(/[^0-9]/ig,""));
					// Get the number of price, ignore the unit.

					var price2_string = all[sort_id[j+1]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
					var price2 = parseInt(price2_string.replace(/[^0-9]/ig,""));
					// Get the number of price, ignore the unit.

					if(price1 < price2)
					{
					  var t = sort_id[j];
					  sort_id[j] = sort_id[j+1];
					  sort_id[j+1] = t;
					}
				  }
				}
				$("#sort_by").val("Price: high-low"); 
			}

			if (brand_apply == null)				//Do following while brand filter is not used.
			{
				// Get the number of total products that need to be displayed
				for (var i = 0; i < all.length; i++)
				{
					// Get and filter information from detail.xml. Then display on the page.
					if (name == "Accessories")
					{
						if ((all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Bags" || all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Belts") && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							total_num = total_num + 1;
							if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani")
							{
								Armani_num = Armani_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace")
							{
								Versace_num = Versace_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni")
							{
								Carlo_Bruni_num = Carlo_Bruni_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey")
							{
								Jack_Honey_num = Jack_Honey_num + 1;
							}

						}
					}
					else if (name != null)
					{
						if (all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == name && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							total_num = total_num + 1;
							if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani")
							{
								Armani_num = Armani_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace")
							{
								Versace_num = Versace_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni")
							{
								Carlo_Bruni_num = Carlo_Bruni_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey")
							{
								Jack_Honey_num = Jack_Honey_num + 1;
							}

						}
					}
					else
					{
						if (all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							total_num = total_num + 1;
							if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani")
							{
								Armani_num = Armani_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace")
							{
								Versace_num = Versace_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni")
							{
								Carlo_Bruni_num = Carlo_Bruni_num + 1;
							} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey")
							{
								Jack_Honey_num = Jack_Honey_num + 1;
							}

						}
					}
				}
			}
			else
			{
				for (var i = 0; i < all.length; i++)
				{
					// Get and filter information from detail.xml. Then display on the page.
					if (name == "Accessories")
					{
						if ((all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Bags" || all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Belts") && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							for (var j=0;j<product_brand.length;j++)
							{
								if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == product_brand[j])
								{
									total_num = total_num + 1;
									if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani")
									{
										Armani_num = Armani_num + 1;
									}
									else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace")
									{
										Versace_num = Versace_num + 1;
									}
									else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni")
									{
										Carlo_Bruni_num = Carlo_Bruni_num + 1;
									}
									else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey")
									{
										Jack_Honey_num = Jack_Honey_num + 1;
									}

								}
							}
						}
					}
					else if (name != null)
					{
						if (all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == name && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							for (var j=0;j<product_brand.length;j++)
							{
								if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == product_brand[j])
								{
									total_num = total_num + 1;
									if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani")
									{
										Armani_num = Armani_num + 1;
									} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace")
									{
										Versace_num = Versace_num + 1;
									} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni")
									{
										Carlo_Bruni_num = Carlo_Bruni_num + 1;
									} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey")
									{
										Jack_Honey_num = Jack_Honey_num + 1;
									}

								}
							}
						}
					}
					else
					{
						if (all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							for (var j=0;j<product_brand.length;j++)
							{
								if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == product_brand[j])
								{
									total_num = total_num + 1;
									if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Armani")
									{
										Armani_num = Armani_num + 1;
									} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Versace")
									{
										Versace_num = Versace_num + 1;
									} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Carlo Bruni")
									{
										Carlo_Bruni_num = Carlo_Bruni_num + 1;
									} else if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == "Jack Honey")
									{
										Jack_Honey_num = Jack_Honey_num + 1;
									}

								}
							}
						}
					}
				}
			}

			if (total_num <= num_per_page)
			{
				page_num = 1;
			}
			else
			{
				page_num = Math.ceil(total_num/num_per_page);
			}

			var page_display = "";
			for (var i=1;i<=page_num;i++)
			{

				if(current_page == i)
				{
					page_display = page_display + '<li class="active"><a href="JavaScript:change_parameter(' + "'page','" + i + "'" + ');">' + i + '</a></li>';
				}
				else
				{
					page_display = page_display + '<li><a href="JavaScript:change_parameter(' + "'page','" + i + "'" + ');">' + i + '</a></li>';
				}
			}
			document.getElementById('to_page').innerHTML = page_display;

			var skipped = 0;					// Store the number of products that should be displayed in previous page.
			var already_display = 0;			// Store the number of products that are ready to be displayed.

			if (brand_apply == null)				//Do following while brand filter is not used.
			{
				// Get the number of total products that need to be displayed
				for (var i = 0; i < all.length; i++)
				{
					// Get and filter information from detail.xml. Then display on the page.
					if (name == "Accessories")
					{
						if ((all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Bags" || all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Belts") && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							if (skipped < num_per_page*(current_page-1))
							{
								skipped = skipped + 1;
								continue;
							}
							else if (already_display == num_per_page)
							{
								break;
							}
							else
							{
								already_display = already_display + 1;
								txt = txt + "<div class='col-md-4 col-sm-6'><div class='product'><div class='flip-container'><div class='flipper'><div class='front'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}

								txt = txt + "</a></div><div class='back'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG2")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}

								txt = txt + "</a></div></div></div><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='invisible'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}
								txt = txt + "</a><div class='text'><h3><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
								} catch (er) {}
								txt = txt + "</a></h3><p class='price'>";
								try
								{
									txt = txt + all[sort_id[i]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
								} catch (er) {}
								txt = txt + "</p><p class='buttons'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='btn btn-default'>View detail</a><a href='basket.html' class='btn btn-primary'><i class='fa fa-shopping-cart'></i>Add to cart</a></p></div></div></div>";
							}
						}
					}
					else if (name != null)
					{
						if (all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == name && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							if (skipped < num_per_page*(current_page-1))
							{
								skipped = skipped + 1;
								continue;
							}
							else if (already_display == num_per_page)
							{
								break;
							}
							else
							{
								already_display = already_display + 1;
								txt = txt + "<div class='col-md-4 col-sm-6'><div class='product'><div class='flip-container'><div class='flipper'><div class='front'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}

								txt = txt + "</a></div><div class='back'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG2")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}

								txt = txt + "</a></div></div></div><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='invisible'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}
								txt = txt + "</a><div class='text'><h3><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
								} catch (er) {}
								txt = txt + "</a></h3><p class='price'>";
								try
								{
									txt = txt + all[sort_id[i]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
								} catch (er) {}
								txt = txt + "</p><p class='buttons'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='btn btn-default'>View detail</a><a href='basket.html' class='btn btn-primary'><i class='fa fa-shopping-cart'></i>Add to cart</a></p></div></div></div>";
							}
						}
					}
					else
					{
						if (all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							if (skipped < num_per_page*(current_page-1))
							{
								skipped = skipped + 1;
								continue;
							}
							else if (already_display == num_per_page)
							{
								break;
							}
							else
							{
								already_display = already_display + 1;
								txt = txt + "<div class='col-md-4 col-sm-6'><div class='product'><div class='flip-container'><div class='flipper'><div class='front'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}

								txt = txt + "</a></div><div class='back'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG2")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}

								txt = txt + "</a></div></div></div><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='invisible'>";
								try
								{
									txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
								} catch (er) {}
								txt = txt + "</a><div class='text'><h3><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
								try
								{
									txt = txt + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
								} catch (er) {}
								txt = txt + "</a></h3><p class='price'>";
								try
								{
									txt = txt + all[sort_id[i]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
								} catch (er) {}
								txt = txt + "</p><p class='buttons'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='btn btn-default'>View detail</a><a href='basket.html' class='btn btn-primary'><i class='fa fa-shopping-cart'></i>Add to cart</a></p></div></div></div>";
							}
						}
					}
				}
			}
			else
			{
				for (var i = 0; i < all.length; i++)
				{
					// Get and filter information from detail.xml. Then display on the page.
					if (name == "Accessories")
					{
						if ((all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Bags" || all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == "Belts") && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							for (var j=0;j<product_brand.length;j++)
							{
								if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == product_brand[j])
								{
									if (skipped < num_per_page*(current_page-1))
									{
										skipped = skipped + 1;
										continue;
									}
									else if (already_display == num_per_page)
									{
										break;
									}
									else
									{
										already_display = already_display + 1;
										txt = txt + "<div class='col-md-4 col-sm-6'><div class='product'><div class='flip-container'><div class='flipper'><div class='front'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}

										txt = txt + "</a></div><div class='back'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG2")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}

										txt = txt + "</a></div></div></div><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='invisible'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}
										txt = txt + "</a><div class='text'><h3><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
										} catch (er) {}
										txt = txt + "</a></h3><p class='price'>";
										try
										{
											txt = txt + all[sort_id[i]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
										} catch (er) {}
										txt = txt + "</p><p class='buttons'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='btn btn-default'>View detail</a><a href='basket.html' class='btn btn-primary'><i class='fa fa-shopping-cart'></i>Add to cart</a></p></div></div></div>";
									}
								}
							}
						}
					}
					else if (name != null)
					{
						if (all[sort_id[i]].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue == name && all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							for (var j=0;j<product_brand.length;j++)
							{
								if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == product_brand[j])
								{
									if (skipped < num_per_page*(current_page-1))
									{
										skipped = skipped + 1;
										continue;
									}
									else if (already_display == num_per_page)
									{
										break;
									}
									else
									{
										already_display = already_display + 1;
										txt = txt + "<div class='col-md-4 col-sm-6'><div class='product'><div class='flip-container'><div class='flipper'><div class='front'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}

										txt = txt + "</a></div><div class='back'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG2")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}

										txt = txt + "</a></div></div></div><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='invisible'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}
										txt = txt + "</a><div class='text'><h3><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
										} catch (er) {}
										txt = txt + "</a></h3><p class='price'>";
										try
										{
											txt = txt + all[sort_id[i]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
										} catch (er) {}
										txt = txt + "</p><p class='buttons'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='btn btn-default'>View detail</a><a href='basket.html' class='btn btn-primary'><i class='fa fa-shopping-cart'></i>Add to cart</a></p></div></div></div>";
									}
								}
							}
						}
					}
					else
					{
						if (all[sort_id[i]].getElementsByTagName("GENDER")[0].childNodes[0].nodeValue == gender)
						{
							for (var j=0;j<product_brand.length;j++)
							{
								if (all[sort_id[i]].getElementsByTagName("BRAND")[0].childNodes[0].nodeValue == product_brand[j])
								{
									if (skipped < num_per_page*(current_page-1))
									{
										skipped = skipped + 1;
										continue;
									}
									else if (already_display == num_per_page)
									{
										break;
									}
									else
									{
										already_display = already_display + 1;
										txt = txt + "<div class='col-md-4 col-sm-6'><div class='product'><div class='flip-container'><div class='flipper'><div class='front'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}

										txt = txt + "</a></div><div class='back'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG2")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}

										txt = txt + "</a></div></div></div><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='invisible'>";
										try
										{
											txt = txt + "<img src='" + all[sort_id[i]].getElementsByTagName("IMG1")[0].childNodes[0].nodeValue + "' alt='" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='img-responsive' height='600px' width='450px'>";
										} catch (er) {}
										txt = txt + "</a><div class='text'><h3><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "'>";
										try
										{
											txt = txt + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
										} catch (er) {}
										txt = txt + "</a></h3><p class='price'>";
										try
										{
											txt = txt + all[sort_id[i]].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;
										} catch (er) {}
										txt = txt + "</p><p class='buttons'><a href='detail.html?gender=" + gender + "&name=" + all[sort_id[i]].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "' class='btn btn-default'>View detail</a><a href='basket.html' class='btn btn-primary'><i class='fa fa-shopping-cart'></i>Add to cart</a></p></div></div></div>";
									}
								}
							}
						}
					}
				}
			}

			document.getElementById('Armani_num').innerHTML = "<input type='checkbox' id='Armani_ck'>Armani (" + Armani_num + ")";
			document.getElementById('Versace_num').innerHTML = "<input type='checkbox' id='Versace_ck'>Versace (" + Versace_num + ")";
			document.getElementById('Carlo_Bruni_num').innerHTML = "<input type='checkbox' id='Carlo_Bruni_ck'>Carlo Bruni (" + Carlo_Bruni_num + ")";
			document.getElementById('Jack_Honey_num').innerHTML = "<input type='checkbox' id='Jack_Honey_ck'>Jack Honey (" + Jack_Honey_num + ")";
			if(brand_apply != null)
			{
				for (var j=0;j<product_brand.length;j++)
				{
					if (product_brand[j] == "Armani")
					{
						document.getElementById('Armani_num').innerHTML = "<input type='checkbox' id='Armani_ck' checked>Armani (" + Armani_num + ")";
					}
					if (product_brand[j] == "Versace")
					{
						document.getElementById('Versace_num').innerHTML = "<input type='checkbox' id='Versace_ck' checked>Versace (" + Versace_num + ")";
					}

					if (product_brand[j] == "Carlo Bruni")
					{
						document.getElementById('Carlo_Bruni_num').innerHTML = "<input type='checkbox' id='Carlo_Bruni_ck' checked>Carlo Bruni (" + Carlo_Bruni_num + ")";
					}

					if (product_brand[j] == "Jack Honey")
					{
						document.getElementById('Jack_Honey_num').innerHTML = "<input type='checkbox' id='Jack_Honey_ck' checked>Jack Honey (" + Jack_Honey_num + ")";
					}
				}
			}
			document.getElementById('product_list').innerHTML = txt;
			document.getElementById('display_num').innerHTML = already_display;
			document.getElementById('total_num').innerHTML = total_num;

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

//==========================================================================================================================================================
	// Change a parameter or add a new parameter into the url.
	// param: string, parameter's name
	// value: string, parameter's value
//==========================================================================================================================================================
function change_parameter(param,value)
{
	var previous=window.location.href ;
	var now="";
	var r = new RegExp("(^|)"+ param +"=([^&]*)(|$)");
	var t = param + "=" + value;
	if(previous.match(r) != null)
	{
		now= previous.replace(eval(r),t);
	}
	else
	{
		if(previous.match("[\?]"))
		{
			now= previous + "&" + t;
		}
		else
		{
			now= previous + "?" + t;
		}
	}
	location.href=now;
}


//==========================================================================================================================================================
	// Change two parameter or add a new parameter into the url.
	// param1: string, 1st parameter's name
	// value1: string, 1st parameter's value
	// param2: string, 2nd parameter's name
	// value2: string, 2nd parameter's value
//==========================================================================================================================================================
function change_two_parameter(param1,value1,param2,value2)
{
	var previous=window.location.href ;
	var now1="";
	var now2="";
	var r1 = new RegExp("(^|)"+ param1 +"=([^&]*)(|$)");
	var t1 = param1 + "=" + value1;
	var r2 = new RegExp("(^|)"+ param2 +"=([^&]*)(|$)");
	var t2 = param2 + "=" + value2;
	if(previous.match(r1) != null)
	{
		now1= previous.replace(eval(r1),t1);
	}
	else
	{
		if(previous.match("[\?]"))
		{
			now1= previous + "&" + t1;
		}
		else
		{
			now1= previous + "?" + t1;
		}
	}
	if(now1.match(r2) != null)
	{
		now2= now1.replace(eval(r2),t2);
	}
	else
	{
		if(now1.match("[\?]"))
		{
			now2= now1 + "&" + t2;
		}
		else
		{
			now2= now1 + "?" + t2;
		}
	}
	location.href=now2;
}
