BlogModule.service('BlogServices',function($http,$q,REST_URI){
    this.saveBlog=function(blog,userId)
    {
        console.log('user id'+userId);
        var deffered=$q.defer();
        $http.post(REST_URI+'blog/'+userId,blog).then(
            function(response)
            {
                console.log(response);
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

    this.allblogs=function()
    {
        console.log('entered');
           var deffered=$q.defer();
           $http.get(REST_URI+'allblogs').then
           (
            
               function(response)
               {
                   console.log("All blog service response"+response);
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
