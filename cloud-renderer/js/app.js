var App = angular.module('App', []);

App.controller('nordicTrends', function($scope, $http) {
  $http.get('http://localhost:3000/tags')
       .then(function(res){
          $scope.nordicTrendItems = res.data.tags;
        });
});