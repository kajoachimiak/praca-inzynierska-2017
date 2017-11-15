var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/roles',{
            templateUrl: '/views/editor.html',
            controller: 'editorController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

var simpleMde = angular.module('myApp', ['uiSimplemde']);