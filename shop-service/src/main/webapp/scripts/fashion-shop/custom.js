//MAGNIFIC POPUP
$(document).ready(function () {
    $('.images-block').magnificPopup({
        delegate: 'a',
        type: 'image',
        gallery: {
            enabled: true
        }
    });

    var notifications = {'checkout_ok_notification' : ['glyphicon glyphicon-ok', 'Congratulations! Payment completed!', 'success'],
                         'checkout_cancel_notification' : ['glyphicon glyphicon-exclamation-sign', 'You\'ve cancelled the payment.', 'danger'],
                         'cart_empty_notification' : ['glyphicon glyphicon-info-sign', 'Your cart is empty.', 'warning'],
                         'customer_info_notification' : ['glyphicon glyphicon-info-sign', 'Please, fill shipment info.', 'warning']};

    for(var notification in notifications) {
        if($('#' + notification).val() == 'true') {
            $.notify(
                {
                    icon: notifications[notification][0],
                    message: notifications[notification][1]
                },
                {
                    type: notifications[notification][2],
                    allow_dismiss: false,
                    newest_on_top: true,
                    placement: {
                        from: 'top', align: 'center'
                    },
                    delay: 2000,
                    timer: 1000
                });
        }
    }
});

(function ($) {

    "use strict";

    // TOOLTIP
    $(".header-links .fa, .tool-tip").tooltip({
        placement: "bottom"
    });
    $(".btn-wishlist, .btn-compare, .display .fa").tooltip('hide');

    // TABS
    $('.nav-tabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

})(jQuery);