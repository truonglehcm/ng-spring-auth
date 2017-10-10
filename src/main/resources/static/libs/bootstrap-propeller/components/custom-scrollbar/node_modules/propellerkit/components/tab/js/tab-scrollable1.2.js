/*!
 * Propeller v1.1.0 (http://propeller.in): tab.js
 * Copyright 2016-2017 Digicorp, Inc.
 * Licensed under MIT (http://propeller.in/LICENSE)
 */

var pmdTab = function ($) {

    /**
    * ------------------------------------------------------------------------
    * Constants
    * ------------------------------------------------------------------------
    */

    var NAME = 'pmdTab';
    var VERSION = '1.0.0';
    var JQUERY_NO_CONFLICT = $.fn[NAME];

    var ClassName = {
        NAV_JUSTIFIED: 'nav-justified',
        PREV_TAB: 'prev-tab',
        LAST_TAB: 'last-tab'
    }

    var Selector = {
        PARENT_SELECTOR: '.pmd-tabs',
        UL_NAV_TABS: 'ul.nav-tabs',
        LI: 'li',
        PMD_TAB_SCROLL_CONTAINER: '.pmd-tabs-scroll-container',
        NAV_TAB: '.nav-tabs',
        PMD_TABS_SCROLL_RIGHT: '.pmd-tabs-scroll-right',
        PMD_TABS_SCROLL_LEFT: '.pmd-tabs-scroll-left',
        UL_LI_ACTIVE: 'ul li.active',
        PMD_TAB_ACTIVE_BAR: '.pmd-tab-active-bar',
        NAV: '.nav',
        UL_LI: 'ul li',
        NAV_TABS_LI: '.nav-tabs li',
        LAST_TAB: '.last-tab',
        PREV_TAB: '.prev-tab'
    };

    function widthOfList($this) {
        var itemsWidth = 0;
        $this.find(Selector.LI).each(function () {
            var itemWidth = $(this)[0].getBoundingClientRect().width;
            itemsWidth += itemWidth;
        });
        return itemsWidth;
    }

    function appendulwidth($this) {
        if ($this.find(Selector.UL_NAV_TABS).hasClass(ClassName.NAV_JUSTIFIED)) {
            $this.find(Selector.UL_NAV_TABS).width("100%")
        } else {
            $this.find(Selector.UL_NAV_TABS).width(widthOfList($this))
        }
    }

    function getLeftPosi($this) {
        return $this.find(Selector.UL_NAV_TABS).position().left;
    }

    function reAdjust($this) {
        if (($this.outerWidth()) < widthOfList($this)) {

            var navScrolledRight = $this.find(Selector.PMD_TAB_SCROLL_CONTAINER).scrollLeft(),
                navWrapWidth = $this.width(),
                navWidth = $this.find(Selector.NAV_TAB).width(),
                ammountRight = navWidth - navScrolledRight - navWrapWidth;

            if (ammountRight > 0) {
                $this.find(Selector.PMD_TABS_SCROLL_RIGHT).show();
            }
        }
        else {
            $this.find(Selector.PMD_TABS_SCROLL_RIGHT).hide();
        }
        if (getLeftPosi($this) < 0) {
            var navScrolledLeft = $this.find(Selector.PMD_TAB_SCROLL_CONTAINER).scrollLeft(),
                ammountLeft = navScrolledLeft;
            if (ammountLeft > 0) {
                $this.find(Selector.PMD_TABS_SCROLL_LEFT).show();
            }
        }
        else {
            $this.find(Selector.PMD_TABS_SCROLL_LEFT).hide();
        }
    }

    function activeTabCenter($this) {
        var $tabWidth = $this.outerWidth(),
            $middlePosition = $tabWidth / 2,
            $tabWrapperLeft = $this.offset().left,
            $sliderActive = $this.find(Selector.UL_LI_ACTIVE),
            $activeWidth = $sliderActive.outerWidth(),
            $tabHalfWidth = $activeWidth / 2,
            $tableftScroll = $this.find(Selector.PMD_TAB_SCROLL_CONTAINER).scrollLeft(),
            $tableftPosi = $this.find(Selector.UL_LI_ACTIVE).offset().left,
            $tabCenterPosi = $tableftPosi - $middlePosition - $tabWrapperLeft + $tableftScroll + $tabHalfWidth;
        $this.find(Selector.PMD_TAB_SCROLL_CONTAINER).animate({ scrollLeft: $tabCenterPosi }, 1);
    }


    function sliderLoad($this) {
        var $slider = $this.find(Selector.PMD_TAB_ACTIVE_BAR),
            $sliderActive = $this.find(Selector.UL_LI_ACTIVE),
            $isX = $sliderActive.offset().left,
            $navX = $this.find(Selector.NAV).offset().left,
            $wrapperLeft = $this.offset().left,
            $sliderLeft = $isX - $wrapperLeft,
            $finalPossion = $wrapperLeft - $navX + $isX - $wrapperLeft;

        if ($navX < $wrapperLeft) {
            $slider.width($sliderActive.width() + "px").css("left", $finalPossion + "px");
        } else {
            $slider.width($sliderActive.width() + "px").css("left", $sliderLeft + "px");
        }
        $this.find(Selector.UL_LI).click(function () {
            var $thisWidth = $(this).width() + "px",
                $newLeft = $(this).offset().left - $wrapperLeft,
                $navX = $(this).closest(Selector.NAV).offset().left;
            $finalPossion = $wrapperLeft - $navX + $newLeft;

            $slider.width($thisWidth).css("left", $finalPossion + "px");
        });
    }


    function onResizeWindow(event) {
        var $this = event.data.param1;
        setTimeout(function () {
            appendulwidth($this);
            reAdjust($this);
            activeTabCenter($this);
        }, 150);

        sliderLoad($this);
    }

    function onPmdTabScrollRightClick(event) {
        var $this = event.data.param1;
        var $tabSet = '',
            $wrapper = $(event.currentTarget).prev(".pmd-tabs-scroll-container"),
            $tab = $wrapper.find(Selector.NAV_TABS_LI),
            $thisWidht = $(event.currentTarget).outerWidth(),
            $navCotainer = $this.outerWidth(),
            $wrapperRight = $this.offset().left + $navCotainer;

        $tab.each(function () {
            var SuspectTabLeft = $(this).offset().left;
            var SuspectTabRight = $(this).offset().left + $(this).outerWidth();
            $(this).removeClass(ClassName.PREV_TAB);
            if (SuspectTabLeft < $wrapperRight && SuspectTabRight > $wrapperRight) {
                $tabSet = SuspectTabRight - $wrapperRight + $thisWidht;
                $(this).addClass(ClassName.LAST_TAB);
                $(this).prev().removeClass(ClassName.LAST_TAB);
            }
        });
        var finalTab = $wrapper.find(Selector.LAST_TAB).next().length;
        if (finalTab === 0) {
            var lastTabRight = $wrapper.find(Selector.LAST_TAB).offset().left + $wrapper.find(Selector.LAST_TAB).outerWidth();
            var NewScrollAmount = lastTabRight - $wrapperRight;
            $wrapper.animate({ scrollLeft: '+=' + NewScrollAmount })
            $(event.currentTarget).fadeOut('slow');
        }
        else {
            $wrapper.animate({ scrollLeft: '+=' + $tabSet });
        }
        $(event.currentTarget).parents(Selector.PARENT_SELECTOR).find(Selector.PMD_TABS_SCROLL_LEFT).fadeIn('slow');
    }

    function onPmdTabScrollLeftClick(event) {
        var $this = event.data.param1;
        var $wrapper = $(event.currentTarget).next(".pmd-tabs-scroll-container"),
            $tab = $wrapper.find(Selector.NAV_TABS_LI),
            $thisWidht = $(event.currentTarget).outerWidth(),
            $wrapperLeft = $this.offset().left,
            $tabSetLeft = '';

        $tab.each(function () {
            var SuspectTabLeft = $(this).offset().left;
            var SuspectTabRight = $(this).offset().left + $(this).outerWidth();
            $(this).removeClass(ClassName.LAST_TAB);
            if (SuspectTabLeft < $wrapperLeft && SuspectTabRight > $wrapperLeft) {
                $tabSetLeft = $wrapperLeft - SuspectTabLeft + $thisWidht;
                $(this).addClass(ClassName.PREV_TAB);
                $(this).next().removeClass(ClassName.PREV_TAB);
            }
        });
        var finalTab = $wrapper.find(Selector.PREV_TAB).prev().length;

        if (finalTab === 0) {
            var lastTableft = $wrapper.find(Selector.PREV_TAB).offset().left;
            var NewScrollAmount = $wrapperLeft - lastTableft;
            $wrapper.animate({ scrollLeft: '-=' + NewScrollAmount })
            $(event.currentTarget).fadeOut('slow');
        }
        else {
            $wrapper.animate({ scrollLeft: '-=' + $tabSetLeft });
        }
        $(event.currentTarget).parents(Selector.PARENT_SELECTOR).find(Selector.PMD_TABS_SCROLL_RIGHT).fadeIn('slow');
    }

    function onUlLiClick(event) {
        var $this = event.data.param1;
        var $wrapper = $(event.target).closest(".pmd-tabs-scroll-container");
        //setTabActive()

        var activeLeft = $(event.target).offset().left;
        var activeRight = $(event.target).offset().left + $(event.target).outerWidth();
        var $navCotainer = $this.outerWidth();
        var $wrapperRight = $this.offset().left + $navCotainer;
        var $buttonWidth = $(Selector.PMD_TABS_SCROLL_RIGHT).outerWidth();
        var $wrapperLeft = $this.offset().left;
        var cuttRight = $wrapperRight - $buttonWidth;
        var cuttleft = $wrapperLeft + $buttonWidth;
        if (activeLeft < cuttleft && activeRight > cuttleft) {

            var setLeft = $wrapperLeft - activeLeft + $buttonWidth;
            $wrapper.animate({ scrollLeft: '-=' + setLeft });
            $(event.target).parents(Selector.PARENT_SELECTOR).find(Selector.PMD_TABS_SCROLL_RIGHT).fadeIn('slow');
        }
        if (activeLeft < cuttRight && activeRight > cuttRight) {
            var setRight = activeRight - $wrapperRight + $buttonWidth;
            $wrapper.animate({ scrollLeft: '+=' + setRight });
            $(event.target).parents(Selector.PARENT_SELECTOR).find(Selector.PMD_TABS_SCROLL_LEFT).fadeIn('slow');
        }
    }

    var pmdTab = function () {
        // _inherits(pmdTab, commons);
        function pmdTab() {
            $(Selector.PARENT_SELECTOR).each(function () {
                var $this = $(this);
                appendulwidth($this);
                reAdjust($this);
                activeTabCenter($this);
                sliderLoad($this);
                $this.find(Selector.PMD_TABS_SCROLL_RIGHT).click({ param1: $this }, onPmdTabScrollRightClick);
                $this.find(Selector.PMD_TABS_SCROLL_LEFT).click({ param1: $this }, onPmdTabScrollLeftClick);
                $this.find(Selector.UL_LI).click({ param1: $this }, onUlLiClick);
                $(window).resize({ param1: $this }, onResizeWindow);
            });
        }
        return pmdTab;
    } ()

    var plugInFunction = function () {
        if (this.selector !== "") {
            Selector.PARENT_SELECTOR = this.selector;
        }
        new pmdTab();
    }

    $.fn[NAME] = plugInFunction

    return pmdTab;
} (jQuery)()
