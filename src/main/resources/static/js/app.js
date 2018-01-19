var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngResource', 'ui.bootstrap']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/views/loginPage.html',
            controller: 'loginController'
        })
        .when('/editor', {
            templateUrl: '/views/editor.html'
        }).otherwise(
        { redirectTo: '/home'}
    );

});
