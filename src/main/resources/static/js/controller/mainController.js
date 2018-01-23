app.controller('mainController', function ($scope, $location, sessionService, $http, $localStorage) {
    console.log('Starting mainController');
    sessionService.isUserAuthorized().then(
        function () {
            console.log('callback success');
            $localStorage.notLoggedIn = false;

            $scope.logout = function () {
                $http({
                    method: 'GET',
                    url: '/logout',
                    headers: {
                        "X-Login-Ajax-call": 'true'
                    }
                }).then(function successCallback(response) {
                    console.log(response);
                    console.log('logout ok');
                    sessionService.deleteUserDetails();
                    $location.path('/logout');
                }, function errorCallback(response) {
                    console.log(response);
                    console.log('logout error');
                });
            };
        }, function () {
            console.log('callback error');
            $localStorage.notLoggedIn = true;
            $location.path('/home');
        }
    )

});