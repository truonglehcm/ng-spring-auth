(function() {
	'use strict';

	/*** get module to regist filter ***/
	angular
		.module('app')
		.filter('trueFalseFilter', trueFalseFilter);
	
	function trueFalseFilter() {
		var trueFalseHash = {
			0: 'false',
			1: 'true'
		};
			 
		return function(input) {
			if (!input){
				return '';
			} else {
				return trueFalseHash[input];
			}
		};
	}
})();