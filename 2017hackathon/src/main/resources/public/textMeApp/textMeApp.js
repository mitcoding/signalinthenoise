var myApp = angular.module('textMeApp', ['ui.router', 'ngResource']);

myApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state({
	    name: 'home',
	    url: '/',
	    templateUrl: 'textMeApp/views/home.html'
	});
  
	$stateProvider.state({
		name: "sms",
		url:"/sms"
	});
  
	$stateProvider.state({
	    name: 'sms.request',
	    url: '/request',
	    templateUrl: 'textMeApp/views/sms/request.html'
	});
  
	$stateProvider.state({
	    name: 'sms.success',
	    url: '/success',
	    templateUrl: 'textMeApp/views/sms/success.html'
	});
  
	$stateProvider.state({
	    name: 'sms.error',
	    url: '/error',
	    templateUrl: 'textMeApp/views/sms/error.html'
	});
	
	$stateProvider.state({
	    name: 'validation',
	    url: '/validation'
	});
	
	$stateProvider.state({
	    name: 'validation.phone-number',
	    url: '/phone-number',
	    controller: ['$state', '$scope', function($state, $scope) {
	    	$scope.currentStateName = $state.current.name + ": "+ ($state.current.name === "validation.phone-number");
	    	$scope.hasError = false;

	    	if ($state.current.name === "validation.phone-number") {
	    		$state.go('validation.phone-number.index');
	    	}
	    }]
	});

	$stateProvider.state({
	    name: 'validation.phone-number.index',
	    url: '/index',
	    templateUrl: 'textMeApp/views/validation/phone-number/index.html',
	    controller: ["$state", "$scope", function($state, $scope) {
	    	$scope.currentStateName = $scope.currentStateName = $state.current.name + " (index): "+ ($state.current.name === "validation.phone-number");
	    	$scope.hasError = false;
	    }]
	});
	
	$stateProvider.state({
	    name: 'validation.phone-number.success',
	    url: '/success',
	    templateUrl: 'textMeApp/views/validation/phone-number/success.html',
	    controller: ["$state", "$scope", function($state, $scope) {
	    	$scope.currentStateName = $scope.currentStateName = $state.current.name + " (success): "+ ($state.current.name === "validation.phone-number");
	    	$scope.hasError = false;
	    }]
	});
	
	$stateProvider.state({
	    name: 'validation.phone-number.error',
	    url: '/error',
	    templateUrl: 'textMeApp/views/validation/phone-number/error.html',
	    controller: ["$state", "$scope", function($state, $scope) {
	    	$scope.currentStateName = $scope.currentStateName = $state.current.name + " (error): "+ ($state.current.name === "validation.phone-number");
	    	
	    	$scope.hasError = true;
	    }]
	});

	
	$urlRouterProvider.otherwise("/");
});

myApp.run(['$rootScope', '$state', '$stateParams', function ($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
}]);