
app.controller('editorController', function ($scope, $http, $location, sessionService, $localStorage) {
        console.log('Starting editorController');
        sessionService.isUserAuthorized().then(
            function () {
                console.log('callback success');
                $scope.headingTitle = "Editor";
                $scope.fileContent = '';
                $scope.fileName = 'log';
                $scope.getLogFile = function () {
                    $http({
                        method: 'GET',
                        url: '/getLog',
                        responseType: 'text'
                    }).then(function successCallback(response) {
                        console.log(response);
                        $scope.fileContent = response.data.log;
                        console.log($scope.fileContent);
                    }, function errorCallback(response) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
                $scope.getLogFile();
                $scope.autoExpand = function (e) {
                    var element = typeof e === 'object' ? e.target : document.getElementById(e);
                    var scrollHeight = element.scrollHeight - 60; // replace 60 by the sum of padding-top and padding-bottom
                    element.style.height = scrollHeight + "px";
                };


                var textFile = null;
                var makeTextFile = function (text) {
                    var data = new Blob([text], {type: 'text/plain'});
                    if (textFile !== null) {
                        window.URL.revokeObjectURL(textFile);
                    }

                    textFile = window.URL.createObjectURL(data);

                    return textFile;
                };
                $scope.buttonClick = function () {
                    var link = document.getElementById('downloadlink');
                    link.href = makeTextFile($scope.fileContent);
                    link.style.display = 'block';
                };

                $scope.showLogoutError = false;

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
                $localStorage.showNotLoggedInError = true;
                $location.path('/home');
            }
        )
    }
);