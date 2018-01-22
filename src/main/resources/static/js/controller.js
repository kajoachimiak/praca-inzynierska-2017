app.controller('scriptTestController', function ($scope, $http, sessionService, $location) {
    // if (!sessionService.isLocalUserDetailsPresent()){
    //     window.alert('Nie jesteś zalogowany');
    //     $location.path('/home');
    // }
    $scope.headingTitle = "Script Test";
    $scope.runScript = function () {
        $http({
            method: 'GET',
            url: '/scriptTest'
        }).then(function successCallback(response) {
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
});


