var myApp = angular.module('textMeApp', ['ui.router']);

myApp.config(function($stateProvider, $urlRouterProvider) {
  var helloState = {
    name: 'hello',
    url: '/hello',
    template: '<h3>Hello World</h3>'
  }

  var aboutState = {
    name: 'about',
    url: '/about',
    template: '<h3>About Us</h3>'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(aboutState);
});