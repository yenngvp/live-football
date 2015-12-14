var StandingController = ['$scope','$http','Standing','enableCache','localStorageService','$stateParams',
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
		var selectedCompetition = $stateParams.competitionId;
		if (angular.isUndefined(selectedCompetition) || selectedCompetition == 0) {
			selectedCompetition = localStorageService.get("PREFERENCES_COMPETITION");
			if (selectedCompetition == null) selectedCompetition = 0;
		}
		
		Standing.standings.query({competitionId: selectedCompetition.id}).$promise.then(
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