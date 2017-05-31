var ProfileModule=angular.module('ProfileModule',[]);

ProfileModule.controller('ProfileController',function($cookieStore,$rootScope,$scope){
    var prfCtrl=this;
    $rootScope.currentUser=$cookieStore.get('currentUser');
    prfCtrl.message='this is profile controller';
    $scope.tab='profile'
    this.displayTab=function(tabvalue)
    {
        alert("hello");
        $scope.tab=tabvalue;
    }
})
