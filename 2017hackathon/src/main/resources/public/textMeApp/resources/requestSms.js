textMeApp.factory("sendSms", ['$resource', function($resource) {
	return $resource("/api/ui:params", { number: "@params" });
}]);