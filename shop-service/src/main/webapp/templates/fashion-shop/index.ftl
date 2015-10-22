<@layout.extends name="fashion-shop/layouts/base_with_footer.ftl">
    <@layout.put block="content">
    <div class="row">
        <div class="col-md-9">
            <div class="product-filter">
                <div class="row">
                    <div class="col-md-4">
                        <div class="display">
                            <@layout.block name="activateButton">

                                </@layout.block>

                        </div>
                    </div>
                    <div class="col-md-2 text-right">
                        <label class="control-label">Sort</label>
                    </div>
                    <div class="col-md-3 text-right">
                        <select class="form-control">
                            <option value="default" selected="selected">Default</option>
                            <option value="NAZ">Name (A - Z)</option>
                            <option value="NZA">Name (Z - A)</option>
                        </select>
                    </div>
                    <div class="col-md-1 text-right">
                        <label class="control-label" for="input-limit">Show</label>
                    </div>
                    <div class="col-md-2 text-right">
                        <select id="input-limit" class="form-control">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3" selected="selected">3</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <@layout.block name="items">

                    </@layout.block>
            </div>
        </div>

        <div class="col-md-3">
            <h3 class="side-heading">Categories</h3>

            <div class="list-group">
                <a href="category-grid.ftl" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Outerwear
                </a>
                <a href="category-grid.ftl" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Jackets
                </a>
                <a href="category-grid.ftl" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Dresses
                </a>
                <a href="category-grid.ftl" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Activewear
                </a>
                <a href="category-grid.ftl" class="list-group-item">
                    <i class="fa fa-chevron-right"></i>
                    Summer Collections
                </a>
            </div>
        </div>
    </div>
    </@layout.put>
</@layout.extends>


