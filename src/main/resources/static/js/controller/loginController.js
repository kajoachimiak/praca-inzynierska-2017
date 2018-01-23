app.controller('loginController', function ($scope, $http, $location,$localStorage) {
    console.log("Starting loginController");

    $scope.showLogInError = false;
    // var showNotLoggedInError = $localStorage.showNotLoggedInError;

    $scope.vm = {
        submitted: false,
        errorMessages: []
    };
    $scope.credentials = {};
    $scope.submitLoginForm = function () {

        console.log("Starting submitLoginForm");
        $scope.vm.submitted = true;

        // if ($scope.form.$invalid) {
        //     return;
        // }
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
            // $.param($scope.credentials)
        }).then(function successCallback(response) {
            console.log(response);
            console.log('log in ok');
            $scope.showLogInError = false;
            $location.path('/mainPage');
        }, function errorCallback(response) {
            console.log(response);
            console.log('log in error');
            $scope.showLogInError = true;
            $scope.vm.errorMessages = [];
            $scope.vm.errorMessages.push({description: 'Nieprawidłowy login lub hasło!'});
        });
    };
});