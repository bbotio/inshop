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
        <#list products as product>
        <div class="col-md-4 col-sm-6">
            <div class="product-col">
                <div class="image">
                    <img src="${product.imageUrl}" alt="product" class="img-responsive">
                </div>
                <div class="caption">
                    <h4><a href="/product/${product.id}">${product.name!"Item without a name"}</a></h4>

                    <div class="description">
                        ${product.description}
                    </div>
                    <div class="price">
                        <span class="price-new">${product.price.price} ${product.price.currency}</span>
                        <span class="price-old">$0</span>
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