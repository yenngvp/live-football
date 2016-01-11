/**
 * 
 */
var ResultController = ['$rootScope', '$scope', '$stateParams', 'MatchDay','enableCache','localStorageService',
                          function($rootScope, $scope, $stateParams, MatchDay, enableCache, localStorageService) {
	
	$rootScope.pageTitle = "Kết quả bóng đá " + localStorageService.get('PREFERENCES_COMPETITION').nameVn;
	
	// Gets localStorage cached
	var key = 'resultsCache'; 
	if (enableCache) {
		console.log("Cache service enabled");
		$scope.results = localStorageService.get(key);
	} else {
		$scope.results = undefined;
	}
    var compId = localStorageService.get('PREFERENCES_COMPETITION').id;

	if (angular.isUndefined($scope.results) || $scope.results == null || $scope.results == 0) {
				
		MatchDay.results.query({giai_dau_id: compId}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}

					$scope.results = value;
                    $scope.numMatchdays = value.length;

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

var ResultByDayController = ['$rootScope', '$scope', '$stateParams', 'MatchDay','enableCache','localStorageService',
                        function($rootScope, $scope, $stateParams, MatchDay, enableCache, localStorageService) {
	
	$rootScope.pageTitle = "Kết quả bóng đá " + localStorageService.get('PREFERENCES_COMPETITION').nameVn;
	
	// Gets localStorage cached
	var key = 'resultsCache'; 
	if (enableCache) {
		console.log("Cache service enabled");
		$scope.results = localStorageService.get(key);
	} else {
		$scope.results = undefined;
	}
    var compId = localStorageService.get('PREFERENCES_COMPETITION').id;
	if (angular.isUndefined($scope.results) || $scope.results == null || $scope.results == 0) {
				
		MatchDay.resultsByDay.query({matchday: $stateParams.matchday, giai_dau_id: compId}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}

					$scope.results = value;
                    $scope.numMatchdays = value.length;
					
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

	$scope.init = function(max, current) {
		$scope.data = [];
		for (var i = 1; i <= max; i++) {
			$scope.data.push(i);
		}
        $scope.pageSize = 4;
        var v = Math.floor(current / $scope.pageSize);
        if (current == $scope.pageSize * v) {
        	$scope.currentPage = v - 1;
        } else {
        	$scope.currentPage = v;
        }
	};

    $scope.numberOfPages = function() {
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }
    
 
}];
