app.controller('scriptTestController', function($scope,$http) {
    $scope.headingTitle = "Script Test";
    $scope.runScript = function () {
        $http({
            method: 'GET',
            url: '/scriptTest'
        }).then(function successCallback(response) {
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
});

app.controller('editorController', function($scope, $http) {
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
    $scope.autoExpand = function(e) {
        var element = typeof e === 'object' ? e.target : document.getElementById(e);
        var scrollHeight = element.scrollHeight -60; // replace 60 by the sum of padding-top and padding-bottom
        element.style.height =  scrollHeight + "px";
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
});

