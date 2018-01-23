app.service('sessionService', function ($localStorage, $http, $q) {
    var sessionService = this;
    this.saveUserDetails = function (login) {
        console.log('Saving user details to local storage. Saving username: ' + login);
        $localStorage.userDetails = login;
    };
    this.isLocalUserDetailsPresent = function () {
        var userDetails = $localStorage.userDetails;
        console.log('Checking if user details are saved in local storage');
        console.log("Username from local storage: " + userDetails);
        return (angular.isDefined(userDetails)
        && userDetails
        && !angular.equals(userDetails, ''))
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

    this.isUserAuthorized = function () {
        var deferred = $q.defer();
        console.log('Checking if user is authorized');
        if (!sessionService.isLocalUserDetailsPresent()) {
            console.log("User details not saved in local storage");
            sessionService.loadUserDetails().then(
                function (response) {
                    console.log("Response: " + response);
                    console.log("Response username: " + response.data.username);
                    if ( angular.isDefined(response.data.username)
                        && response.data.username && !angular.equals(response.data.username, '')) {
                        sessionService.saveUserDetails(response.data.username);
                        console.log('User details saved in local storage');
                        deferred.resolve();
                    }else {
                        console.log("User loaded form server is empty or undefined. Result is: " + response);
                        deferred.reject();
                    }
                },
                function (response) {
                    console.log("Failed to load user from server. Result is: " + response);
                    deferred.reject();
                }
            );
        } else {
            console.log('User is authorized');
            deferred.resolve();
        }
        return deferred.promise;
    }
});
