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
				
		MatchDay.results.query({competition: $stateParams.competition}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}

					$scope.results = value;
					
					$scope.hideSpinner = true;
				},
				//error
				function( error ) {
					// TODO: Handle request returns error
					console.log("Failed with: " + error);
					
					$scope.hideSpinner = true;
				}
			);
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
				
		MatchDay.resultsByDay.query({matchday: $stateParams.matchday, competition: $stateParams.competition}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}

					$scope.results = value;
					
					$scope.hideSpinner = true;
				},
				//error
				function( error ) {
					// TODO: Handle request returns error
					console.log("Failed with: " + error);

					$scope.hideSpinner = true;
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
