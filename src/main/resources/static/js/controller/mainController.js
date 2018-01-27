app.controller('mainController', function ($scope, $location, sessionService, $http) {
    console.log('Starting mainController');
    sessionService.deleteUserDetails();
    sessionService.isUserAuthorized().then(
        function () {
            console.log('callback success');

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
            $scope.resetAlertFlags = function () {
                $scope.showScriptError = false;
                $scope.showScriptSuccess = false;
            };

            $scope.runScript = function (templateId, templateName) {
                $http({
                    method: 'GET',
                    url: '/runScript',
                    params: {templateId: templateId, templateName:templateName}
                }).then(function successCallback(response) {
                    console.log("Run script success");
                    console.log(response);
                    $scope.scriptResult = response.data.executionSuccess;
                    if($scope.scriptResult === true){
                        $scope.showScriptSuccess = true;
                        $scope.showScriptError = false;
                    }else {
                        $scope.showScriptSuccess = false;
                        $scope.showScriptError = true;
                    }
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
                $scope.showFileSaveSuccess = false;
                $scope.showFileSaveError = false;
                $scope.fileContent = '';
                $scope.fileName = 'log';
                $http({
                    method: 'GET',
                    url: '/getFileContent',
                    params: {templateId: templateId, templateName:templateName},
                    responseType: 'text'
                }).then(function successCallback(response) {
                    console.log("Loading file content success");
                    console.log(response);
                    $scope.fileContent = response.data.fileContent;
                    console.log($scope.fileContent);
                }, function errorCallback(response) {
                    console.log("Loading file content error");
                    console.log(response);
                });
            };
            $scope.autoExpand = function (e) {
                var element = typeof e === 'object' ? e.target : document.getElementById(e);
                var scrollHeight = element.scrollHeight - 60; // replace 60 by the sum of padding-top and padding-bottom
                element.style.height = scrollHeight + "px";
            };


            var textFile = null;
            var makeTextFile = function (text) {
                console.log("File content:" +text );
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
                console.log("File content:" + fileContent );
                var postData = {
                    fileContent: fileContent
                };
                $http({
                    method: 'POST',
                    url: '/saveFile',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    params: {templateId: templateId, templateName:templateName},
                    data: postData
                }).then(function successCallback(response) {
                    console.log("Saving file content success");
                    console.log(response);
                    $scope.saveFileSuccess = response.data.writingSuccess;
                    if($scope.saveFileSuccess === true) {
                        $scope.showFileSaveSuccess = true;
                        $scope.showFileSaveError = false;
                    }else {
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
                });
            };
        }, function () {
            console.log('callback error');
            $location.path('/home');
        }
    )

});