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

app.controller('editorController', function($scope) {
    $scope.headingTitle = "Editor";
    function uiSimplemdeDirective($timeout, $window, simpleMdeConfig){
        return {
            require: '?ngModel',
            restrict: 'A',
            priority: 599,
            link: function(scope, element, attrs, ngModel){
                if(angular.isUndefined($window.SimpleMDE)){
                    throw new Error("SimpleMDE is not defined! Is simplemde.js included?");
                }
                //create editor instance
                var default_options = simpleMdeConfig || {};
                var current_options = scope.$eval(attrs.uiSimplemde);
                var options = angular.extend({}, default_options, current_options);
                var editor = createEditorInstance(options, element[0]);
                scope.$mde = editor;
                //setup value synchronization
                syncWithNgModel(editor, ngModel, scope);
                //watch configuration changes
                watchConfigurations(scope, attrs, editor);
                //cleanup
                scope.$on('$destroy', function(){
                    //destroy editor instance
                    scope.$mde.toTextArea();
                });
            }
        };
    }
});