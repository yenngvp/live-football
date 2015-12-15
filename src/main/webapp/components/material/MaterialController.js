

var MaterialController = ['$scope','$http','Standing','enableCache','localStorageService','$stateParams',
                          function ($scope, $http, Standing, enableCache, localStorageService, $stateParams) {
	
	if (enableCache) {
		// Gets localStorage cached
		var key = 'standingsCache'; 
		$scope.standings = localStorageService.get(key);
	} else {
		$scope.standings = undefined;
	}
	
	if (angular.isUndefined($scope.standings) || $scope.standings == null || $scope.standings == 0) {
		
		// Gets user competition pref from the local storage
		var competitionId = $stateParams.competitionId;
		if (angular.isUndefined(competitionId) || competitionId == 0) {
			var competition = localStorageService.get("PREFERENCES_COMPETITION");
			if (competition == null) competitionId = 0;
			else competitionId = competition.id;
		}
		
		Standing.standings.query({competition: $stateParams.competition}).$promise.then(
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