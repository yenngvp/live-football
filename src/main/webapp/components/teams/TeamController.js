var TeamController = ['$rootScope','$scope','$state','Team','localStorageService', 'enableCache','$stateParams',
                      function($rootScope,$scope,$state,Team,localStorageService,enableCache,$stateParams) {
	
	$rootScope.pageTitle = "Danh sách đội bóng " + localStorageService.get('PREFERENCES_COMPETITION').nameVn;
    
	// Gets localStorage cached
    var key = 'teamsCache';
    if (enableCache) {
        $scope.teams = localStorageService.get(key);
    } else {
        $scope.teams = null;
    }
    var compId = localStorageService.get('PREFERENCES_COMPETITION').id;
    if (angular.isUndefined($scope.teams) || $scope.teams == null || $scope.teams.length == 0) {
        Team.teams.query({giai_dau_id: compId}).$promise.then(
                //success
                function( value ) {
                    if (enableCache) {
                        localStorageService.set(key, value);
                    }

                    $scope.teams = value;
                    var competition = $scope.teams[0].competition;
                    if (competition) {
                        $scope.competitionName = competition.nameVn + " (" + competition.yearFrom.toString() + "/" + competition.yearTo.toString() + ")";
                    }

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


var TeamDetailsController = ['$scope','$rootScope','$stateParams', '$cacheFactory','Team','localStorageService','enableCache','Standing',
                             function($scope,$rootScope,$stateParams,$cacheFactory,Team,localStorageService,enableCache, Standing) {

	$rootScope.pageTitle = "Thông tin đội bóng " + localStorageService.get('PREFERENCES_COMPETITION').nameVn;;
	
    // Gets localStorage cached
    var key = 'teamMembersCache_' + $stateParams.id;
    if (enableCache) {
        $scope.teamMembers = localStorageService.get(key);
    } else {
        $scope.teamMembers = null;
    }

     Standing.standingBySquad.get({squadId: $stateParams.id}).$promise.then(
        //success
        function (value) {
            $scope.currentStanding = value;

            $scope.labels = ["Thắng","Hoà","Thua"];
            $scope.chartData = [value.won, value.drawn, value.lost];
            $scope.chartColors = ["#FFBC12", "#52E8FF", "#3F2F7F"];
        },
         //error
         function (error) {
             // TODO: Handle request returns error
             console.log("Failed with: " + error);
             $scope.hideSpinner = true;
        }
      );

     if (angular.isUndefined($scope.teamMembers) || $scope.teamMembers == null || $scope.teamMembers.length == 0) {
        Team.teamMembers.query({id: $stateParams.id}).$promise.then(
            //success
            function (value) {
                if (enableCache) {
                    localStorageService.set(key, value);
                }

                $scope.teamMembers = value;
                
                if (value.length > 0) {
                	var competition = $scope.teamMembers[0].squad.competition;
                	if (competition) {
                		$scope.season = competition.yearFrom.toString() + "/" + competition.yearTo.toString();
                	}
                }
                
                $scope.hideSpinner = true;
            },
            //error
            function (error) {
                // TODO: Handle request returns error
                console.log("Failed with: " + error);
                $scope.hideSpinner = true;
            }
        );
    }
}];
