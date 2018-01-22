var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngResource', 'ui.bootstrap', 'ngStorage']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/views/loginPage.html',
            controller: 'loginController'
        })
        .when('/editor', {
            templateUrl: '/views/editor.html'
        })
        .when('/scriptTest', {
            templateUrl: '/views/scriptTest.html'
        })
        .when('/logout', {
            redirectTo: '/home'
        })
        .otherwise(
        { redirectTo: '/home'}
    );

});
