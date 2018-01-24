var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngResource','ngAnimate','ngTouch', 'ui.bootstrap', 'ngStorage', 'ui.tree']);
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
        .when('/mainPage', {
            templateUrl: '/views/mainPage.html'
        })
        .otherwise(
        { redirectTo: '/home'}
    );

});
