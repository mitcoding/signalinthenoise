textMeApp.factory("sendSms", ['$resource', function($resource) {
	return $resource("/api/ui:number", { number: "@number" });
}]);