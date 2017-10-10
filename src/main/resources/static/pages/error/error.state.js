(function() {
    'use strict';

    /*** get app to regist config state ***/
    angular
        .module('app')
        .config(errorConfig);

    /*** @inject service ***/
    errorConfig.$inject = ['$stateProvider'];
    
    /**
     * define config state
     * @param $stateProvider
     * @returns
     */
    function errorConfig($stateProvider) {
        $stateProvider.state('error', {
        	url : '/error/:code',
			views: {
		        content: {
		          templateUrl: 'pages/error/error.tpl.html',
		          controller : 'ErrorCtrl'
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('error');
	                    return $translate.refresh();
	                }]
	            }
		    }
        });
    }
})();
