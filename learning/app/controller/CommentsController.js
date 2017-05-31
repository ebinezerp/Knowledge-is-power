var CommentsModule=angular.module('CommentsModule',[]);
CommentsModule.controller('CommentsController',function($cookieStore,$rootScope)
{
    this.message="This is comment controller";
    this.comments={};
   
     cmtCtrl=this;
    $rootScope.currentUser=$cookieStore.get('currentUser');
    cmtCtrl.postComment=function()
    {
        console.log(cmtCtrl.comments);
        console.log(cmtCtrl.comments.users);
        console.log(cmtCtrl.comments.blog);

    }
})