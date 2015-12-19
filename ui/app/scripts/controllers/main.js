'use strict';

/**
 * @ngdoc function
 * @name acdanuiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the acdanuiApp
 */
angular.module('acdanuiApp')
  .controller('MainCtrl', function ($scope) {
    $scope.greeting = {id: 'xxx', content: 'Hello World'};
  });
