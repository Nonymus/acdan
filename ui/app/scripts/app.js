'use strict';

/**
 * @ngdoc overview
 * @name acdanuiApp
 * @description
 * # acdanuiApp
 *
 * Main module of the application.
 */
angular
  .module('acdanuiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider, $httpProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'NavigationCtrl',
        controllerAs: 'navigation'
      })
      .otherwise({
        redirectTo: '/'
      });

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  });
