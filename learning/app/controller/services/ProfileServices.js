ProfileModule.service('ProfileServices',function($http,$q,REST_URI){
    this.editProfile=function(user)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+"updateUser",user).then
        (
            function(response)
            {
                console.log(response);
                deffered.resolve(response);
            },function(error)
            {
                deffered.reject(error);
            }
        )
        return deffered.promise;
    }


    this.imageUpload=function(image)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+"/imageUpload",image,{
			transformRequest:angular.indentity,
			headers:{'Content-Type': undefined}
		}).then
        (
            function(response)
            {
                console.log(response);
                deffered.resolve(response);
            },function(error)
            {
                console.log(error);
                deffered.reject(error);
            }
        )

        return deffered.promise;

        
    }
})