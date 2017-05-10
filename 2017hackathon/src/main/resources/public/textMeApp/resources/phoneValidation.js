textMeApp.factory("phoneValidation", ['$resource', function($resource) {
	return $resource("/api/validtion/phone/:number", { number: "@number" });
}]);