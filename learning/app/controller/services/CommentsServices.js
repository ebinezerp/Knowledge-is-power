CommentsModule.service('CommentsServices',function($http,$q,REST_URI)
{
    this.postComment=function(blogComment)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+'postcomment',blogComment).then
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



    this.blogComments=function(blogId)
    {
        var deffered=$q.defer();
        $http.get(REST_URI+'allcomments/'+blogId).then
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