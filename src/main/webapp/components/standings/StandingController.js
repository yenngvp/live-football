var StandingController = ['$scope','$http','Standing','enableCache','localStorageService',
                          function ($scope, $http, Standing, enableCache, localStorageService) {
	
	if (enableCache) {
		// Gets localStorage cached
		var key = 'standingsCache'; 
		$scope.standings = localStorageService.get(key);
	} else {
		$scope.standings = undefined;
	}
	
	if (angular.isUndefined($scope.standings) || $scope.standings == null || $scope.standings == 0) {
		Standing.query().$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}
					
					$scope.standings = value;
				},
				//error
				function( error ) {
					// TODO: Handle request returns error
					console.log("Network error: " + error);
				}
		);
	}

}];