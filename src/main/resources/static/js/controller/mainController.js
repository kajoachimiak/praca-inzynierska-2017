app.controller('mainController', function ($scope, $location, sessionService, $http) {
    console.log('Starting mainController');
    sessionService.isUserAuthorized().then(
        function () {
            console.log('callback success');
            $scope.courseList = ['SSBD', 'SUD', 'Podstawy Informatyki', 'TSK', 'WDI'];

            $scope.toggle = function (scope) {
                scope.toggle();
            };

            $scope.expandAll = function () {
                $scope.$broadcast('angular-ui-tree:expand-all');
            };

            $scope.collapseAll = function () {
                $scope.$broadcast('angular-ui-tree:collapse-all');
            };

            $scope.loadTreeData = function () {
                $http({
                    method: 'GET',
                    url: '/userRelations'
                }).then(function successCallback(response) {
                    console.log(response);
                    console.log('Tree data loading ok');
                }, function errorCallback(response) {
                    console.log(response);
                    console.log('Tree data loading error');
                });
            };

            $scope.loadTreeData();

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
            $location.path('/home');
        }
    )

});