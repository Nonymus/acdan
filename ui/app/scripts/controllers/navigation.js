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

    var authenticate = function (credentials, callback) {
      var headers = credentials ? {
        authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
      } : {};

      $http.get('/user', {headers: headers}).success(function (data) {
        $rootScope.authenticated = data.name;
        if (callback) {
          callback();
        }
      }).error(function () {
        $rootScope.authenticated = false;
        if (callback) {
          callback();
        }
      });
    };

    authenticate();

    $scope.credentials= {};

    $scope.login = function() {
      authenticate($scope.credentials, function() {
        if ($rootScope.authenticated) {
          $location.path('/');
          $scope.error = false;
        } else {
          $location.path('/login');
          $scope.error = true;
        }
      });
    };

    $scope.logout = function () {
      $http.post('/logout', {}).success(function () {
        $rootScope.authenticated = false;
        $location.path('/');
      }).error(function () {
        $rootScope.authenticated = false;
      });
    };
  });
