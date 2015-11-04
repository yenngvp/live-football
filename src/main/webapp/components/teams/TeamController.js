var TeamController = ['$scope','$state','Team',function($scope,$state,Team) {

	$scope.teams = Team.query();
}];


var TeamDetailsController = ['$scope','$rootScope','$stateParams','Team', function($scope,$rootScope,$stateParams,Team) {

	var currentId = $stateParams.id;
	var nextId = parseInt($stateParams.id) + 1;
	var prevId = parseInt($stateParams.id) - 1;

	$scope.prevTeam = Team.get({id:prevId});
	$scope.nextTeam = Team.get({id:nextId});
	$scope.currentTeam = Team.get($stateParams);

	/*
	$scope.saveTeam = function(){
		Team = $scope.currentTeam;
		Team.save(Team);
	}
	
	$scope.addPet = function() {
		$scope.petFormHeader = "Add a new Pet";
		$scope.currentPet = {type:{}};
	}
	
	$scope.editPet = function(id) {
		$scope.petFormHeader = "Edit Pet";
		for(i = 0;i < $scope.currentTeam.pets.length; i++) {
			if($scope.currentTeam.pets[i].id == id) {
				$scope.currentPet = $scope.currentTeam.pets[i];
				break;
			}
		}
	};
	 */
}];

/*
var AddTeamController = ['$scope','Team', function($scope,Team) {

	$scope.Team={id:0,pets:[]};

	$scope.addTeam = function(){
		Team.save($scope.Team);
	}
}];
*/