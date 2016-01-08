var MainController =  ['$scope','$rootScope','$state','$sessionStorage','$location', 'context',
                       'locale', 'localeSupported', 'localeEvents','localStorageService','Standing','Preferences',
                       function($scope, $rootScope, $state, $sessionStorage,$location, context,
                               locale, localeSupported, localeEvents, localStorageService, Standing, Preferences) {
    
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

   var init = function() {

       // Gets server preferences
       var key = "_APP_PREFERENCES_";
       $scope.appPrefs = localStorageService.get(key);
       if ($scope.appPrefs == null) {
           Preferences.pref.query().$promise.then(
               //success
               function (value) {
                   localStorageService.set(key, value);
                   $scope.appPrefs = value;
                   
                   // default one competition
                   $rootScope.saveSelectedCompetition(value[0][0].name);
               },
               //error
               function (error) {
                   // TODO: Handle request returns error
                   console.log("Network error: " + error);
               }
           );

       }
   };

	/*
	 * Localization supported
	 */
	$scope.supportedLang = localeSupported;
    $scope.localeData = {
        'vi-VN': {
            flagClass: 'flag-vi',
            langDisplayText: 'Tiếng Việt'
        },
        'en-US': {
            flagClass: 'flag-us',
            langDisplayText: 'English'
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
    
    /*
     * Saves user preferences to local storage 
     */
    // From a click event
    $rootScope.saveSelectedCompetition = function(name) {
    	if (!angular.isUndefined($scope.appPrefs)) {
    		var competition = findCompetitionByName(name);
    		if (competition != null) {
	    		localStorageService.set("PREFERENCES_COMPETITION", competition);
	    		$scope.selectedCompetition = competition;
	    		$location.search({giai_dau: competition.id});
    		}
    	}
    };
    
    var findCompetitionByName = function(name) {
    	if (!angular.isUndefined($scope.appPrefs)) {
    		var competitions = $scope.appPrefs[0];

    		for (var index in competitions) {
    			if (competitions[index].name == name) {
    				return competitions[index];
    			}
    		}
    	}
    	return null;
    }
    
    $scope.selectedCompetition = localStorageService.get("PREFERENCES_COMPETITION");

    /*
     * Standings service
     */
    $scope.standingsShortlist = Standing.standingsShortlist.query();
    
    $scope.myModel = {
            Url: 'http://jasonwatmore.com/post/2014/08/01/AngularJS-directives-for-social-sharing-buttons-Facebook-Like-GooglePlus-Twitter-and-Pinterest.aspx',
            Name: "AngularJS directives for social sharing buttons - Facebook, Google+, Twitter and Pinterest | Jason Watmore's Blog", 
            ImageUrl: 'http://www.jasonwatmore.com/pics/jason.jpg'
    };

   /*
    * Initialises data for the main controller on page load
    */
   init();
}];

