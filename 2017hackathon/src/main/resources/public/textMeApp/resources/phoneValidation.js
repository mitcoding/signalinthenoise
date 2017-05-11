textMeApp.factory("phoneValidation", ['$resource', function($resource) {
	return $resource("/validation/phone:params", { number: "@params" });
}]);