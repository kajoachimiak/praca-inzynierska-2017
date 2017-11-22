var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/editor',{
            templateUrl: '/views/editor.html',
            controller: 'editorController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

var simpleMde = angular.module('myApp', ['uiSimplemde']);


