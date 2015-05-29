var App = angular.module('App', []);

App.controller('nordicTrends', function($scope, $http) {
  $http.get('http://localhost:3000/tags')
       .then(function(res){
          var flatList = res.data.tags;
          $scope.nordicTrendItems = _.groupBy(flatList, function(tag){ return tag.origin; });
        });
});