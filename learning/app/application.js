var app=angular.module('Learning',[
    'ngRoute',
    'ui.router',
    'LoginModule',
    'AdminModule',
    'JobModule',
    'ngCookies',
    'StudentModule',
    'VerificationModule',
    'BlogModule',
    'ProfileModule',
    'CommentsModule',
    'ChatModule',
    'angularUtils.directives.dirPagination'
    
]);

var app=angular.module('Learning');

app.controller('HomeController',function($scope,$cookies,$route,$rootScope,$location,$cookieStore){
    $scope.name='ebinezer';
    this.name='ebinezer';
    $rootScope.userrole=$cookies.get('role');

    $rootScope.authentiated=$cookies.get('authentiated');

    this.logout=function()
    {
        $cookies.remove('authentiated');
        $cookies.remove('role');
        $cookieStore.remove('currentUser');
        $rootScope.currentUser={};
        $rootScope.authentiated=false;
        $rootScope.userrole='';
        $route.reload();
        $location.path("/");
        
        
    }

   

});

app.constant('REST_URI','http://localhost:8090/project/')
app.config(function($routeProvider,$stateProvider){
$routeProvider.when("/home",{
    templateUrl:'./app/pages/home.html',
    controller:'HomeController',
    controllerAs:'home'
})
.when("/aboutus",{
    templateUrl:'./app/pages/aboutus.html',
    controller:'AboutController',
    controllerAs:'about'
})
.when("/login",{
    templateUrl:'./app/pages/login.html',
    controller:'LoginController',
    controllerAs:'login'
})
.when("/signup",{
    templateUrl:'./app/pages/registration.html',
    controller:'RegController',
    controllerAs:'regCtrl'
})
.when("/trainer",{
    templateUrl:'./app/pages/trainer.html',
    controller:'TrainerController',
    controllerAs:'tranCtrl'
})
.when("/admin",{
    templateUrl:'./app/pages/admin.html',
    controller:'AdminController',
    controllerAs:'adminCtrl'
})
.when("/student",{
    templateUrl:'./app/pages/student/student.html',
    controller:'StudentController',
    controllerAs:'stdCtrl'
})
.when("/verification/:id",{
    templateUrl:'./app/pages/student/verification.html',
    controller:'verificationController'
})
.when("/indiblog/:id",{
    templateUrl:'./app/pages/student/indivisualblog.html',
    controller:'BlogController',
    controllerAs:'blogCtrl'
})
.otherwise({
    redirect:'/index.html',
    controller:'HomeController',
    controllerAs:'home'
})

})

