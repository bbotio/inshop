<@layout.extends name="fashion-shop/index.ftl">
    <@layout.put block="activateButton">
        <a href="category-list.ftl" class="active">
            <i class="fa fa-th-list" title="List View"></i>
        </a>
        <a href="category-grid.ftl">
            <i class="fa fa-th" title="Grid View"></i>
        </a>
    </@layout.put>

    <@layout.put block="items">
        <#list 1..6 as x>
        <div class="col-xs-12">
            <div class="product-col list clearfix">
                <div class="image">
                    <img src="/images/fashion-shop/product-images/14.jpg" alt="product" class="img-responsive">
                </div>
                <div class="caption">
                    <h4><a href="product-full.ftl">Fashion Garments</a></h4>

                    <div class="description">
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
                        the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley
                        of type and scrambled it to make a type specimen book.
                    </div>
                    <div class="price">
                        <p class="price-tax">Ex Tax: $279.99</p>
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