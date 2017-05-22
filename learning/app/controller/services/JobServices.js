var multipartFile=JobModule.service('multipartFile',function(REST_URI,$q,$http,$location,$rootScope){
    this.post=function(data)
	{
		
		 console.log('data in  multipart file service::::::'+data.jobTitle);
		var fd=new FormData();
		for(var key in data)
		{
			 console.log(key+":::"+data[key]);
			fd.append(key, data[key]);
		}
		console.log('form data'+fd);
		console.log(fd.companyLogo);
		return fd;
	}

	this.httpPost=function(uploadUrl,result,jobCtrl)
	{
		var deffered=$q.defer();
		$http.post(REST_URI+'newjob',result,{
			transformRequest:angular.indentity,
			headers:{'Content-Type': undefined}
		}).then
		(
			function(response)
			{
				console.log(response);
				console.log('helllo');
                console.log(response.data);
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

	this.alljobs=function()
	{
		var deffered=$q.defer();
		$http.get(REST_URI+'allJobs').then(
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
})