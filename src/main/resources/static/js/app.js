var translationsPL = {
    // login form
    SIGNIN_FORM_HEADING: 'Proszę się zalogować',
    SIGNIN_FORM_LOGIN: 'Login',
    SIGNIN_FORM_PASSWORD: 'Hasło',
    SIGNIN_FORM_SUBMIT: 'Zaloguj',

    //main page header
    MAIN_HEADER:'Menedżer serwera aplikacyjnego',
    LOGGED_USER:'Zalogowany użytkownik:',
    LOG_OUT_BUTTON:'Wyloguj',

    //sidebar
    EXPAND_TREE_BUTTON:'Rozwiń',
    COLLAPSE_TREE_BUTTON:'Zwiń',

    //main page content
    TEMPLATE_LIST_HEADER:'Lista szablonów',
    TEMPLATE_LIST_RUN_COMMAND:'Wykonaj',
    TEMPLATE_LIST_FILE_CONTENT:'Zawartość pliku:',
    TEMPLATE_LIST_GENERATE_FILE:'Generuj plik do pobrania',
    TEMPLATE_LIST_DOWNLOAD_FILE:'Pobierz plik',
    TEMPLATE_LIST_SAVE_CHANGES:'Zapisz zmiany',
    TEMPLATE_LIST_OPEN_URL_BTN:'Otwórz w nowej karcie',

    //alerts
    LOG_OUT_ERROR:'Wylogowanie nie powiodło się! Skontaktuj się z administratorem.',
    EXECUTE_COMMAND_SUCCESS:'Polecenie wykonano pomyślnie!',
    EXECUTE_COMMAND_ERROR:'Wykonanie polecenia nie powiodło się! Skontaktuj się z administratorem.',
    SAVE_CHANGES_SUCCESS:'Pomyślnie zapisano zmiany!',
    SAVE_CHANGES_ERROR:'Zapisanie zmian nie powiodło się! Skontaktuj się z administratorem.',
    LOAD_URL_ERROR:'Pobranie adresu strony nie powiodło się! Skontaktuj się z administratorem.'


};

var translationsEN = {
    // login form
    SIGNIN_FORM_HEADING: 'Please Log In',
    SIGNIN_FORM_LOGIN: 'Login',
    SIGNIN_FORM_PASSWORD: 'Password',
    SIGNIN_FORM_SUBMIT: 'Log In',

    //main page header
    MAIN_HEADER:'Application server manager ',
    LOGGED_USER:'Current user:',
    LOG_OUT_BUTTON:'Log Out',

    //sidebar
    EXPAND_TREE_BUTTON:'Expand all',
    COLLAPSE_TREE_BUTTON:'Collapse all',

    //main page content
    TEMPLATE_LIST_HEADER:'Template list',
    TEMPLATE_LIST_RUN_COMMAND:'Run',
    TEMPLATE_LIST_FILE_CONTENT:'File content:',
    TEMPLATE_LIST_GENERATE_FILE:'Generate file to download',
    TEMPLATE_LIST_DOWNLOAD_FILE:'Download file',
    TEMPLATE_LIST_SAVE_CHANGES:'Save changes',
    TEMPLATE_LIST_OPEN_URL_BTN:'Open in new tab',

    // alerts
    LOG_OUT_ERROR:'Log out failed! Please, contact administrator.',
    EXECUTE_COMMAND_SUCCESS:'Command executed successfully!',
    EXECUTE_COMMAND_ERROR:'Command execution failed! Please, contact administrator.',
    SAVE_CHANGES_SUCCESS:'Changes saved!',
    SAVE_CHANGES_ERROR:'Saving changes failed! Please, contact administrator.',
    LOAD_URL_ERROR:'Loading url failed! Please, contact administrator.'

};

var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngResource','ngAnimate','ngTouch', 'ui.bootstrap',
    'ngStorage', 'ui.tree', 'pascalprecht.translate']);
app.config(function ($routeProvider, $translateProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/views/loginPage.html',
            controller: 'loginController'
        })
        .when('/logout', {
            redirectTo: '/home'
        })
        .when('/mainPage', {
            templateUrl: '/views/mainPage.html'
        })
        .otherwise(
        { redirectTo: '/home'}
    );

    $translateProvider.translations('pl', translationsPL);
    $translateProvider.translations('en', translationsEN);
    $translateProvider.fallbackLanguage('pl');
    $translateProvider.preferredLanguage('pl');

});
