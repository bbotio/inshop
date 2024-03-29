<@layout.extends name="fashion-shop/layouts/base.ftl">
    <@layout.put block="header">
    <div class="header-top">
        <div class="container">
            <div class="header-links">
                <ul class="nav navbar-nav pull-right">
                    <li>
                        <a href="/">
                            <i class="fa fa-home" title="" data-original-title="Home"></i>
                                        <span class="hidden-sm hidden-xs">
                                            Home
                                        </span>
                        </a>
                    </li>
                    <li>
                        <a href="/cart">
                            <i class="fa fa-shopping-cart" title="" data-original-title="Shopping Cart"></i>
                                        <span class="hidden-sm hidden-xs">
                                            Shopping Cart
                                        </span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="main-header">
            <div class="row">
                <div class="col-md-3">
                    <div id="search">
                        <div class="input-group">
                            <input type="text" class="form-control input-lg" placeholder="Search">
                              <span class="input-group-btn">
                                <button class="btn btn-lg" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                              </span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div id="logo">
                        <a href="/index"><img src="/images/fashion-shop/logo.png" title="Fashion Shoppe"
                                                    alt="Fashion Shoppe" class="img-responsive"></a>
                    </div>
                </div>
                <div class="col-md-3">
                    <div id="cart" class="btn-group btn-block">
                        <button type="button" data-toggle="dropdown" class="btn btn-block btn-lg dropdown-toggle">
                            <i class="fa fa-shopping-cart"></i>
                            <span class="hidden-md">Cart:</span>
                            <span id="cart-total">0 item(s) - $0.00</span>
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <p class="text-center">Your shopping cart is empty!</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </@layout.put>
</@layout.extends>
