<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title></title>
<style>
 html,
 body {
            width: 100%;
            height: 100%;
            margin: 0;
       }
        canvas {
            background-color: #ccc;
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
        }
.button {
    background-color: silver;
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

<div style="border:1px solid #DAA520;background-color: #DAA520;">
<H1 id="home" style="position:relative;top:-10px;left:20px;">Robot Live</H1>
</div>

<canvas id="myCanvas" width="200" height="100"
style="border:1px solid #c3c3c3;">
Your browser does not support the canvas element.
</canvas>
</body>
</html>