var AdminModule=angular.module('AdminModule',['ngRoute']);
AdminModule.controller('AdminController',function($rootScope)
{
    var admin=this;
    this.test='testing';
    this.menuItem='jobs';
   
    this.menu=function(menuItem)
    {
        console.log(menuItem);
         admin.menuItem=menuItem;
         if(menuItem=='jobs')
         {
             $rootScope.newjob=false;
         }
    }
    
});







