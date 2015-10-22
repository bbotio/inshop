<@layout.extends name="fashion-shop/index.ftl">
    <@layout.put block="activateButton">
        <a href="category-list.ftl">
            <i class="fa fa-th-list" title="List View"></i>
        </a>
        <a href="category-grid.ftl" class="active">
            <i class="fa fa-th" title="Grid View"></i>
        </a>
    </@layout.put>

    <@layout.put block="items">
        <#list 1..6 as x>
        <div class="col-md-4 col-sm-6">
            <div class="product-col">
                <div class="image">
                    <img src="/images/fashion-shop/product-images/14.jpg" alt="product" class="img-responsive">
                </div>
                <div class="caption">
                    <h4><a href="product.ftl">Fashion Garments</a></h4>

                    <div class="description">
                        We are so lucky living in such a wonderful time. Our almost unlimited ...
                    </div>
                    <div class="price">
                        <span class="price-new">$199.50</span>
                        <span class="price-old">$249.50</span>
                    </div>
                    <div class="cart-button button-group">
                        <button type="button" title="Wishlist" class="btn btn-wishlist">
                            <i class="fa fa-heart"></i>
                        </button>
                        <button type="button" title="Compare" class="btn btn-compare">
                            <i class="fa fa-bar-chart-o"></i>
                        </button>
                        <button type="button" class="btn btn-cart">
                            Add to cart
                            <i class="fa fa-shopping-cart"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        </#list>
    </@layout.put>
</@layout.extends>