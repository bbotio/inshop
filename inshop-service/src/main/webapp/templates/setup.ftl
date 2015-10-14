<@layout.extends name="layouts/base.ftl">

    <@layout.put block="content">
    <h2 class="page-title">Setup
        <small>your shop</small>
    </h2>
    <div class="row">
        <div class="col-lg-7 col-lg-offset-1">
            <section class="widget">
                <header>
                    <h4>
                        <i class="fa fa-windows"></i>
                        InShop
                    </h4>
                </header>
                <div class="body">
                    <div id="wizard" class="form-wizard">
                        <ul class="wizard-navigation nav-justified">
                            <li><a href="#tab1" data-toggle="tab"><strong>Details</strong></a></li>
                            <li><a href="#tab2" data-toggle="tab"><strong>Domain</strong></a></li>
                            <li><a href="#tab3" data-toggle="tab"><strong>View</strong></a></li>
                            <li><a href="#tab4" data-toggle="tab"><strong>Contacts</strong></a></li>
                            <li><a href="#tab5" data-toggle="tab"><strong>Payment</strong></a></li>
                            <li><a href="#tab6" data-toggle="tab"><strong>Delivery</strong></a></li>
                            <li><a href="#tab7" data-toggle="tab"><strong>Analytics</strong></a></li>
                            <li><a href="#tab8" data-toggle="tab"><strong>Thank you!</strong></a></li>
                        </ul>
                        <div id="bar" class="progress progress-xs">
                            <div class="progress-bar progress-bar-inverse"></div>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane" id="tab1">
                                <form class="form-horizontal mt-sm" action='/shop/details' method="POST">
                                    <fieldset>
                                        <div class="form-group">
                                            <!-- Shop name-->
                                            <label class="control-label col-md-3" for="shop-name">Shop name</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                    <input type="text" id="shop-name" name="shop-name" placeholder=""
                                                           class="form-control" value="${shop.title}">
                                                    <span class="help-block">Name of your shop. Will be used in shop title, logo and so on. By default instagram name.</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <!-- Shop details -->
                                            <label class="control-label col-md-3" for="shop-details">Shop
                                                details</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                        <textarea id="shop-details" name="shop-details"
                                                                  placeholder=""
                                                                  class="form-control">${shop.description}</textarea>
                                                    <span class="help-block">Details about your shop.By default instagram bio</span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <form class="form-horizontal mt-sm" action='/shop/domain' method="POST">
                                    <fieldset>
                                        <div class="form-group">
                                            <!-- Domain -->
                                            <label class="control-label col-md-3" for="domain">Domain</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                    <input type="text" id="domain" name="domain" placeholder=""
                                                           class="form-control" value="${shop.domain}">
                                                    <span class="help-block">Set domain for your shop</span>
                                                    <span class="help-block">If you would like to use you own domain name. Put your domain name in field above and add CNAME record to your domain registrant that point to <strong>shop.inshop.yt</strong></span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab3">
                                <form class="form-horizontal mt-sm" action='' method="POST">
                                    <fieldset>
                                        <div class="form-group">
                                            <!-- Template name -->
                                            <label class="control-label col-md-3" for="shop-template">Shop
                                                template</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                    <select id="shop-template" data-placeholder="Choose shop template"
                                                            class="select-block-level chzn-select">
                                                        <option value=""></option>
                                                        <option value="First template">First template</option>
                                                    </select>
                                                    <span class="help-block">Shop template</span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab4">
                                <form class="form-horizontal mt-sm" action='' method="POST">
                                    <fieldset>
                                        <div class="form-group">
                                            <!-- Address -->
                                            <label class="control-label col-md-3" for="address">Address</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                    <input type="text" id="address" name="address" placeholder=""
                                                           class="form-control">
                                                    <span class="help-block">Your address</span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab5">
                                <div class="form-group">
                                    <!-- Shop name-->
                                    <label class="control-label col-md-3"></label>

                                    <div class="col-md-8">
                                        <div class="col-md-13">
                                            <form action="/setup/payment" method="GET">
                                                <button type="submit" class="btn btn-success col-md-11">Grant permissions</button>
                                            </form>
                                            <span class="help-block">Grant permissions for PayPal Express Checkout.</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab6">
                                <form class="form-horizontal mt-sm" action='' method="POST">
                                    <fieldset>
                                        <div class="form-group">
                                            <label for="country-select" class="control-label col-md-3">Destination
                                                Country</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10"><select id="country-select"
                                                                               data-placeholder="Choose a Country..."
                                                                               class="select-block-level chzn-select">
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
                                                    <option value="Saint Pierre and Miquelon">Saint Pierre and
                                                        Miquelon
                                                    </option>
                                                    <option value="Saint Vincent and The Grenadines">Saint Vincent and
                                                        The Grenadines
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
                                                    <option value="South Georgia and The South Sandwich Islands">South
                                                        Georgia and The South Sandwich Islands
                                                    </option>
                                                    <option value="South Sudan">South Sudan</option>
                                                    <option value="Spain">Spain</option>
                                                    <option value="Sri Lanka">Sri Lanka</option>
                                                    <option value="Sudan">Sudan</option>
                                                    <option value="Suriname">Suriname</option>
                                                    <option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen
                                                    </option>
                                                    <option value="Swaziland">Swaziland</option>
                                                    <option value="Sweden">Sweden</option>
                                                    <option value="Switzerland">Switzerland</option>
                                                    <option value="Syrian Arab Republic">Syrian Arab Republic</option>
                                                    <option value="Taiwan, Republic of China">Taiwan, Republic of
                                                        China
                                                    </option>
                                                    <option value="Tajikistan">Tajikistan</option>
                                                    <option value="Tanzania, United Republic of">Tanzania, United
                                                        Republic of
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
                                                    <option value="Turks and Caicos Islands">Turks and Caicos Islands
                                                    </option>
                                                    <option value="Tuvalu">Tuvalu</option>
                                                    <option value="Uganda">Uganda</option>
                                                    <option value="Ukraine">Ukraine</option>
                                                    <option value="United Arab Emirates">United Arab Emirates</option>
                                                    <option value="United Kingdom">United Kingdom</option>
                                                    <option value="United States">United States</option>
                                                    <option value="United States Minor Outlying Islands">United States
                                                        Minor Outlying Islands
                                                    </option>
                                                    <option value="Uruguay">Uruguay</option>
                                                    <option value="Uzbekistan">Uzbekistan</option>
                                                    <option value="Vanuatu">Vanuatu</option>
                                                    <option value="Venezuela">Venezuela</option>
                                                    <option value="Viet Nam">Viet Nam</option>
                                                    <option value="Virgin Islands, British">Virgin Islands, British
                                                    </option>
                                                    <option value="Virgin Islands, U.S.">Virgin Islands, U.S.</option>
                                                    <option value="Wallis and Futuna">Wallis and Futuna</option>
                                                    <option value="Western Sahara">Western Sahara</option>
                                                    <option value="Yemen">Yemen</option>
                                                    <option value="Zambia">Zambia</option>
                                                    <option value="Zimbabwe">Zimbabwe</option>
                                                </select>
                                                    <span class="help-block pull-left">Please choose your country destination</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="courier" class="control-label col-md-3">Choose shipping
                                                option</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10"><select id="courier"
                                                                               data-placeholder="Choose an option.."
                                                                               class="select-block-level chzn-select">
                                                    <option value=""></option>
                                                    <option value="Australia Post">Australia Post</option>
                                                    <option value="DHL US">DHL US</option>
                                                    <option value="Other">Other</option>
                                                </select>
                                                    <span class="help-block pull-left">Please choose your shipping option</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3" for="destination">Destination Zip
                                                Code</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10"><input type="text" id="destination"
                                                                              name="destination" placeholder=""
                                                                              class="form-control">
                                                    <span class="help-block">Please provide your Destination Zip Code</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3" for="dest-address">Destination
                                                Address</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10"><input type="text" id="dest-address"
                                                                              name="address" placeholder=""
                                                                              class="form-control">
                                                    <span class="help-block">Please provide the destination address</span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab7">
                                <form class="form-horizontal mt-sm" action='' method="POST">
                                    <fieldset>
                                        <div class="form-group">
                                            <!-- Google Analytics -->
                                            <label class="control-label col-md-3" for="google-analytics">Google
                                                Analytics ID</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                    <input type="text" id="google-analytics" name="google-analytics"
                                                           placeholder="" class="form-control">
                                                    <span class="help-block">Add your Google Analytics ID in the field below. Create a Google Analytics account</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <!-- Shop tags -->
                                            <label class="control-label col-md-3" for="shop-tags">Shop tags</label>

                                            <div class="col-md-8">
                                                <div class="col-md-10">
                                                    <input type="text" id="shop-tags" name="shop-tags" placeholder=""
                                                           class="form-control">
                                                    <span class="help-block">Search tags for your shop</span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab8">
                                <div id="setup_tab_errors">
                                </div>
                                <p></p>
                            </div>
                            <div class="description ml mr mt-n-md">
                                <ul class="pager wizard">
                                    <li class="previous">
                                        <button class="btn btn-primary pull-left"><i class="fa fa-caret-left"></i>
                                            Previous
                                        </button>
                                    </li>
                                    <li class="next">
                                        <button class="btn btn-primary pull-right">Next <i
                                                class="fa fa-caret-right"></i></button>
                                    </li>
                                    <li class="finish" style="display: none">
                                        <button class="btn btn-success pull-right">Luanch<i class="fa fa-check"></i>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    </@layout.put>
    <@layout.put block="scripts">
    <!-- page libs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.1/select2.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.3.1/jquery.maskedinput.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.3/moment.js"></script>
    <script src="scripts/bootstrap-datetimepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap-wizard/1.2/jquery.bootstrap.wizard.js"></script>

    <!-- page application js -->
    <script src="scripts/forms-wizard.js"></script>
    </@layout.put>
</@layout.extends>
