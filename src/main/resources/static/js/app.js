var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngResource', 'ui.bootstrap']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/scriptTest', {
            templateUrl: '/views/scriptTest.html',
            controller: 'scriptTestController'
        })
        .when('/editor', {
            templateUrl: '/views/editor.html',
            controller: 'editorController'
        })
        .otherwise(
            {redirectTo: '/'}
        );

});
app.run(function run($http, $cookies){
    $http.defaults.headers.post['X-CSRFToken'] = $cookies['csrftoken'];

});