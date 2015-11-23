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
	

	/*
	 * Localization supported
	 */
	$scope.supportedLang = localeSupported;
    $scope.localeData = {
        'en-US': {
            flagClass: 'flag-us',
            langDisplayText: 'English'
        },
        'fr-FR': {
            flagClass: 'flag-fr',
            langDisplayText: 'Français'
        },
        'vi-VN': {
            flagClass: 'flag-vi',
            langDisplayText: 'Tieng Viet'
        }
        
    };

    $scope.currentYear = new Date().getFullYear();
    $scope.fullName = 'Yen Nguyen';

    $scope.setLocale = function (loc) {
    	console.log("setLocale as " + loc);
        locale.setLocale(loc);
    };

    locale.ready('common').then(function () {
    	
    	for (var i =0; i < $scope.supportedLang.length; i++) 
    	{
    		console.log($scope.supportedLang[i]);
    	}
    	$scope.setLocale($scope.supportedLang[2]);
    	console.log('common ready ' + locale.getLocale());
        $scope.flagClass = $scope.localeData[locale.getLocale()].flagClass;
        $scope.langDisplayText = $scope.localeData[locale.getLocale()].langDisplayText;
    });

    $scope.$on(localeEvents.localeChanges, function (event, data) {
    	console.log('Changing locale');
        $scope.flagClass = $scope.localeData[data].flagClass;
        $scope.langDisplayText = $scope.localeData[data].langDisplayText;
    });
    
    
}];

