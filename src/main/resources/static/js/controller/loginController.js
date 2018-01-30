app.controller('loginController', function ($scope, $http, $location, sessionService, $translate) {
    console.log("Starting loginController");
    sessionService.deleteUserDetails();

    $scope.changeLanguageLogin = function (langKey) {
        $translate.use(langKey);
    };

    $scope.credentials = {};

    $scope.showLogInError = false;

    $scope.submitLoginForm = function () {
        console.log("Starting submitLoginForm");
        console.log($scope.credentials);
        $scope.preparePostData = function () {
            return 'username=' + $scope.credentials.username + '&password=' + $scope.credentials.password;
        };
        var postData = $scope.preparePostData();
        $http({
            method: 'POST',
            url: '/authenticate',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Login-Ajax-call': 'true'
            },
            data: postData
        }).then(function successCallback(response) {
            console.log(response);
            console.log('log in ok');
            $scope.showLogInError = false;
            $location.path('/mainPage');
        }, function errorCallback(response) {
            console.log(response);
            console.log('log in error');
            $scope.showLogInError = true;
        });
    };
});