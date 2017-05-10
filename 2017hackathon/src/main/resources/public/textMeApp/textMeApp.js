var myApp = angular.module('textMeApp', ['ui.router', 'ngResource']);

myApp.config(function($stateProvider, $urlRouterProvider) {
  var helloState = {
    name: 'hello',
    url: '/hello',
    templateUrl: 'textMeApp/views/hello.html'
  }

  var aboutState = {
    name: 'about',
    url: '/about',
    templateUrl: 'textMeApp/views/about.html'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(aboutState);
});