var StandingController = ['$rootScope','$scope','$http','Standing','enableCache','localStorageService','$stateParams',"$state","locale",
						  function ($rootScope, $scope, $http, Standing, enableCache, localStorageService, $stateParams, $state, locale) {
	
	$rootScope.pageTitle = "Bảng xếp hạng bóng đá " + localStorageService.get('PREFERENCES_COMPETITION').nameVn;
	
	if (enableCache) {
		// Gets localStorage cached
		var key = 'standingsCache';
		$scope.standings = localStorageService.get(key);
	} else {
		$scope.standings = undefined;
	}
     var compId = localStorageService.get('PREFERENCES_COMPETITION').id;
	if (angular.isUndefined($scope.standings) || $scope.standings == null) {

		Standing.standings.query({giai_dau_id: compId}).$promise.then(
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

					$scope.drawChart = compId != null && compId > 0;

					if ($scope.drawChart) {
						var allowMultiSelect = false;
						$scope.data = {
							multipleSelect: [],
							singleSelect: {}
						};
						$scope.data.multipleSelect = [];

						// Displays top 1 standing chart
						var s = $scope.standings[0];
						for (var i = 0; i < 1 && i < s.length; i++) {
							if (allowMultiSelect) {
								$scope.data.multipleSelect.push(s[i]);
							} else {
								$scope.data.singleSelect = s[i];
							}

						}

						var totalMatchdays;
						if (allowMultiSelect) {
							totalMatchdays = $scope.data.multipleSelect[0].squad.competition.totalMatchdays;
						} else {
							totalMatchdays = $scope.data.singleSelect.squad.competition.totalMatchdays;
						}

						// Chart options
						$scope.chartOptions = {
							"datasetFill": false,
                            /*
							"scaleOverride": true,
							"scaleSteps": 1,
							"scaleStepWidth": 19,
							"scaleStartValue": 1,
							"scaleFontSize": 9 */
						};
						$scope.labels = [];

						var matchweek = locale.getString('common.matchweek');

						for (var i = 1; i <= totalMatchdays; i++) {

							$scope.labels.push(i);
						}

						var updateChartData = function () {

							queryStandingHist($scope.data.singleSelect.squad.id).$promise.then(
								//success
								function (value) {
									if (value.length > 0) {
										$scope.series = [];
										$scope.series.push($scope.data.singleSelect.squad.name);

										var currentMatchday = value.length;
										$scope.chartData = [];
										var arrPos = [];
										for (var j = 0; j < currentMatchday; j++) {
											arrPos.push(value[j].currentPosition);
										}
										$scope.chartData.push(arrPos);

									}
								},
								function (error) {

								}
							);
						};

						updateChartData();

						$scope.update = function () {

							updateChartData();
						};

//					  $scope.onClick = function (points, evt) {
//					    console.log(points, evt);
//					  };
					}

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


                              var queryStandingHist = function(squadId) {
                                  return Standing.standingsAllBySquad.query({squadId: squadId});
                              };



	$scope.goTeamDetail = function(teamId) {
		$state.go('teamDetails', {id: teamId});
	};


}];