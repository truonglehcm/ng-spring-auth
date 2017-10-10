(function() {
    'use strict';

    /*** get app to regist services ***/
    angular
        .module('app')
        .service('EditUserService', EditUserService);

    /*** @inject ***/
    EditUserService.$inject = [ '$http', '$uibModal' ];
    
    /**
     * define services
     * @param $http
     * @param $uibModal
     * @returns
     */
	function EditUserService($http, $uibModal) {
		var service = {
			editUser : editUser
		};
	
		function editUser(grid, user) {
			$uibModal.open({
				templateUrl : 'pages/management/user/edit/users.edit.tpl.html',
				controller 	: 'EditUserCtrl',
				resolve 	: {
					grid 	: function() { return grid; },
					user 	: function() { return user; }
				}
			});
		}
	
		return service;
	}
	
})();