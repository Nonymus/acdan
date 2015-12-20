'use strict';

/**
 * @ngdoc function
 * @name acdanuiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the acdanuiApp
 */
angular.module('acdanuiApp')
  .controller('MainCtrl', function ($scope, $http) {

    // Value if nothings loaded, i.e. not authenticated
    $scope.greeting = {id: 'xxx', content: 'Hello World'};

    $http.get('resource/').success(function (data) {
      $scope.greeting = data;
    });
  });
