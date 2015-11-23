var StandingController = ['$scope','$http','Standing','enableCache','localStorageService',
                          function ($scope, $http, Standing, enableCache, localStorageService) {
	
	if (enableCache) {
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
	} else {
		$scope.standings = Standing.query();
	}

}];