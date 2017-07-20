app.controller('usersController', function ($scope) {
    $scope.headingTitle = "User List";
});


marked.setOptions({
    renderer: new marked.Renderer(),
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: true,
    smartLists: true,
    smartypants: false
});

app.controller('markdownController', function ($scope) {

    $scope.result = "";

    $scope.$watch('source', function () {
        $scope.result = marked($scope.source);
    });

});