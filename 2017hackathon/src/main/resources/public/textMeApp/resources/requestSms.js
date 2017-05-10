textMeApp.factory("sendSms", ['$resource', function($resource) {
	return $resource("/api/sms/request/:number", { number: "@number" });
}]);