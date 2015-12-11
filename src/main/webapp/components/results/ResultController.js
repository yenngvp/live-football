/**
 * 
 */
var ResultController = ['$scope', '$stateParams', 'MatchDay','enableCache','localStorageService',
                          function($scope, $stateParams, MatchDay, enableCache, localStorageService) {
	
	// Gets localStorage cached
	var key = 'resultsCache'; 
	if (enableCache) {
		console.log("Cache service enabled");
		$scope.results = localStorageService.get(key);
	} else {
		$scope.results = undefined;
	}
	
	if (angular.isUndefined($scope.results) || $scope.results == null || $scope.results == 0) {
				
		var competition = null;//localStorageService.get("PREFERENCES_COMPETITION");
		if (competition != null) {
			
			MatchDay.resultsByDay.query({id: $stateParams.id, day: $stateParams.day}).$promise.then(
					//success
					function( value ) {
						if (enableCache) {
							localStorageService.set(key, value);
						}

						$scope.results = value;
						
						console.log(value);
					},
					//error
					function( error ) {
						// TODO: Handle request returns error
						console.log("Failed with: " + error);
					}
			);
		} else {
		
			MatchDay.results.query().$promise.then(
					//success
					function( value ) {
						if (enableCache) {
							localStorageService.set(key, value);
						}
	
						$scope.results = value;
					},
					//error
					function( error ) {
						// TODO: Handle request returns error
						console.log("Failed with: " + error);
					}
			);
		}
	}	
}];

var ResultByDayController = ['$scope', '$stateParams', 'MatchDay','enableCache','localStorageService',
                        function($scope, $stateParams, MatchDay, enableCache, localStorageService) {
	
	// Gets localStorage cached
	var key = 'resultsCache'; 
	if (enableCache) {
		console.log("Cache service enabled");
		$scope.results = localStorageService.get(key);
	} else {
		$scope.results = undefined;
	}
	
	if (angular.isUndefined($scope.results) || $scope.results == null || $scope.results == 0) {
				
		MatchDay.resultsByDay.query({id: $stateParams.id, day: $stateParams.day}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}

					$scope.results = value;
					
					console.log(value);
				},
				//error
				function( error ) {
					// TODO: Handle request returns error
					console.log("Failed with: " + error);
				}
		);
	}	
}];


var MatchdayPagedController = ["$scope", "MatchDay", function($scope, MatchDay) {

	$scope.init = function(max) {
		$scope.data = [];
		for (var i = 1; i <= max; i++) {
			$scope.data.push(i);
		}
	};

    $scope.currentPage = 0;
    $scope.pageSize = 10;

    $scope.numberOfPages = function() {
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }
    
 
}];
