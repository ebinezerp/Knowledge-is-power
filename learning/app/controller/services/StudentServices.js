StudentModule.service('StudentServices',function($http,$q,REST_URI){

     
     this.alljobs=function()
     {
        var deffered=$q.defer();
        $http.get(REST_URI+'allJobs').then
        (
            function(response)
            {
                console.log(response);
                console.log('service');
                deffered.resolve(response);
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