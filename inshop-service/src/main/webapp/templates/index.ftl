<html>
<head>
    <meta charset="utf-8"/>
    <title>InShop</title>
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"/>
    <meta name="description" content="make shop from your instagram account">
    <meta name="author" content="Aleksei Kornev">
    <meta name="keywords" content="instagram, shop, e-commerce, inshop, clothing, clothing shop, accessories,
    accessories shop, gadgets, gadgets shop, mobile, mobile shop"/>
    <link href="css/style.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Exo+2:400,100,300,500,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400italic,400,300,600,700' rel='stylesheet'
          type='text/css'>
</head>
<body>
<div id="header">
    <div class="row">
        <div class="span12">
            <h1>
                <a href="#">InShop</a>
            </h1>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<div id="top">
    <div id="content" class="introduction hero">
        <div class="container">
            <div class="intro align-centered">

                <h1>The simple way to be closer to your customer</h1>
                <h4>In one click turn your instagram account into a modern shop.</h4>

                <form id="form" action="/authorize" method="POST">
                    <p>
                        <a class="login-button" type="submit" onclick="$(this).closest('form').submit();"></a>
                    </p>
                </form>

                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<script src="/scripts/jquery.min.js"></script>
<script src="/scripts/scripts.js"></script>
</body>
</html>
