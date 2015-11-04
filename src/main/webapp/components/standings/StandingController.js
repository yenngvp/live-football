var StandingController = ['$scope','$http','Standing', function ($scope, $http, Standing) {
	
	$scope.standings = Standing.query();
	
}];