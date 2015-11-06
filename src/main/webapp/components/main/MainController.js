var MainController =  ['$scope','$rootScope','$state','$sessionStorage', 'context', function($scope, $rootScope, $state, $sessionStorage, context) {
    
	$scope.$storage = $sessionStorage;
	
	$scope.getSession = function() {
		return $scope.$storage.session;
	};
	
	$scope.login = function() {
		$scope.$storage.session = { 'username' : 'test' };
		$state.go('dashboard');
	};
	
	$scope.logout = function() {
		$scope.$storage.session = null;
		$state.go('dashboard');
	};
	
	$scope.goHome = function() {
		$state.go('dashboard');
		
		/*
		if ($scope.getSession() == null) {
			$state.go('landing');
		} else {
			$state.go('dashboard');
		}*/
	}
	
	
	$scope.menuTabs = [ {
		'name' : 'dashboard',
		'url' : '#',
		'font' : 'fa fa-home'
	}, {
		'name' : 'matchdays',
		'url' : '#matchdays',
		'font' : 'fa fa-eyedropper'
	}, {
		'name' : 'teams',
		'url' : '#teams',
		'font' : 'fa fa-paw'
	}, {
		'name' : 'standings',
		'url' : '#standings',
		'font' : 'fa fa-user'
	}, {
		'name' : 'stats',
		'url' : '#stats',
		'font' : 'fa fa-question'
	}, {
		'name' : 'Brackets',
		'url' : '#',
		'font' : 'fa fa-question'
	}
	];
	
	$scope.context = context;
	
	$scope.footerText = '© ' + new Date().getFullYear() + ' Yen Nguyen. Alrights reserved!';
	
	$rootScope.$state = $state;
	
	$rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
		var requireLogin = toState.data.requireLogin;

		if (requireLogin && $scope.getSession() == null) {
			event.preventDefault();
			$state.go('dashboard');
		}
	});
}];

