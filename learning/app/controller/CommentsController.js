var CommentsModule=angular.module('CommentsModule',[]);
CommentsModule.controller('CommentsController',function($cookieStore,$cookies,$rootScope,CommentsServices)
{
    this.message="This is comment controller";
    this.comments={};
    cmtCtrl=this;
    
 
    $rootScope.currentUser=$cookieStore.get('currentUser');
    
   
    console.log('indiblog in cmt controllr::::'+$rootScope.indiblogId);
 
    cmtCtrl.postComment=function()
    {
        cmtCtrl.comments.users=$rootScope.currentUser;
        //cmtCtrl.comments.blog=cmtCtrl.comments.blog;
           angular.forEach($rootScope.AllBlogs,function(value,key)
    {
         console.log(value);
         console.log($rootScope.indiblogId==value.blogId);
        if($rootScope.indiblogId==value.blogId)
        {

            cmtCtrl.comments.blog=value;
        }
    })

        console.log($rootScope.currentBlog);
        console.log(cmtCtrl.comments);
        console.log(cmtCtrl.comments.users);
        console.log(cmtCtrl.comments.blog);
        
        CommentsServices.postComment(cmtCtrl.comments).then
        (
            function(response)
            {
                console.log(response);
                $rootScope.allBlogComments=response.data;
                cmtCtrl.comments.blogComment='';
            },
            function(error)
            {
                console.log(error);
            }
        )

    }


    this.blogComments=function(blogId)
    {
        CommentsServices.blogComments(blogId).then
        (
            function(success)
            {
                console.log(success);
                $rootScope.allBlogComments=success.data;
            },
            function(error)
            {
                console.log(error);
            }
        )
    }
   
   this.blogComments($rootScope.indiblogId);
})




