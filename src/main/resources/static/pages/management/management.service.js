(function() {
    'use strict';

    angular
        .module('app')
        .factory('ManagementService', ManagementService);

    ManagementService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    function ManagementService($http, $q, $log, $auth, CONFIG) {
    	var services = {
    		// todo	
    	}
    	
    	return services;
    }
    
})();
