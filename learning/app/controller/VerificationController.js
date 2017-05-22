var VerificationModule=angular.module('VerificationModule',[]);
VerificationModule.controller('verificationController',function($routeParams,verificationService,$location){


   this.verify=function()
   {
     alert($routeParams.id);
     verificationService.verify($routeParams.id).then
(
         function(success)
         {
             console.log(success);
             if(success.status=='200')
             {
                 $location.path('/login');
             }
         },
         function(error)
         {
             console.log(error);
         }
     )
   }

   this.verify();

}

);


VerificationModule.service('verificationService',function($http,$q,REST_URI)
{
    this.verify=function(id)
    {
    var deffered=$q.defer();
    $http.get(REST_URI+'verify/'+id).then
    (
        function(success)
        {
            console.log(success);
            deffered.resolve(success);
        },
        function(error)
        {
            console.log(error);
            deffered.reject(error);
        }
    );
    return deffered.promise;
    }
    
})