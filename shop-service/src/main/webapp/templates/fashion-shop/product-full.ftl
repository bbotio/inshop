<@layout.extends name="fashion-shop/layouts/base_with_footer.ftl">
    <@layout.put block="content">
    <div class="row product-info full">
        <!-- Left Starts -->
        <div class="col-sm-4 images-block">
            <a href="/images/fashion-shop/product-images/14.jpg">
                <img src="/images/fashion-shop/product-images/14.jpg" alt="Image" class="img-responsive thumbnail">
            </a>

        </div>
        <!-- Left Ends -->
        <!-- Right Starts -->
        <div class="col-sm-8 product-details">
            <div class="panel-smart">
                <!-- Product Name Starts -->
                <h2>Fashion Garments</h2>
                <!-- Product Name Ends -->
                <hr>
                <!-- Manufacturer Starts -->
                <ul class="list-unstyled manufacturer">
                    <li>
                        <span>Brand:</span> Indian spices
                    </li>
                    <li><span>Model:</span> SKU012452</li>
                    <li><span>Reward Points:</span> 300</li>
                    <li>
                        <span>Availability:</span> <strong class="label label-danger">Out Of Stock</strong>
                    </li>
                </ul>
                <!-- Manufacturer Ends -->
                <hr>
                <!-- Price Starts -->
                <div class="price">
                    <span class="price-head">Price :</span>
                    <span class="price-new">$199.50</span>
                    <span class="price-old">$249.50</span>
                    <p class="price-tax">Ex Tax: $279.99</p>
                </div>
                <!-- Price Ends -->
                <hr>
                <!-- Available Options Starts -->
                <div class="options">
                    <h3>Available Options</h3>
                    <div class="form-group">
                        <label for="select" class="control-label text-uppercase">Select:</label>
                        <select name="select" id="select" class="form-control">
                            <option value="1" selected="">Select</option>
                            <option value="2">Option 1</option>
                            <option value="3">Option 1</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label text-uppercase">Radio:</label>
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
                                Option one is this and that—be sure to include why it's great
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                Option two can be something else and selecting it will deselect option one
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label text-uppercase">Checkbox:</label>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                Option one is this and that—be sure to include why it's great
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" checked="">
                                Option two is checked
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label text-uppercase" for="input-quantity">Qty:</label>
                        <input type="text" name="quantity" value="1" size="2" id="input-quantity" class="form-control">
                    </div>
                    <div class="cart-button button-group">


                        <button type="button" class="btn btn-cart">
                            Add to cart
                            <i class="fa fa-shopping-cart"></i>
                        </button>
                    </div>
                </div>
                <!-- Available Options Ends -->
            </div>
        </div>
        <!-- Right Ends -->
    </div>
    <!-- Product Info Ends -->
    <!-- Tabs Starts -->
    <div class="tabs-panel panel-smart">
        <!-- Nav Tabs Starts -->
        <ul class="nav nav-tabs">
            <li class="">
                <a href="#tab-description">Description</a>
            </li>
            <li class="">
                <a href="#tab-specification">Specification</a>
            </li>
            <li class=""><a href="#tab-ainfo">Additional Information</a></li>
            <li class="active"><a href="#tab-review">Review</a></li>
        </ul>
        <!-- Nav Tabs Ends -->
        <!-- Tab Content Starts -->
        <div class="tab-content clearfix">
            <!-- Description Starts -->
            <div class="tab-pane" id="tab-description">
                <p>
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                </p>
                <p>
                    It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                </p>
            </div>
            <!-- Description Ends -->
            <!-- Specification Starts -->
            <div class="tab-pane" id="tab-specification">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <td colspan="2"><strong>Name</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Name</td>
                        <td>Attribute Specification</td>
                    </tr>
                    </tbody>
                </table>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <td colspan="2"><strong>Name</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Name</td>
                        <td>Attribute Specification</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- Specification Ends -->
            <!-- Additional Information Starts -->
            <div class="tab-pane" id="tab-ainfo">
                <p>
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                </p>
            </div>
            <!-- Additional Information Ends -->
            <!-- Review Starts -->
            <div class="tab-pane active" id="tab-review">
                <form class="form-horizontal">
                    <div class="form-group required">
                        <label class="col-sm-2 control-label" for="input-name">Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" value="" id="input-name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group required">
                        <label class="col-sm-2 control-label" for="input-review">Review</label>
                        <div class="col-sm-10">
                            <textarea name="text" rows="5" id="input-review" class="form-control"></textarea>
                            <div class="help-block">
                                Some note goes here..
                            </div>
                        </div>
                    </div>
                    <div class="form-group required">
                        <label class="col-sm-2 control-label ratings">Ratings</label>
                        <div class="col-sm-10">
                            Bad&nbsp;
                            <input type="radio" name="rating" value="1">
                            &nbsp;
                            <input type="radio" name="rating" value="2">
                            &nbsp;
                            <input type="radio" name="rating" value="3">
                            &nbsp;
                            <input type="radio" name="rating" value="4">
                            &nbsp;
                            <input type="radio" name="rating" value="5">
                            &nbsp;Good
                        </div>
                    </div>
                    <div class="buttons">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" id="button-review" class="btn btn-brown">
                                Submit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- Review Ends -->
        </div>
        <!-- Tab Content Ends -->
    </div>
    </@layout.put>
</@layout.extends>