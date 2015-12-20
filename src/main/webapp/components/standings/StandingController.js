var StandingController = ['$scope','$http','Standing','enableCache','localStorageService','$stateParams',"$state","locale",
                          function ($scope, $http, Standing, enableCache, localStorageService, $stateParams, $state, locale) {
	
	if (enableCache) {
		// Gets localStorage cached
		var key = 'standingsCache'; 
		$scope.standings = localStorageService.get(key);
	} else {
		$scope.standings = undefined;
	}
		
	if (angular.isUndefined($scope.standings) || $scope.standings == null) {
			
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

                    $scope.data = {
                        multipleSelect: []
                    };
                    $scope.data.multipleSelect = [];

					// Displays top 4 standings chart
					var s = $scope.standings[0];
					for (var i = 0; i < 4 && i < s.length; i++) {
                        $scope.data.multipleSelect.push(s[i]);
					}

					// Chart options
					 $scope.chartOptions = {"datasetFill" : false};
					 $scope.labels = [];
					 var totalMatchdays = $scope.data.multipleSelect[0].squad.competition.totalMatchdays;
					 var currentMatchday = 15;//$scope.data.multipleSelect[0].squad.competition.nextMatchday;
                     if (currentMatchday < 0) currentMatchday = 10; // testing

                    var matchweek = locale.getString('common.matchweek');

                    for (var i = 1; i <= totalMatchdays; i++) {

                         $scope.labels.push( matchweek + ' ' + i );
					 }

                    var updateChartData = function () {
                        $scope.series = [];
                        $scope.chartData = [];

                        for (var i = 0; i < $scope.data.multipleSelect.length; i++) {
                            $scope.series.push($scope.data.multipleSelect[i].squad.name);

                            var arrPos = [];
                            for (var j = 0; j < currentMatchday; j++) {
                                arrPos.push($scope.data.multipleSelect[i].currentPosition);
                            }
                            $scope.chartData.push(arrPos);
                        }
                    };

                    updateChartData();

					 $scope.update = function() {

                         updateChartData();
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