(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .service('EditTagService', EditTagService);

    /*** @inject services ***/
    EditTagService.$inject = [ '$http', '$uibModal' ];
    
    /**
     * define services
     * @param $http
     * @param $uibModal
     * @returns
     */
	function EditTagService($http, $uibModal) {
		var service = {
			editTag : editTag
		};
	
		function editTag(grid, tag) {
			$uibModal.open({
				templateUrl : 'pages/management/tag/edit/tags.edit.tpl.html',
				controller 	: 'EditTagCtrl',
				resolve 	: {
					grid 	: function() { return grid; },
					tag 	: function() { return tag; }
				}
			});
		}
	
		return service;
	}
	
})();