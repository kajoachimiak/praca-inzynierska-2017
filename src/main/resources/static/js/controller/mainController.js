app.controller('mainController', function ($scope, $location, sessionService, $http, $window, $translate, $timeout) {
    console.log('Starting mainController');
    sessionService.deleteUserDetails();
    sessionService.isUserAuthorized().then(
        function () {
            console.log('callback success');
            $scope.currentUserLogin = sessionService.getCurrentUserLogin();

            $scope.changeLanguage = function (langKey) {
                $translate.use(langKey);
            };

            //Tree logic begin
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
            //Tree logic end


            //Template list logic begin
            $scope.templateData = [];

            $scope.loadTemplatesForNode = function (nodeType) {
                $scope.statusOpen = false;
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
            //Template list logic end


            //Script template logic begin
            $scope.resetFlags = function () {
                $scope.showLoadHistoryError = false;
                $scope.showScriptError = false;
                $scope.showScriptSuccess = false;
                $scope.showHistoryTable = false;
                $scope.showLoadingIcon = false;
            };

            $scope.checkProcessStatus = function (processId) {
                $scope.processStatus = 'PROCESSING';
                do {
                    $scope.showLoadingIcon = true;
                    $timeout(function () {
                        $http({
                            method: 'GET',
                            url: '/processStatus',
                            params: {processId: processId}
                        }).then(function successCallback(response) {
                            console.log("Checking process status");
                            console.log(response);

                            $scope.processStatus = response.data;
                            $scope.showLoadingIcon = false;
                            if (angular.equals($scope.processStatus,'SUCCESS')) {
                                $scope.showScriptSuccess = true;
                                $scope.showScriptError = false;
                            }
                            if(angular.equals($scope.processStatus,'FAILED')) {
                                $scope.showScriptSuccess = false;
                                $scope.showScriptError = true;
                            }
                            console.log($scope.processStatus);
                        }, function errorCallback(response) {
                            console.log("Checking process status error");
                            console.log(response);
                            $scope.showScriptSuccess = false;
                            $scope.showScriptError = true;
                        });
                    }, 5000);
                } while (angular.isDefined($scope.processStatus)
                        && $scope.processStatus
                        && !angular.equals($scope.processStatus,'PROCESSING'));
            };

            $scope.runScript = function (templateId, templateName) {
                $http({
                    method: 'GET',
                    url: '/runScript',
                    params: {templateId: templateId, templateName: templateName}
                }).then(function successCallback(response) {
                    console.log(response);
                    $scope.scriptResult = response.data.executionSuccess;
                    $scope.processId = response.data.message;
                    $scope.checkProcessStatus($scope.processId);
                }, function errorCallback(response) {
                    console.log("Run script error");
                    console.log(response);
                    $scope.showScriptSuccess = false;
                    $scope.showScriptError = true;
                });
            };
            //Script template logic end


            //File template logic begin


            $scope.loadFileContent = function (templateId, templateName) {
                $scope.showDownloadLink = false;
                $scope.showFileSaveSuccess = false;
                $scope.showFileSaveError = false;
                $scope.showLoadFileError = false;
                $scope.fileContent = '';
                $scope.fileName = 'log';
                $http({
                    method: 'GET',
                    url: '/getFileContent',
                    params: {templateId: templateId, templateName: templateName},
                    responseType: 'text'
                }).then(function successCallback(response) {
                    console.log("Loading file content success");
                    console.log(response);
                    $scope.fileContent = response.data.fileContent;
                    $scope.status = response.data.status;
                    console.log($scope.fileContent);
                    $scope.showLoadFileError = !$scope.status;
                }, function errorCallback(response) {
                    console.log("Loading file content error");
                    console.log(response);
                    $scope.showLoadFileError = true;
                });
            };


            var textFile = null;
            var makeTextFile = function (text) {
                console.log("File content:" + text);
                var data = new Blob([text], {type: 'text/plain'});
                if (textFile !== null) {
                    window.URL.revokeObjectURL(textFile);
                }

                textFile = window.URL.createObjectURL(data);
                console.log("File to save content" + textFile);
                return textFile;
            };
            $scope.showDownloadLink = false;
            $scope.downloadFile = function (fileContent) {
                console.log("Starting download file");
                var link = document.getElementById('downloadlink');
                link.href = makeTextFile(fileContent);
                console.log("Link to download file content" + link.href);
                $scope.showDownloadLink = true;
            };

            $scope.saveFileOnServer = function (templateId, templateName, fileContent) {
                console.log("File content:" + fileContent);
                var postData = {
                    fileContent: fileContent
                };
                $http({
                    method: 'POST',
                    url: '/saveFile',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    params: {templateId: templateId, templateName: templateName},
                    data: postData
                }).then(function successCallback(response) {
                    console.log("Saving file content success");
                    console.log(response);
                    $scope.saveFileSuccess = response.data.writingSuccess;
                    if ($scope.saveFileSuccess === true) {
                        $scope.showFileSaveSuccess = true;
                        $scope.showFileSaveError = false;
                    } else {
                        $scope.showFileSaveSuccess = false;
                        $scope.showFileSaveError = true;
                    }
                }, function errorCallback(response) {
                    console.log("Saving file content error");
                    console.log(response);
                    $scope.showFileSaveSuccess = false;
                    $scope.showFileSaveError = true;
                });
            };
            //File template logic end

            //URL template logic begin
            $scope.loadUrl = function (templateId, templateName) {
                $scope.showLoadUrlError = false;
                $http({
                    method: 'GET',
                    url: '/getUrl',
                    params: {templateId: templateId, templateName: templateName}
                }).then(function successCallback(response) {
                    console.log("Loading url success");
                    console.log(response);
                    $scope.url = response.data.url;
                    console.log("Loaded url: " + $scope.url);
                }, function errorCallback(response) {
                    console.log("Loading url error");
                    console.log(response);
                    $scope.showLoadUrlError = true;
                });
            };

            $scope.redirectToUrl = function (url) {
                console.log("Redirecting to url : " + url);
                $window.open(url, '_blank');
            };
            //URL template logic end


            //template history logic begin
            $scope.loadTemplateHistory = function (templateId, templateName) {
                $scope.showLoadHistoryError = false;
                $scope.showHistoryTable = false;
                $http({
                    method: 'GET',
                    url: '/loadTemplateHistory',
                    params: {templateId: templateId, templateName: templateName}
                }).then(function successCallback(response) {
                    console.log("Loading template history success");
                    console.log(response);
                    $scope.templateHistory = response.data.templateHistory;
                    $scope.showHistoryTable = true;
                    $scope.showLoadHistoryError = false;
                    console.log("Loaded template history: " + $scope.templateHistory);
                }, function errorCallback(response) {
                    console.log("Loading template history error");
                    console.log(response);
                    $scope.showLoadHistoryError = true;
                    $scope.showHistoryTable = false;
                });

            };
            //template history logic end

            $scope.showLogoutError = false;
            //Logout logic
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
                    $scope.showLogoutError = true;
                });
            };
        }, function () {
            console.log('callback error');
            $location.path('/home');
        }
    )

});