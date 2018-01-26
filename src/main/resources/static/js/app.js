var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngResource','ngAnimate','ngTouch', 'ui.bootstrap', 'ngStorage', 'ui.tree']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/views/loginPage.html',
            controller: 'loginController'
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
