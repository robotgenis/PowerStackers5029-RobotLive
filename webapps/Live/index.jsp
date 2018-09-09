<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title></title>
<style>
.button {
    background-color: #c5e2e6;
    border: none;
    color: Gray;
    padding: 20px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}  
h1 {
    font-family: Arial Black;
    font-size: 300%;
}

a {
	font-family: Arial Black;
    font-size: 200%;
}
div {

    width: inherit;
  text-align: left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#home").click(function(){
        var url = "/";
		$(location).attr('href',url)
    });
});
</script>
</head>
<body>

<div style="border:1px solid #287982;background-color: #287982;">
<H1 id="home" style="position:relative;top:-10px;left:20px;">Connect Arduino</H1>
</div>

<div style="position:relative;top:40px;text-align:center;" >
<a href="/live/mobile" type="button" onclick="" class="button" style="position:relative;left:20px;">Mobile Update</a>
</div>
<div style="position:relative;top:40px;text-align:center;" >
<a href="/live/dash" type="button" onclick="" class="button" style="position:relative;left:20px;">Dashboard</a>
</div>
</body>
</html>