<!DOCTYPE html>
<html>
<head>
<@layout.block name="head">
    <title>InShop</title>
    <link href="css/application.css" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="utf-8">
    <script>
    /* yeah we need this empty stylesheet here. It's cool chrome & chromium fix
       chrome fix https://code.google.com/p/chromium/issues/detail?id=167083
       https://code.google.com/p/chromium/issues/detail?id=332189
     */
    </script>
</@layout.block>
</head>
<body class="background-dark">
<div class="logo">
    <h4><a href="#">In<strong>Shop</strong></a></h4>
</div>
<nav id="sidebar" class="sidebar nav-collapse collapse">
<@layout.block name="sidebar">
<!-- put sidebar here -->
</@layout.block>
</nav>    
<div class="wrap">
    <header class="page-header">
        <div class="navbar">
            <ul class="nav navbar-nav navbar-right pull-right">
                <@layout.block name="top">
                    <li class="hidden-xs"><a href="login.html"><i class="fa fa-sign-out"></i></a></li>
                </@layout.block>
            </ul>
        </div>
    </header>  
    <div class="content container">
        <@layout.block name="content">
            <!-- put content here -->
        </@layout.block>
    </div>
    <div class="loader-wrap hiding hide">
        <i class="fa fa-circle-o-notch fa-spin"></i>
    </div>
</div>
<@layout.block name="scripts">
<!-- common libraries. required for every page-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="scripts/jquery.pjax.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.1/js/bootstrap.js"></script>
<script src="scripts/widgster.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.5.2/underscore.js"></script>

<!-- common application js -->
<script src="scripts/app.js"></script>
<script src="scripts/settings.js"></script>
</@layout.block>
</body>
</html>
