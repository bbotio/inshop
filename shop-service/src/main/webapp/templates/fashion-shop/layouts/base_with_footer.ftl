<@layout.extends name="fashion-shop/layouts/base_with_header.ftl">
    <@layout.put block="footer">
    <div class="footer-links">
        <div class="container">
            <div class="col-md-4 col-sm-8">
                <h5>Information</h5>
                <ul>
                    <li><a href="/about">About Us</a></li>
                    <li><a href="#">Delivery Information</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">Terms &amp; Conditions</a></li>
                </ul>
            </div>
            <div class="col-md-4 col-sm-5">
                <h5>Follow Us</h5>
                <ul>
                    <li><a href="${instagramUrl!"#"}">Instagram</a></li>
                </ul>
            </div>
            <div class="col-md-4 col-sm-12 last">
                <h5>Contact Us</h5>
                <ul>
                    <li>My Shop</li>
                    <li>
                        1247 LB Nagar Road, Hyderabad, Telangana - 35
                    </li>
                    <li>
                        Email: <a href="#">info@myshop.com</a>
                    </li>
                </ul>
                <h4 class="lead">
                    Tel: <span>1(234) 567-9842</span>
                </h4>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <p class="pull-left">
                <a href="http://inshop.yt">Inshop</a> All rights reserved
            </p>
        </div>
    </div>
    </@layout.put>
</@layout.extends>