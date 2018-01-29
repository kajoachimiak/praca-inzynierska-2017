app.service('sessionService', function ($localStorage, $http, $q) {
    var sessionService = this;
    this.saveUserDetails = function (login) {
        console.log('Saving user details to local storage. Saving username: ' + login);
        $localStorage.userDetails = login;
    };
    this.deleteUserDetails = function () {
        delete $localStorage.userDetails;
    };

    this.loadUserDetails = function () {
        console.log('Loading user details from server');
        return $http({
            method: 'GET',
            url: '/userDetails'
        })
    };
    this.getCurrentUserLogin = function () {
        return $localStorage.userDetails;
    };

    this.isUserAuthorized = function () {
        var deferred = $q.defer();
        console.log('Checking if user is authorized');
        sessionService.loadUserDetails().then(
            function (response) {
                console.log("Response: " + response);
                console.log("Response username: " + response.data.username);
                if (angular.isDefined(response.data.username)
                    && response.data.username && !angular.equals(response.data.username, '')) {
                    sessionService.saveUserDetails(response.data.username);
                    console.log('User details saved in local storage');
                    deferred.resolve();
                } else {
                    console.log("User loaded form server is empty or undefined. Result is: " + response);
                    deferred.reject();
                }
            },
            function (response) {
                console.log("Failed to load user from server. Result is: " + response);
                deferred.reject();
            }
        );
        return deferred.promise;
    }
});
