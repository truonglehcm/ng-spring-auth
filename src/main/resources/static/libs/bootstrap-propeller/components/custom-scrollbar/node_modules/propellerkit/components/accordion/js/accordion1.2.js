
/*!
 * Propeller v1.1.0 (http://propeller.in): accordion.js
 * Copyright 2016-2017 Digicorp, Inc.
 * Licensed under MIT (http://propeller.in/LICENSE)
 */

var pmdAccordion = function ($) {
    /**
    * ------------------------------------------------------------------------
    * Constants
    * ------------------------------------------------------------------------
    */
    var NAME = 'pmdAccordion';
    var VERSION = '1.0.0';
    var JQUERY_NO_CONFLICT = $.fn[NAME];

    var ClassName = {
        IN: 'in',
        ACTIVE: 'active'
    };

    var Selector = {
        PARENT_SELECTOR: 'a[data-toggle="collapse"]',
        COLLAPSE_IN: '.collapse.in',
        ACTIVE: '.active',
        PANEL: '.panel',
        EXPANDALL: '#expandAll',
        COLLAPSEALL: '#collapseAll'
    };

    var Event = {
        CLICK: 'click'
    };

    function applyCollapse(e) {
        var $this = $(e.target);
        var objectID = $this.attr('href');
        var expandable = $this.attr('data-expandable');
        var expanded = $this.attr("aria-expanded");
        var current = $this.parent().parent().parent().parent().attr("id");
        if (expandable === 'true') {
            if ($(objectID).hasClass(ClassName.IN)) {
                $(objectID).collapse('hide');
            }
            else {
                $(objectID).collapse('show');
            }
            if (expanded === "true") {
                $this.parents(Selector.PANEL).addClass(ClassName.ACTIVE);
            }
            else {
                $this.parents(Selector.PANEL).removeClass(ClassName.ACTIVE);
            }
        } else {
            if (expanded === "true") {
                $("#" + current + " " + Selector.ACTIVE).removeClass(ClassName.ACTIVE);
            }
            else {
                $("#" + current + " " + Selector.ACTIVE).removeClass(ClassName.ACTIVE);
                $this.parents(Selector.PANEL).addClass(ClassName.ACTIVE);
            }
        }
    }

    function expandAll(e) {
        var $this = $(e.target);
        var targetId = $this.attr("data-target");
        $('#' + targetId + ' ' + Selector.PARENT_SELECTOR).each(function () {
            var objectID = $this.attr('href');
            if ($(objectID).hasClass(ClassName.IN) === false) {
                $(objectID).collapse('show');
                $(objectID).parent().addClass(ClassName.ACTIVE);
            }
        });
    }

    function collapseAll(e) {
        var $this = $(e.target);
        var targetId = $this.attr("data-target");
        $('#' + targetId + ' ' + Selector.PARENT_SELECTOR).each(function () {
            var objectID = $this.attr('href');
            $(objectID).collapse('hide');
            $(objectID).parent().removeClass(ClassName.ACTIVE);
        });
    }

    var pmdAccordion = function () {
        function pmdAccordion() {
            $(Selector.COLLAPSE_IN).parents(Selector.PANEL).addClass(ClassName.ACTIVE);
            $(Selector.PARENT_SELECTOR).off(Event.CLICK);
            $(Selector.PARENT_SELECTOR).on(Event.CLICK, applyCollapse);
            $(Selector.EXPANDALL).off(Event.CLICK);
            $(Selector.EXPANDALL).on(Event.CLICK, expandAll);
            $(Selector.COLLAPSEALL).off(Event.CLICK);
            $(Selector.COLLAPSEALL).on(Event.CLICK, collapseAll);
        }
        return pmdAccordion;
    } ()

    var plugInFunction = function () {
        if (this.selector !== "") {
            Selector.PARENT_SELECTOR = this.selector;
        }
        new pmdAccordion();
    }

    $.fn[NAME] = plugInFunction;

    return pmdAccordion;

} (jQuery)()
