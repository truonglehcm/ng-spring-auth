(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('FooterController', FooterController);
	
	/*** @inject services ***/
	FooterController.$inject = ['$scope',  '$state'];
	
	/**
	 * define controller
	 * @param $scope
	 * @param $state
	 * @returns
	 */
	function FooterController($scope, $state) {
		// todo
	}
}());