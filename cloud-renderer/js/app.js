var App = angular.module('App', []);

App.controller('nordicTrends', function($scope, $http) {
  $http.get('http://localhost:3000/tags')
       .then(function(res){
          var flatList = res.data.tags;
          $scope.nordicTrendItems = _.groupBy(flatList, function(tag){ return tag.origin; });
          _.each($scope.nordicTrendItems, function(value, key, list) {
            var sortedList = _.sortBy(value, function(tag) { return tag.num * -1; });
            $scope.nordicTrendItems[key] = sortedList;
          });
        });
});
