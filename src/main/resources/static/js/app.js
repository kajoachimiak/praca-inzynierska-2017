var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/scriptTest',{
            templateUrl: '/views/scriptTest.html',
            controller: 'scriptTestController'
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


