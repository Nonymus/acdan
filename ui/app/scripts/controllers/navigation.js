/**
 * Created by jp on 20.12.15.
 */
'use strict';

/**
 * @ngdoc function
 * @name acdanuiApp.controller:NavigationCtrl
 * @description
 * # NavigationCtrl
 * Controller of the acdanuiApp
 */
angular.module('acdanuiApp')
  .controller('NavigationCtrl', function ($scope, $rootScope, $http, $location) {

    $scope.isActive = function (loc) {
      return loc === $location.path();
    };

    $http.get('/user').success(function(data){
      /*jshint -W116 */
      $rootScope.authenticated = data.name == true;
      /*jshint +W116 */
    }).error(function() {
      $rootScope.authenticated = false;
    });

    $scope.logout = function () {
      $http.post('/logout', {}).success(function () {
        $rootScope.authenticated = false;
        $location.path('/');
      }).error(function () {
        $rootScope.authenticated = false;
      });
    };
  });
