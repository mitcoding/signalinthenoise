textMeApp.factory("phoneValidation", ['$resource', function($resource) {
	return $resource("/api/ui:params", { number: "@params" });
}]);