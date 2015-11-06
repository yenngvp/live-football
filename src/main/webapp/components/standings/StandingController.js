var StandingController = ['$scope','$http','Standing','localStorageService', function ($scope, $http, Standing, localStorageService) {
	
	// Gets localStorage cached
	var key = 'standingsCache'; 
    $scope.standings = localStorageService.get(key);
    if (angular.isUndefined($scope.standings) || $scope.standings == null || $scope.standings == 0) {
    	Standing.query().$promise.then(
    			//success
    			function( value ) {
    				localStorageService.set(key, value);
    				$scope.standings = value;
    			},
    			//error
    			function( error ) {
    				// TODO: Handle request returns error
    				console.log("Failed with: " + error);
    			}
    	);
    }

}];