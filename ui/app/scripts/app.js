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
      .otherwise({
        redirectTo: '/'
      });

    // This prevents Spring from returning authorization methods header and in turn
    // prevents the browser from showing a basic-auth login form on unauthorized responses
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  });
