app.controller('mainController', function ($scope, $location, sessionService, $http) {
    console.log('Starting mainController');
    sessionService.deleteUserDetails();
    sessionService.isUserAuthorized().then(
        function () {
            console.log('callback success');
            $scope.treeData = [];


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
                    $scope.treeData = response.data.list;
                }, function errorCallback(response) {
                    console.log(response);
                    console.log('Tree data loading error');
                });
            };

            $scope.loadTreeData();

            $scope.templateData = [];

            $scope.loadTemplatesForNode = function (nodeType) {
                $http({
                    method: "GET",
                    url: '/treeNodeTemplates',
                    params: {nodeType: nodeType}
                }).then(function successCallback(response) {
                    console.log(response);
                    $scope.templateData = response.data;
                    console.log('Templates loading ok');
                    console.log('Template data: ' + $scope.templateData);
                }, function errorCallback(response) {
                    console.log(response);
                    console.log('Templates loading error');
                });
            };

            $scope.toggleAccordionPanel = function (scope) {
                scope.toggleAccordionPanel();
            };

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