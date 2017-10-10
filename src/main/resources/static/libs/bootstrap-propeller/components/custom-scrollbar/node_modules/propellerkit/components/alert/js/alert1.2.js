/*!
 * Propeller v1.1.0 (http://propeller.in): alert.js
 * Copyright 2016-2017 Digicorp, Inc.
 * Licensed under MIT (http://propeller.in/LICENSE)
 */

var pmdAlert = function ($) {
    /**
     * ------------------------------------------------------------------------
     * Constants
     * ------------------------------------------------------------------------
     */
    var NAME = 'pmdAlert';
    var VERSION = '1.0.0';
    var JQUERY_NO_CONFLICT = $.fn[NAME];
    var positionX = 'left';
    var positionY = 'top';
    var dataEffect = 'fadeInUp';
    var dataMessage = 'Alert Message';
    var dataType = 'information';
    var actionText = 'Ok';
    var action = 'false';
    var duration = 3000;

    var ClassName = {
        PMD_ALERT_CONTAINER: 'pmd-alert-container',
        PMD_ALERT: 'pmd-alert',
        PMD_ALERT_CLOSE: 'pmd-alert-close',
        VISIBLE: 'visible'
    };

    var Selector = {
        PARENT_SELECTOR: '.pmd-alert-toggle',
        BODY: 'body',
        PMD_ALERT_CONTAINER: '.pmd-alert-container',
        PMD_ALERT: '.pmd-alert',
        CENTER: ".center",
        PMD_ALERT_CLOSE: '.pmd-alert-close'
    };

    var Event = {
        CLICK: 'click'
    };

    /**
     * ------------------------------------------------------------------------
     * Class Definition
     * ------------------------------------------------------------------------
     */

    function getNotificationValue() {
        if (action == "true") {
            if (actionText == null) {
                return "<div class='pmd-alert' data-action='true'>" + dataMessage + "<a href='javascript:void(0)' class='pmd-alert-close'>×</a></div>";
            } else {
                return "<div class='pmd-alert' data-action='true'>" + dataMessage + "<a href='javascript:void(0)' class='pmd-alert-close'>" + actionText + "</a></div>";
            }
        } else {
            if (actionText == null) {
                return "<div class='pmd-alert' data-action='false'>" + dataMessage + "</div>";
            } else {
                return "<div class='pmd-alert' data-action='true'>" + dataMessage + "<a href='javascript:void(0)' class='pmd-alert-close'>" + actionText + "</a></div>";
            }
        }
    }

    function closeAlertNotification(e) {
        var $this = $(e.target);
        $this.parents(Selector.PMD_ALERT).slideUp(function () { $(this).removeClass(ClassName.VISIBLE).remove(); });
    }

    var pmdAlert = function () {
        function pmdAlert() {
            $(Selector.PARENT_SELECTOR).unbind("click");
            $(Selector.PARENT_SELECTOR).on("click", function () {
                positionX = $(this).attr("data-positionX");
                positionY = $(this).attr("data-positionY");
                dataEffect = $(this).attr("data-effect");
                dataMessage = $(this).attr("data-message");
                dataType = $(this).attr("data-type");
                actionText = $(this).attr("data-action-text");
                action = $(this).attr("data-action");


                if ($(window).width() < 768) {
                    positionX = "center";
                }

                if (!$(Selector.PMD_ALERT_CONTAINER + "." + positionX + "." + positionY).length) {
                    $('body').append("<div class='" + ClassName.PMD_ALERT_CONTAINER + " " + positionX + " " + positionY + "'></div>");
                }

                var currentPath = $(Selector.PMD_ALERT_CONTAINER + "." + positionX + "." + positionY);
                var notification = getNotificationValue();
                var boxLength = $(Selector.PMD_ALERT_CONTAINER + "." + positionX + "." + positionY + " " + Selector.PMD_ALERT).length;
                if ($(this).attr("data-duration") !== undefined) {
                    duration = $(this).attr("data-duration");
                }

                if (boxLength > 0 && positionY != 'top') {
                    currentPath.prepend(notification);
                } else {
                    currentPath.append(notification);
                }
                currentPath.width($(Selector.PMD_ALERT).outerWidth());
                if (action == "true") {
                    currentPath.children("[data-action='true']").addClass(Selector.VISIBLE + " " + dataEffect);
                } else {
                    currentPath.children("[data-action='false']").addClass(Selector.VISIBLE + " " + dataEffect).delay(duration).slideUp(
                        function () {
                            $(this).removeClass(Selector.VISIBLE + " " + dataEffect).remove();
                        });
                }
                currentPath.children(Selector.PMD_ALERT).eq(boxLength).addClass(dataType);
                var middle = $(Selector.PMD_ALERT).outerWidth() / 2;
                $(Selector.PMD_ALERT_CONTAINER + Selector.CENTER).css("marginLeft", "-" + middle + "px");
                $(document).on(Event.CLICK, ".pmd-alert-close", closeAlertNotification);
            });


        }

        return pmdAlert;

    } ()

    var plugInFunction = function () {
        if (this.selector !== "") {
            Selector.PARENT_SELECTOR = this.selector;
        }
        new pmdAlert();
    }

    $.fn[NAME] = plugInFunction;

    return pmdAlert;

} (jQuery)()
