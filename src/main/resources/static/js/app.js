var app = angular.module('app', ['ngRoute','ngResource', 'ngSanitize']);
app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/markdown',{
            templateUrl: '/views/markdown.html',
            controller: 'markdownController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

