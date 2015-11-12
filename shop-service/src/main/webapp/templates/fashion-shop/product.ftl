<@layout.extends name="fashion-shop/layouts/base_with_footer.ftl">
    <@layout.put block="content">
    <div class="row">
        <div class="col-md-9">
            <div class="row product-info">
                <div class="col-sm-5 images-block">
                    <p>
                        <img src="${product.imageUrl}" alt="Image" class="img-responsive thumbnail">
                    </p>
                </div>
                <div class="col-sm-7 product-details">
                    <h2>${product.name!"Item without a name"}</h2>
                    <hr>
                    <ul class="list-unstyled manufacturer">
                        <li>
                            <span>Brand:</span> Indian spices
                        </li>
                        <li><span>Reward Points:</span> 300</li>
                        <li>
                            <span>Availability:</span> <strong class="label label-success">In Stock</strong>
                        </li>
                    </ul>
                    <hr>
                    <div class="price">
                        <span class="price-head">Price :</span>
                        <span class="price-new">${product.price.price} ${product.price.currency}</span>
                    </div>
                    <hr>
                    <div class="options">
                        <div class="form-group">
                            <label class="control-label text-uppercase" for="input-quantity">Qty:</label>
                            <input type="text" name="quantity" value="1" size="2" id="input-quantity"
                                   class="form-control">
                        </div>
                        <div class="cart-button button-group">


                            <button type="button" class="btn btn-cart">
                                Add to cart
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                        </div>
                    </div>
                    <hr>
                </div>
            </div>
            <div class="product-info-box">
                <h4 class="heading">Description</h4>

                <div class="content panel-smart">
                    <p>
                        ${product.description}
                    </p>
                </div>
            </div>
            <div class="product-info-box">
                <h4 class="heading">Additional Information</h4>

                <div class="content panel-smart">
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
                        the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley
                        of type and scrambled it to make a type specimen book. It has survived not only five centuries,
                        but also the leap into electronic typesetting, remaining essentially unchanged. It was
                        popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                        and more recently with desktop publishing software like Aldus PageMaker including versions of
                        Lorem Ipsum.
                    </p>
                </div>
            </div>
            <div class="product-info-box">
            </div>
        </div>
        <div class="col-md-3">
            <h3 class="side-heading">Categories</h3>

            <div class="list-group">
                <a href="/category-grid" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Outerwear
                </a>
                <a href="/category-grid" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Jackets
                </a>
                <a href="/category-grid" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Dresses
                </a>
                <a href="/category-grid" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Activewear
                </a>
                <a href="/category-grid" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Summer Collections
                </a>
            </div>
        </div>
    </div>
    </@layout.put>
</@layout.extends>