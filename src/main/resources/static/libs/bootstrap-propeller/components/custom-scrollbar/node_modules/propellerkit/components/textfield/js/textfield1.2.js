
// ------- Propeller pmdTextfield component js function ------- //
var pmdTextfield = function ($) {
  /**
   * ------------------------------------------------------------------------
   * Constants
   * ------------------------------------------------------------------------
   */

  var NAME = 'textfield';
  var VERSION = '1.0.0';
  var JQUERY_NO_CONFLICT = $.fn[NAME];

  var ClassName = {
    TEXTFIELD_FOCUS: 'pmd-textfield-focused',
    PMD_TEXTFIELD: 'pmd-textfield',
    TEXTFIELD_FLOATING_COMPLETE: 'pmd-textfield-floating-label-completed',
    TEXTFIELD_FLOATING_ACTIVE: 'pmd-textfield-floating-label-active'
  }

  var Selector = {
    PARENT_SELECTOR: '',
    TEXTFIELD_FOCUS: '.' + ClassName.TEXTFIELD_FOCUS,
    PMD_TEXTFIELD: '.' + ClassName.PMD_TEXTFIELD,
    TEXTFIELD_INPUT: '.pmd-textfield input.form-control',
    TEXTFIELD_FORM_CONTROL: '.pmd-textfield .form-control'
  };

  var Template = {
    TEXTFIELD_LABEL: '<span class="pmd-textfield-focused"></span>'
  }

  var Event = {
    FOCUS: 'focus',
    FOCUSOUT: 'focusout',
    CHANGE: 'change'
  }

  /**
   * ------------------------------------------------------------------------
   * Class Definition
   * ------------------------------------------------------------------------
   */

  function onFocus(e) {
    var $this = $(e.target);
    $this.closest(Selector.PMD_TEXTFIELD).addClass(ClassName.TEXTFIELD_FLOATING_ACTIVE + " " + ClassName.TEXTFIELD_FLOATING_COMPLETE);
  }

  function onFocusOut(e) {
    var $this = $(e.target);
    if ($this.val() === "") {
      $this.closest(Selector.PMD_TEXTFIELD).removeClass(ClassName.TEXTFIELD_FLOATING_COMPLETE);
    }
    $this.closest(Selector.PMD_TEXTFIELD).removeClass(ClassName.TEXTFIELD_FLOATING_ACTIVE);
  }

  function onChange(e) {
    var $this = $(e.target);
    if ($this.val() !== "") {
      $this.closest(Selector.PMD_TEXTFIELD).addClass(ClassName.TEXTFIELD_FLOATING_COMPLETE);
    }
  }


  var pmdTextfield = function () {
    _inherits(pmdTextfield, commons);

    function pmdTextfield() {
      $(pmdTextfield.prototype.attachParentSelector(Selector.PARENT_SELECTOR, Selector.TEXTFIELD_FOCUS)).remove();
      $(pmdTextfield.prototype.attachParentSelector(Selector.PARENT_SELECTOR, Selector.TEXTFIELD_FORM_CONTROL)).after(Template.TEXTFIELD_LABEL);
      $(pmdTextfield.prototype.attachParentSelector(Selector.PARENT_SELECTOR, Selector.TEXTFIELD_INPUT)).each(function () {
        if ($(this).val() !== "") {
          $(this).closest(Selector.PMD_TEXTFIELD).addClass(ClassName.TEXTFIELD_FLOATING_COMPLETE);
        }
      });
    }
    return pmdTextfield;
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
    new pmdTextfield()
  }

  $(document).on(Event.CHANGE, Selector.TEXTFIELD_INPUT, onChange);
  $(document).on(Event.FOCUS, Selector.TEXTFIELD_FORM_CONTROL, onFocus);
  $(document).on(Event.FOCUSOUT, Selector.TEXTFIELD_FORM_CONTROL, onFocusOut);

  $.fn[NAME] = plugInFunction

  return pmdTextfield
} (jQuery)()
