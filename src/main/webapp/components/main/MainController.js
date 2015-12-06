var MainController =  ['$scope','$rootScope','$state','$sessionStorage', 'context',
                       'locale', 'localeSupported', 'localeEvents',
                       function($scope, $rootScope, $state, $sessionStorage, context,
                               locale, localeSupported, localeEvents) {
    
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
	

	$scope.timeIndicator = function($date) {
		var now = new Date();
		var ahead = new Date(parseInt($date, 10));
		var diff = ahead - now;
		var dayNow = now.getDay(); // Day in week (0-6)
		var dayAhead = ahead.getDay();
		
		if (diff == 0) {
			// Today
			console.log(toString(ahead) + ' is today');
		} else if (diff > 0 && diff <= 7) {
			// Future within 7 days but needs to check it is in the week or next week
			if (dayAhead > dayNow) {
				// Same week
				console.log(toString(ahead) + ' is in this week');
			} else {
				// day ahead is next week
				console.log(toString(ahead) + ' is next week');
			}
		} else if (diff > 7 && diff < (dayNow + 7 * dayAhead)) {
			// day ahead is still within next week
			console.log(toString(ahead) + ' is next week');
			
		}
	};
			
	
	/*
	 * Localization supported
	 */
	$scope.supportedLang = localeSupported;
    $scope.localeData = {
        'en-US': {
            flagClass: 'flag-us',
            langDisplayText: 'English'
        },
        'vi-VN': {
            flagClass: 'flag-vi',
            langDisplayText: 'Tiếng Việt'
        },
        'fr-FR': {
            flagClass: 'flag-fr',
            langDisplayText: 'Français'
        }
    };

    $scope.currentYear = new Date().getFullYear();
    $scope.fullName = 'Yen Nguyen';

    $scope.setLocale = function (loc) {
        locale.setLocale(loc);
    };

    locale.ready('common').then(function () {
        $scope.flagClass = $scope.localeData[locale.getLocale()].flagClass;
        $scope.langDisplayText = $scope.localeData[locale.getLocale()].langDisplayText;
        
        $rootScope.dateFormatString = locale.getString('common.dateFormat');
    });

    $scope.$on(localeEvents.localeChanges, function (event, data) {
    	$rootScope.dateFormatString = locale.getString('common.dateFormat');
    	
        $scope.flagClass = $scope.localeData[data].flagClass;
        $scope.langDisplayText = $scope.localeData[data].langDisplayText;
    });
    
    
}];

