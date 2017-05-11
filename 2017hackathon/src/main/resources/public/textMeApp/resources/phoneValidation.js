textMeApp.factory("phoneValidation", ['$resource', function($resource) {
	return $resource("/api/ui:number", { number: "@number" });
}]);