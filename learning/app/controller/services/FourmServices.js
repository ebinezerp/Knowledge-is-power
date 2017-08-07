FourmModule.service('FourmServices',function($http,$q,REST_URI){
    this.postFourm=function(fourm)
    {
        console.log(fourm);
        var deffered=$q.defer();
        $http.post(REST_URI+'postFourm',fourm).then(
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
        )
        return deffered.promise;
    }

    this.allFourmsFun=function()
    {
        var deffered=$q.defer();
        $http.get(REST_URI+'allFourms').then
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
        )

        return deffered.promise;
    }

    this.getFourm=function(fourmId)
    {
        var deffered=$q.defer();
        $http.get(REST_URI+'getFourm/'+fourmId).then
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
        )
        return deffered.promise;
    }

})