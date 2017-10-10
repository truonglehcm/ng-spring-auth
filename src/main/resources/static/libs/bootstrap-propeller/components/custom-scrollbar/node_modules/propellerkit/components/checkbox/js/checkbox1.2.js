// ------- Propeller Checkbox component js function ------- //
var pmdInputCheckBox = function ($) {
  /**
   * ------------------------------------------------------------------------
   * Constants
   * ------------------------------------------------------------------------
   */

  var NAME = 'inputCheckBox';
  var VERSION = '1.0.0';
  var JQUERY_NO_CONFLICT = $.fn[NAME];

  var ClassName = {
    ANIMATE: 'animate',
    PMD_CHECKBOX: 'pmd-checkbox',
    TEXTFIELD_FOCUS: 'pmd-textfield-focused'
  }

  var Selector = {
    PARENT_SELECTOR: '',
    PMD_CHECKBOX_INPUT: 'input:checkbox:not(.pm-ini)',
    PMD_CHECKBOX_RIPPLE: '.pmd-checkbox-ripple-effect',
    INK: '.ink'
  };

  var Template = {
    CHECK_BOX_LABEL: '<span class="pmd-checkbox-label">&nbsp;</span>',
    SPAN_LINK: '<span class="ink"></span>'
  }

  var Event = {
    CLICK: 'click',
    MOUSE_DOWN: 'mousedown'
  }

  /**
   * ------------------------------------------------------------------------
   * Class Definition
   * ------------------------------------------------------------------------
   */

  function onMouseDown(e) {
    var $this = $(e.target);
    var rippler = $this;
    $(Selector.INK).remove();
    // create .ink element if it doesn't exist
    if (rippler.find(Selector.INK).length === 0) {
      rippler.append(Template.SPAN_LINK);
    }
    var ink = rippler.find(Selector.INK);
    // prevent quick double clicks
    ink.removeClass(ClassName.ANIMATE);
    // set .ink diametr
    if (!ink.height() && !ink.width()) {
      var d = Math.max(rippler.outerWidth(), rippler.outerHeight());
      ink.css({
        height: 20,
        width: 20
      });
    }
    // get click coordinates
    var x = e.pageX - rippler.offset().left - ink.width() / 2;
    var y = e.pageY - rippler.offset().top - ink.height() / 2;
    // set .ink position and add class .animate
    ink.css({
      top: y + 'px',
      left: x + 'px'
    }).addClass(ClassName.ANIMATE);
    setTimeout(function () {
      ink.remove();
    }, 1500);
  }


  var pmdInputCheckBox = function () {
    _inherits(pmdInputCheckBox, commons);

    function pmdInputCheckBox() {
      var finalSelector = pmdInputCheckBox.prototype.attachParentSelector(Selector.PARENT_SELECTOR, Selector.PMD_CHECKBOX_INPUT);
      $(finalSelector).after(Template.CHECK_BOX_LABEL);
      $(finalSelector).addClass("pm-ini");
    }
    return pmdInputCheckBox;
  } ()


  /**
   * ------------------------------------------------------------------------
   * jQuery
   * ------------------------------------------------------------------------
   */
  var plugInFunction = function () {
    if (this.selector !== "") {
      Selector.PARENT_SELECTOR = this.selector;
    }
    new pmdInputCheckBox()
  }

  $(document).on(Event.MOUSE_DOWN, Selector.PMD_CHECKBOX_RIPPLE, onMouseDown);
  $.fn[NAME] = plugInFunction

  return pmdInputCheckBox
} (jQuery)()
