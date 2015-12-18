var StandingController = ['$scope','$http','Standing','enableCache','localStorageService','$stateParams',"$state",
                          function ($scope, $http, Standing, enableCache, localStorageService, $stateParams, $state) {
	
	if (enableCache) {
		// Gets localStorage cached
		var key = 'standingsCache'; 
		$scope.standings = localStorageService.get(key);
	} else {
		$scope.standings = undefined;
	}
		
	if (angular.isUndefined($scope.standings) || $scope.standings == null || $scope.standings == 0) {
			
		Standing.standings.query({competition: $stateParams.competition}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}
					  
					$scope.standings = [];
					for (var i = 0; i < value.length; i++) {
						var arr = [];
						for (var index in value[i]) {
							if (typeof value[i][index] === 'object') {
								arr.push(value[i][index]);
							}
						}
						$scope.standings.push(arr);
					 }
					

					// Display some top 4 charts
					var s = $scope.standings[0];
					$scope.standingsForChart = [];
					for (var i = 0; i < 4 && i < s.length; i++) {
						$scope.standingsForChart.push(s[i]);
					}
					console.log($scope.standingsForChart);
					
					// Chart options
					 $scope.chartOptions = {"datasetFill" : false};
					 $scope.labels = [];
					 var totalMatchdays = $scope.standingsForChart[0].squad.competition.totalMatchdays;
					 var currentMatchday = $scope.standingsForChart[0].squad.competition.nextMatchday - 1;
					 for (var i = 1; i <= totalMatchdays; i++) {
						 $scope.labels.push(i);
					 }
					 
					 $scope.series = [];
					 $scope.selectedStandings = {};
					 $scope.standingsChartForTeams = [];

					for (var i = 0; i < $scope.standingsForChart.length; i++) {
						 $scope.series.push($scope.standingsForChart[i].squad.name);
						 
						 var arrPos = [];
						 for (var i = 0; i < currentMatchday; i++) {
							 $scope.selectedStandings = standingsForChart[i];
							 arrPos.push($scope.standingsForChart[i].currentPosition);
						 }
						 $scope.standingsChartForTeams.push(arrPos);
					 }
					 
					 $scope.update = function() {
						console.log($scope.selectedStandings.squad.name);
						
//						$scope.series = [];
//						for (var i = 0; i < $scope.standingsChartForTeams.length; i++) {
//							$scope.series.push(standing[$scope.standingsChartForTeams[i]].squad.name);
//						}
					 };
					 
//					  $scope.onClick = function (points, evt) {
//					    console.log(points, evt);
//					  };

					$scope.hideSpinner = true;
				},
				//error
				function( error ) {
					// TODO: Handle request returns error
					console.log("Network error: " + error);

					$scope.hideSpinner = true;
				}
		);
	}

	$scope.goTeamDetail = function(teamId) {
		$state.go('teamDetails', {id: teamId});
	};
	
	
}];