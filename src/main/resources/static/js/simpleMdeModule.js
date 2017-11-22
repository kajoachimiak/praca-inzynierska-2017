/**
 * Created by karol on 15.11.17.
 */
angular.module('uiSimplemde', [])
    .value('simpleMdeConfig', {
    })
    .directive('uiSimplemde', function($window, $timeout){
        //...
});

function createEditorInstance(options, element){
    //create and bind editor instance by pass element to constructor
    options['element']= element;
    var editor = new SimpleMDE(options);
    //load default value inside target textarea element
    var default_value = angular.element(element).val();
    if(default_value.trim())
        editor.value(default_value);
    return editor;
}

function syncWithNgModel(editor, ngModel, scope){
    if(!ngModel)
        return;
    //model->editor
    ngModel.$render = function(){
        editor.value(ngModel.$viewValue);
    };
    //editor->model
    editor.codemirror.on('changes', function(){
        var newValue = editor.value();
        if(newValue !== ngModel.$viewValue){
            scope.$evalAsync(function(){
                ngModel.$setViewValue(newValue);
            })
        }
    });
}

function watchConfigurations(scope, attrs, editor){
    scope.$watch(attrs.uiSimplemde, function(newVal, oldVal){
        if(newVal !== oldVal){
            //update SimpleMDE configurations
        }
    });
}
