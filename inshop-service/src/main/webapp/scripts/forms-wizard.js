$(function(){
    function pageLoad(){
        $('.chzn-select').select2();
        $("#destination").mask("99999");
        $("#credit").mask("9999-9999-9999-9999");
        $("#expiration-date").datetimepicker({
            pickTime: false
        });
        function handleTabError(tab, navigation, index) {
            var has_errors = false
            if (typeof window.setup_errors != "undefined") {
                var setup_errors_div = $("#setup_tab_errors")
                setup_errors_div.text("")
                $.each(window.setup_errors, function(tab_name, err_obj) {
                    if (err_obj.errors.length > 0) {
                        var tab_errors = $("<div class='alert alert-danger'></div>")
                        tab_errors.append($("<h4><strong>Error in tab " + tab_name + "</strong></h4>"))
                        $.each(err_obj.errors, function(_, error){
                            tab_errors.append($("<p>" + error + "</p>"))
                        })
                        setup_errors_div.append(tab_errors)
                        has_errors = true
                    }
              })
            }
            return has_errors
        }
        $("#wizard").bootstrapWizard({
            onTabShow: function(tab, navigation, index) {
                var $total = navigation.find('li').length;
                var $current = index+1;
                var $percent = ($current/$total) * 100;
                var $wizard = $("#wizard");
                $wizard.find('.progress-bar').css({width:$percent+'%'});

                if($current >= $total) {
                    $wizard.find('.pager .next').hide();
                    if (!handleTabError(tab, navigation, index)) {
                        $wizard.find('.pager .finish').show();
                        $wizard.find('.pager .finish').removeClass('disabled');
                    }
                } else {
                    $wizard.find('.pager .next').show();
                    $wizard.find('.pager .finish').hide();
                }
            },

            findTabName: function(tab) {
                return tab.find("strong").text()
            },

            findTabId: function(tab) {
                return tab.children().attr("href")
            },

            findForm: function(tab) {
                return $(this.findTabId(tab)).children("form")
            },

            makeQuery: function(tab) {
                tab_form = this.findForm(tab)
                endpoint = tab_form.attr("action")
                return $.post(endpoint, tab_form.serialize())
            },

            onTabChange: function(tab, navigation, index) {
                var $total = navigation.find('li').length;
                var $current = index+1;
                if (tab.length > 0 && $total != $current) {
                    req = this.makeQuery(tab)
                    var tab_name = this.findTabName(tab)
                    var tab_id = this.findTabId(tab)
                    req.done(function(resp){
                        if (typeof window.setup_errors == "undefined") {
                            window.setup_errors = {}
                        }
    
                        if (resp.response.errors.length > 0) {
                            window.setup_errors[tab_name] = {"tab_id": tab_id, "errors": resp.response.errors}
                        } else {
                            delete window.setup_errors[tab_name]
                        }
                        if (resp.status == 'ERROR') {
                            // show errors notifications
                        }

                    })
                }
            },
            onLast: function(tab, navigation, index) {
                if (Object.keys(window.setup_errors).length == 0 
                        || typeof window.setup_errors == "undefined") {
                    tab_form = this.findForm(tab)
                    tab_form.submit()
                }
            }, 
        });

        $('.widget').widgster();
    }

    pageLoad();

});
