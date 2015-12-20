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
    $scope.greeting = {id: 'xxx', content: 'Hello World'};

    // Get auth-token from UI-Server, then request real data from resource-server
    $http.get('/token').success(function(token){
      $http({
        url: 'http://localhost:9000',
        method: 'GET',
        headers: {
          'X-Auth-Token': token.token
        }
      }).success(function(data){
        $scope.greeting = data;
      })
    });
  });
