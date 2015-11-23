<@layout.extends name="fashion-shop/layouts/base_with_footer.ftl">
    <@layout.put block="content">

        <#if checkoutOkNotification!false>
            <input id="checkout_ok_notification" value="${checkoutOkNotification?c}" hidden>
        </#if>

        <#if checkoutCancelNotification!false>
            <input id="checkout_cancel_notification" value="${checkoutCancelNotification?c!false}" hidden>
        </#if>

        <#if cartEmptyNotification!false>
            <input id="cart_empty_notification" value="${cartEmptyNotification?c!false}" hidden>
        </#if>

        <#if customerInfoNotification!false>
            <input id="customer_info_notification" value="${customerInfoNotification?c!false}" hidden>
        </#if>

    <!-- Breadcrumb Starts -->
    <ol class="breadcrumb">
        <li><a href="index.ftl">Home</a></li>
        <li class="active">Shopping Cart</li>
    </ol>
    <!-- Breadcrumb Ends -->
    <!-- Main Heading Starts -->
    <h2 class="main-heading text-center">
        Shopping Cart
    </h2>
    <!-- Main Heading Ends -->
    <!-- Shopping Cart Table Starts -->
    <div class="table-responsive shopping-cart-table">
        <table class="table table-bordered">
            <thead>
            <tr>
                <td class="text-center">
                    Image
                </td>
                <td class="text-center">
                    Product Details
                </td>
                <td class="text-center">
                    Quantity
                </td>
                <td class="text-center">
                    Price
                </td>
                <td class="text-center">
                    Total
                </td>
                <td class="text-center">
                    Action
                </td>
            </tr>
            </thead>
            <tbody>
                <#assign currency = "">

                <#if products??>
                    <#list products as product>
                    <tr>
                        <td class="text-center">
                            <a href="product.ftl">
                                <img src="${product.imageUrl}" alt="Product Name"
                                     title="Product Name"
                                     class="img-thumbnail">
                            </a>
                        </td>
                        <td class="text-center">
                            <a href="product-full.ftl">Fashion Garments</a>
                        </td>
                        <td class="text-center">
                            <div class="input-group btn-block">
                                <input type="text" name="quantity" value="${product.quantity}" size="1"
                                       class="form-control">
                            </div>
                        </td>
                        <td class="text-center">
                        ${product.price.price} ${product.price.currency}
                        </td>
                        <td class="text-center">
                            <#assign totalPrice = product.price.price * product.quantity>
                            ${totalPrice} ${product.price.currency}
                        </td>
                        <td class="text-center">
                            <button type="submit" title="" class="btn btn-default tool-tip"
                                    data-original-title="Update">
                                <i class="fa fa-refresh"></i>
                            </button>
                            <button type="button" title="" class="btn btn-default tool-tip"
                                    data-original-title="Remove">
                                <i class="fa fa-times-circle"></i>
                            </button>
                        </td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4" class="text-right">
                    <strong>Total :</strong>
                </td>
                <td colspan="2" class="text-left">
                    <#if total??>
                    ${total.price} ${total.currency}
                    <#else>
                        0 USD
                    </#if>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <!-- Shopping Cart Table Ends -->
    <!-- Shipping Section Starts -->
    <section class="registration-area">
        <div class="row">
            <!-- Shipping & Shipment Block Starts -->
            <div class="col-sm-6">
                <!-- Taxes Block Starts -->
                <div class="panel panel-smart">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Shipping &amp; Taxes
                        </h3>
                    </div>
                    <div class="panel-body">
                        <!-- Form Starts -->
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="inputCountry" class="col-sm-3 control-label">Country :</label>

                                <div class="col-sm-9">
                                    <select class="form-control" id="inputCountry">
                                        <option>- All Countries -</option>
                                        <option>India</option>
                                        <option>USA</option>
                                        <option>UK</option>
                                        <option>China</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputRegion" class="col-sm-3 control-label">Region :</label>

                                <div class="col-sm-9">
                                    <select class="form-control" id="inputRegion">
                                        <option>- All Regions -</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputZipCode" class="col-sm-3 control-label">Zip Code :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputZipCode"
                                           placeholder="Zip Code">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-default">
                                        Get Info
                                    </button>
                                </div>
                            </div>
                        </form>
                        <!-- Form Ends -->
                    </div>
                </div>
                <!-- Taxes Block Ends -->
                <!-- Shipment Information Block Starts -->
                <div class="panel panel-smart">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Shipment Information
                        </h3>
                    </div>
                    <div class="panel-body">
                        <!-- Form Starts -->
                        <form class="form-horizontal" role="form" action='/cart/saveCustomer' method="post"
                              data-toggle="validator">
                            <div class="form-group">
                                <label for="inputFname" class="col-sm-3 control-label">First Name :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputFname" name="inputFname"
                                           placeholder="First Name">
                                </div>
                            <#--<div class="help-block with-errors"></div>-->
                            </div>
                            <div class="form-group">
                                <label for="inputLname" class="col-sm-3 control-label">Last Name :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputLname" name="inputLname"
                                           placeholder="Last Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-3 control-label">Email :</label>

                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail" name="inputEmail"
                                           placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPhone" class="col-sm-3 control-label">Phone :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputPhone" name="inputPhone"
                                           placeholder="Phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputAddress1" class="col-sm-3 control-label">Address/1 :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputAddress1" name="inputAddress1"
                                           placeholder="Address/1">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputAddress2" class="col-sm-3 control-label">Address/2 :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputAddress2" name="inputAddress2"
                                           placeholder="Address/2">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCity" class="col-sm-3 control-label">City :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputCity" name="inputCity"
                                           placeholder="City">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPostCode" class="col-sm-3 control-label">Postal Code :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputPostCode" name="inputPostCode"
                                           placeholder="Postal Code">
                                </div>
                            </div>

                            <div class="form-group">
                            <#--Select Country-->
                                <label for="inputCountry" class="col-sm-3 control-label">Country :</label>

                                <div class="col-sm-9">
                                    <select class="form-control" id="inputCountry" name="inputCountry"
                                            data-placeholder="Choose a Country...">

                                        <option value=""></option>
                                        <option value="United States">United States</option>
                                        <option value="United Kingdom">United Kingdom</option>
                                        <option value="Afghanistan">Afghanistan</option>
                                        <option value="Albania">Albania</option>
                                        <option value="Algeria">Algeria</option>
                                        <option value="American Samoa">American Samoa</option>
                                        <option value="Andorra">Andorra</option>
                                        <option value="Angola">Angola</option>
                                        <option value="Anguilla">Anguilla</option>
                                        <option value="Antarctica">Antarctica</option>
                                        <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                                        <option value="Argentina">Argentina</option>
                                        <option value="Armenia">Armenia</option>
                                        <option value="Aruba">Aruba</option>
                                        <option value="Australia">Australia</option>
                                        <option value="Austria">Austria</option>
                                        <option value="Azerbaijan">Azerbaijan</option>
                                        <option value="Bahamas">Bahamas</option>
                                        <option value="Bahrain">Bahrain</option>
                                        <option value="Bangladesh">Bangladesh</option>
                                        <option value="Barbados">Barbados</option>
                                        <option value="Belarus">Belarus</option>
                                        <option value="Belgium">Belgium</option>
                                        <option value="Belize">Belize</option>
                                        <option value="Benin">Benin</option>
                                        <option value="Bermuda">Bermuda</option>
                                        <option value="Bhutan">Bhutan</option>
                                        <option value="Bolivia">Bolivia</option>
                                        <option value="Bosnia and Herzegovina">Bosnia and Herzegovina
                                        </option>
                                        <option value="Botswana">Botswana</option>
                                        <option value="Bouvet Island">Bouvet Island</option>
                                        <option value="Brazil">Brazil</option>
                                        <option value="British Indian Ocean Territory">British Indian Ocean
                                            Territory
                                        </option>
                                        <option value="Brunei Darussalam">Brunei Darussalam</option>
                                        <option value="Bulgaria">Bulgaria</option>
                                        <option value="Burkina Faso">Burkina Faso</option>
                                        <option value="Burundi">Burundi</option>
                                        <option value="Cambodia">Cambodia</option>
                                        <option value="Cameroon">Cameroon</option>
                                        <option value="Canada">Canada</option>
                                        <option value="Cape Verde">Cape Verde</option>
                                        <option value="Cayman Islands">Cayman Islands</option>
                                        <option value="Central African Republic">Central African Republic
                                        </option>
                                        <option value="Chad">Chad</option>
                                        <option value="Chile">Chile</option>
                                        <option value="China">China</option>
                                        <option value="Christmas Island">Christmas Island</option>
                                        <option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands
                                        </option>
                                        <option value="Colombia">Colombia</option>
                                        <option value="Comoros">Comoros</option>
                                        <option value="Congo">Congo</option>
                                        <option value="Congo, The Democratic Republic of The">Congo, The
                                            Democratic Republic of The
                                        </option>
                                        <option value="Cook Islands">Cook Islands</option>
                                        <option value="Costa Rica">Costa Rica</option>
                                        <option value="Cote D'ivoire">Cote D'ivoire</option>
                                        <option value="Croatia">Croatia</option>
                                        <option value="Cuba">Cuba</option>
                                        <option value="Cyprus">Cyprus</option>
                                        <option value="Czech Republic">Czech Republic</option>
                                        <option value="Denmark">Denmark</option>
                                        <option value="Djibouti">Djibouti</option>
                                        <option value="Dominica">Dominica</option>
                                        <option value="Dominican Republic">Dominican Republic</option>
                                        <option value="Ecuador">Ecuador</option>
                                        <option value="Egypt">Egypt</option>
                                        <option value="El Salvador">El Salvador</option>
                                        <option value="Equatorial Guinea">Equatorial Guinea</option>
                                        <option value="Eritrea">Eritrea</option>
                                        <option value="Estonia">Estonia</option>
                                        <option value="Ethiopia">Ethiopia</option>
                                        <option value="Falkland Islands (Malvinas)">Falkland Islands
                                            (Malvinas)
                                        </option>
                                        <option value="Faroe Islands">Faroe Islands</option>
                                        <option value="Fiji">Fiji</option>
                                        <option value="Finland">Finland</option>
                                        <option value="France">France</option>
                                        <option value="French Guiana">French Guiana</option>
                                        <option value="French Polynesia">French Polynesia</option>
                                        <option value="French Southern Territories">French Southern
                                            Territories
                                        </option>
                                        <option value="Gabon">Gabon</option>
                                        <option value="Gambia">Gambia</option>
                                        <option value="Georgia">Georgia</option>
                                        <option value="Germany">Germany</option>
                                        <option value="Ghana">Ghana</option>
                                        <option value="Gibraltar">Gibraltar</option>
                                        <option value="Greece">Greece</option>
                                        <option value="Greenland">Greenland</option>
                                        <option value="Grenada">Grenada</option>
                                        <option value="Guadeloupe">Guadeloupe</option>
                                        <option value="Guam">Guam</option>
                                        <option value="Guatemala">Guatemala</option>
                                        <option value="Guinea">Guinea</option>
                                        <option value="Guinea-bissau">Guinea-bissau</option>
                                        <option value="Guyana">Guyana</option>
                                        <option value="Haiti">Haiti</option>
                                        <option value="Heard Island and Mcdonald Islands">Heard Island and
                                            Mcdonald Islands
                                        </option>
                                        <option value="Holy See (Vatican City State)">Holy See (Vatican City
                                            State)
                                        </option>
                                        <option value="Honduras">Honduras</option>
                                        <option value="Hong Kong">Hong Kong</option>
                                        <option value="Hungary">Hungary</option>
                                        <option value="Iceland">Iceland</option>
                                        <option value="India">India</option>
                                        <option value="Indonesia">Indonesia</option>
                                        <option value="Iran, Islamic Republic of">Iran, Islamic Republic
                                            of
                                        </option>
                                        <option value="Iraq">Iraq</option>
                                        <option value="Ireland">Ireland</option>
                                        <option value="Israel">Israel</option>
                                        <option value="Italy">Italy</option>
                                        <option value="Jamaica">Jamaica</option>
                                        <option value="Japan">Japan</option>
                                        <option value="Jordan">Jordan</option>
                                        <option value="Kazakhstan">Kazakhstan</option>
                                        <option value="Kenya">Kenya</option>
                                        <option value="Kiribati">Kiribati</option>
                                        <option value="Korea, Democratic People's Republic of">Korea,
                                            Democratic People's Republic of
                                        </option>
                                        <option value="Korea, Republic of">Korea, Republic of</option>
                                        <option value="Kuwait">Kuwait</option>
                                        <option value="Kyrgyzstan">Kyrgyzstan</option>
                                        <option value="Lao People's Democratic Republic">Lao People's
                                            Democratic Republic
                                        </option>
                                        <option value="Latvia">Latvia</option>
                                        <option value="Lebanon">Lebanon</option>
                                        <option value="Lesotho">Lesotho</option>
                                        <option value="Liberia">Liberia</option>
                                        <option value="Libyan Arab Jamahiriya">Libyan Arab Jamahiriya
                                        </option>
                                        <option value="Liechtenstein">Liechtenstein</option>
                                        <option value="Lithuania">Lithuania</option>
                                        <option value="Luxembourg">Luxembourg</option>
                                        <option value="Macao">Macao</option>
                                        <option value="Macedonia, The Former Yugoslav Republic of">
                                            Macedonia, The Former Yugoslav Republic of
                                        </option>
                                        <option value="Madagascar">Madagascar</option>
                                        <option value="Malawi">Malawi</option>
                                        <option value="Malaysia">Malaysia</option>
                                        <option value="Maldives">Maldives</option>
                                        <option value="Mali">Mali</option>
                                        <option value="Malta">Malta</option>
                                        <option value="Marshall Islands">Marshall Islands</option>
                                        <option value="Martinique">Martinique</option>
                                        <option value="Mauritania">Mauritania</option>
                                        <option value="Mauritius">Mauritius</option>
                                        <option value="Mayotte">Mayotte</option>
                                        <option value="Mexico">Mexico</option>
                                        <option value="Micronesia, Federated States of">Micronesia,
                                            Federated States of
                                        </option>
                                        <option value="Moldova, Republic of">Moldova, Republic of</option>
                                        <option value="Monaco">Monaco</option>
                                        <option value="Mongolia">Mongolia</option>
                                        <option value="Montenegro">Montenegro</option>
                                        <option value="Montserrat">Montserrat</option>
                                        <option value="Morocco">Morocco</option>
                                        <option value="Mozambique">Mozambique</option>
                                        <option value="Myanmar">Myanmar</option>
                                        <option value="Namibia">Namibia</option>
                                        <option value="Nauru">Nauru</option>
                                        <option value="Nepal">Nepal</option>
                                        <option value="Netherlands">Netherlands</option>
                                        <option value="Netherlands Antilles">Netherlands Antilles</option>
                                        <option value="New Caledonia">New Caledonia</option>
                                        <option value="New Zealand">New Zealand</option>
                                        <option value="Nicaragua">Nicaragua</option>
                                        <option value="Niger">Niger</option>
                                        <option value="Nigeria">Nigeria</option>
                                        <option value="Niue">Niue</option>
                                        <option value="Norfolk Island">Norfolk Island</option>
                                        <option value="Northern Mariana Islands">Northern Mariana Islands
                                        </option>
                                        <option value="Norway">Norway</option>
                                        <option value="Oman">Oman</option>
                                        <option value="Pakistan">Pakistan</option>
                                        <option value="Palau">Palau</option>
                                        <option value="Palestinian Territory, Occupied">Palestinian
                                            Territory, Occupied
                                        </option>
                                        <option value="Panama">Panama</option>
                                        <option value="Papua New Guinea">Papua New Guinea</option>
                                        <option value="Paraguay">Paraguay</option>
                                        <option value="Peru">Peru</option>
                                        <option value="Philippines">Philippines</option>
                                        <option value="Pitcairn">Pitcairn</option>
                                        <option value="Poland">Poland</option>
                                        <option value="Portugal">Portugal</option>
                                        <option value="Puerto Rico">Puerto Rico</option>
                                        <option value="Qatar">Qatar</option>
                                        <option value="Reunion">Reunion</option>
                                        <option value="Romania">Romania</option>
                                        <option value="Russian Federation">Russian Federation</option>
                                        <option value="Rwanda">Rwanda</option>
                                        <option value="Saint Helena">Saint Helena</option>
                                        <option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
                                        <option value="Saint Lucia">Saint Lucia</option>
                                        <option value="Saint Pierre and Miquelon">Saint Pierre andMiquelon</option>
                                        <option value="Saint Vincent and The Grenadines">Saint Vincent andThe
                                            Grenadines
                                        </option>
                                        <option value="Samoa">Samoa</option>
                                        <option value="San Marino">San Marino</option>
                                        <option value="Sao Tome and Principe">Sao Tome and Principe</option>
                                        <option value="Saudi Arabia">Saudi Arabia</option>
                                        <option value="Senegal">Senegal</option>
                                        <option value="Serbia">Serbia</option>
                                        <option value="Seychelles">Seychelles</option>
                                        <option value="Sierra Leone">Sierra Leone</option>
                                        <option value="Singapore">Singapore</option>
                                        <option value="Slovakia">Slovakia</option>
                                        <option value="Slovenia">Slovenia</option>
                                        <option value="Solomon Islands">Solomon Islands</option>
                                        <option value="Somalia">Somalia</option>
                                        <option value="South Africa">South Africa</option>
                                        <option value="South Georgia and The South Sandwich Islands">SouthGeorgia
                                            and
                                            The South Sandwich
                                            Islands
                                        </option>
                                        <option value="South Sudan">South Sudan</option>
                                        <option value="Spain">Spain</option>
                                        <option value="Sri Lanka">Sri Lanka</option>
                                        <option value="Sudan">Sudan</option>
                                        <option value="Suriname">Suriname</option>
                                        <option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen</option>
                                        <option value="Swaziland">Swaziland</option>
                                        <option value="Sweden">Sweden</option>
                                        <option value="Switzerland">Switzerland</option>
                                        <option value="Syrian Arab Republic">Syrian Arab Republic</option>
                                        <option value="Taiwan, Republic of China">Taiwan, Republic ofChina</option>
                                        <option value="Tajikistan">Tajikistan</option>
                                        <option value="Tanzania, United Republic of">Tanzania, UnitedRepublic of
                                        </option>
                                        <option value="Thailand">Thailand</option>
                                        <option value="Timor-leste">Timor-leste</option>
                                        <option value="Togo">Togo</option>
                                        <option value="Tokelau">Tokelau</option>
                                        <option value="Tonga">Tonga</option>
                                        <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                                        <option value="Tunisia">Tunisia</option>
                                        <option value="Turkey">Turkey</option>
                                        <option value="Turkmenistan">Turkmenistan</option>
                                        <option value="Turks and Caicos Islands">Turks and Caicos Islands</option>
                                        <option value="Tuvalu">Tuvalu</option>
                                        <option value="Uganda">Uganda</option>
                                        <option value="Ukraine">Ukraine</option>
                                        <option value="United Arab Emirates">United Arab Emirates</option>
                                        <option value="United Kingdom">United Kingdom</option>
                                        <option value="United States">United States</option>
                                        <option value="United States Minor Outlying Islands">United StatesMinor
                                            Outlying
                                            Islands
                                        </option>
                                        <option value="Uruguay">Uruguay</option>
                                        <option value="Uzbekistan">Uzbekistan</option>
                                        <option value="Vanuatu">Vanuatu</option>
                                        <option value="Venezuela">Venezuela</option>
                                        <option value="Viet Nam">Viet Nam</option>
                                        <option value="Virgin Islands, British">Virgin Islands, British</option>
                                        <option value="Virgin Islands, U.S.">Virgin Islands, U.S.</option>
                                        <option value="Wallis and Futuna">Wallis and Futuna</option>
                                        <option value="Western Sahara">Western Sahara</option>
                                        <option value="Yemen">Yemen</option>
                                        <option value="Zambia">Zambia</option>
                                        <option value="Zimbabwe">Zimbabwe</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputRegion" class="col-sm-3 control-label">Region :</label>

                                <div class="col-sm-9">
                                    <input type="text" id="inputRegion" name="inputRegion" placeholder=""
                                           class="form-control" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-brown">
                                        Submit
                                    </button>
                                </div>
                            </div>
                        </form>
                        <!-- Form Ends -->
                    </div>
                </div>
                <!-- Shipment Information Block Ends -->
            </div>
            <!-- Shipping & Shipment Block Ends -->
            <!-- Discount & Conditions Blocks Starts -->
            <div class="col-sm-6">
                <!-- Discount Coupon Block Starts -->
                <div class="panel panel-smart">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Discount Coupon Code
                        </h3>
                    </div>
                    <div class="panel-body">
                        <!-- Form Starts -->
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="inputCouponCode" class="col-sm-3 control-label">Coupon Code :</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputCouponCode"
                                           placeholder="Coupon Code">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-default">
                                        Apply Coupon
                                    </button>
                                </div>
                            </div>
                        </form>
                        <!-- Form Ends -->
                    </div>
                </div>
                <!-- Discount Coupon Block Ends -->
                <!-- Conditions Panel Starts -->
                <div class="panel panel-smart">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Terms &amp; Conditions
                        </h3>
                    </div>
                    <div class="panel-body">
                        <p>
                            HTML Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna
                            felis sollicitudin mauris. Integer in mauris eu nibh euismod gravida. Duis ac tellus et
                            risus vulputate vehicula.
                        </p>

                        <p>
                            Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros
                            est euismod turpis, id tincidunt sapien risus a quam. Maecenas fermentum consequat mi.
                            Donec fermentum. Pellentesque malesuada nulla a mi.
                        </p>

                        <p>
                            Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque. Aliquam faucibus,
                            elit ut dictum aliquet, felis nisl adipiscing sapien, sed malesuada diam lacus eget
                            erat. Cras mollis scelerisque nunc. Nullam arcu. Aliquam consequat.
                        </p>
                    </div>
                </div>
                <!-- Conditions Panel Ends -->
                <!-- Total Panel Starts -->
                <div class="panel panel-smart">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Total
                        </h3>
                    </div>
                    <div class="panel-body">
                        <dl class="dl-horizontal">
                            <dt>Coupon Discount :</dt>
                            <dd>$-25.00</dd>
                            <dt>Subtotal :</dt>
                            <dd>$300.00</dd>
                            <dt>Payment Fee :</dt>
                            <dd>$10.00</dd>
                            <dt>Shipment Fee :</dt>
                            <dd>$15.00</dd>
                            <dt>Tax Total :</dt>
                            <dd>$315.00</dd>
                        </dl>
                        <hr>
                        <dl class="dl-horizontal total">
                            <dt>Total :</dt>

                            <#if total??>
                                <dd>${total.price} ${total.currency}</dd>
                            <#else>
                                <dd>0 USD</dd>
                            </#if>
                        </dl>
                        <hr>

                        <form action="/cart/checkout" method="get">
                            <div class="text-uppercase clearfix">
                                <a href="#" class="btn btn-default pull-left">
                                    <span class="hidden-xs">Continue Shopping</span>
                                    <span class="visible-xs">Continue</span>
                                </a>
                                <button type="submit" class="btn btn-default pull-right">
                                    Checkout
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </@layout.put>
</@layout.extends>