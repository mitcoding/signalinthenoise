var textMeApp = angular.module('textMeApp', ['ui.router', 'ngResource']);

textMeApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state({
	    name: 'home',
	    url: '/',
	    templateUrl: 'textMeApp/views/home.html'
	});
  
	$stateProvider.state({
		name: "sms",
		url:"/sms",
		controller: ["$scope", "$stateParams", function($scope, $stateParams){
	    	$scope.tel = "(310) 309 0654";
	    }]
	});
  
	$stateProvider.state({
	    name: 'sms.request',
	    url: '/request',
	    templateUrl: 'textMeApp/views/sms/request.html',
	    controller: ["$scope", "$stateParams", function($scope, $stateParams){
	    	
	    }]
	});
  
	$stateProvider.state({
	    name: 'sms.success',
	    url: '/success',
	    templateUrl: 'textMeApp/views/sms/success.html'
	});
  
	$stateProvider.state({
	    name: 'sms.error',
	    url: '/error',
	    templateUrl: 'textMeApp/views/sms/error.html',
	    params: {
	    	error: null
	    },
	    controller: ["$scope", "$stateParams", function($scope, $stateParams) {
	    	console.log($stateParams.error);
	    }]
	});
	
	$stateProvider.state({
	    name: 'validation',
	    url: '/validation'
	});
	
	$stateProvider.state({
	    name: 'validation.phone-number',
	    url: '/phone-number/{tel}',
	    controller: ['$state', '$stateParams', '$scope', function($state, $stateParams, $scope) {
	    	$scope.currentStateName = $state.current.name + ": "+ ($state.current.name === "validation.phone-number");
	    	$scope.tel = $stateParams.tel;
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
	    resolve: {
	    	phoneValidation: "phoneValidation",
	    	validationResult: function($stateParams, phoneValidation) {
	    		return phoneValidation.get({number: $stateParams.tel}).$promise.then(
	    			function(data) {
	    				console.log(["phoneValidation.success: ", data]);
	    				return data;
	    			},
	    			function(error) {
	    				console.log(["phoneValidation.error: ", error]);
	    				return {data: {} };
	    			}
	    		);
	    	}
	    },
	    controller: ["$state", "$scope", "validationResult", function($state, $scope, validationResult) {
	    	var hasError = (validationResult && validationResult.data && validationResult.data.error) ? true : false;
	    	
	    	$scope.currentStateName = $scope.currentStateName = $state.current.name + " (index): "+ ($state.current.name === "validation.phone-number");
	    	$scope.hasError = hasError;
	    	
	    	if (hasError) {
	    		$state.go("validation.phone-number.error");
	    	} else {
	    		$state.go("validation.phone-number.success");
	    	}
	    }]
	});
	
	$stateProvider.state({
	    name: 'validation.phone-number.success',
	    url: '/success',
	    templateUrl: 'textMeApp/views/validation/phone-number/success.html',
	    resolve: {
	    	sendSms: "sendSms",
	    	smsResult: function($stateParams, sendSms) {
	    		return sendSms.get({number: $stateParams.tel}).$promise.then(
	    			function(data) {
	    				console.log(["smsResult.success: ", data]);
	    				return data;
	    			},
	    			function(error) {
	    				console.log(["smsResult.error: ", error]);
	    				return error;
	    			}
	    		);
	    	}
	    },
	    controller: ["$state", "$scope", "smsResult", function($state, $scope, smsResult) {
	    	var hasError = (smsResult && smsResult.data && smsResult.data.error) ? true : false;
	    	
	    	$scope.currentStateName = $scope.currentStateName = $state.current.name + " (success): "+ ($state.current.name === "validation.phone-number");
	    	$scope.hasError = false;
	    	if (hasError) {
	    		$state.go("sms.error", { error: smsResult });
			} else {
				$state.go("sms.success");
			}
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

textMeApp.run(['$rootScope', '$state', '$stateParams', function ($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
}]);