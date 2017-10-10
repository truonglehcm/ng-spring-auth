(function() {
	'use strict';

	/*** set modules ***/
	angular
		.module('app', [ 'ui.router', 'ngResource', 'satellizer', 'toastr', 'vcRecaptcha',
			'ui.bootstrap', 'ui.tinymce', 'underscorejs', 'angular-loading-bar', 'ui.grid.pagination',
			'ngAnimate', 'ngMessages', 'swaggerUi', 'ui.grid.cellNav', 'dibari.angular-ellipsis',
			'ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.pagination', 'headroom', 'ngSanitize']);
})();