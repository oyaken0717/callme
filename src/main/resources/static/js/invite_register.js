$(function() {
//■ ajaxでユーザー候補を取得する。
	$('#searchUser').on("keyup", function() {
		
		var hostUrl = 'http://localhost:8080/api-user-search/find-by-like-name-and-user-id-and-group-id';
		var name = $("#searchUser").val();    
		var userId = $("#userId").val();    
		var groupId = $("#groupId").val();    

		$.ajax({
			url : hostUrl,
			type : 'POST',
//			dataType : 'json', ■ userListがnullの場合 > 強制的にエラーになる > ここをコメントアウトする > なんか上手くいく
			data : {
				 name : name,
				 userId : userId,
				 groupId : groupId				 
			},
			async: true
			
//■ doneーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー			
		}).done(function(data) {
			console.log(data);
			console.dir(JSON.stringify(data));
			
			//■ #addSearchUserを検索前の状態に戻す。> 「.empty()」> 子要素のみ消す。
			$("#addSearchUser").empty();
			
//	    	//■ 「招待するユーザー」を配列に入れる。
//			var $obj = $(".invite-list").children();
//console.log($obj);			
			

			//■ 検索にマッチしたユーザーを表示する。
			$.each(data, function(index,user) {				
				$("#addSearchUser").append("<div class=\"addUser\" ><input class=\"childAddUser\" type=\"hidden\" value="+user.userId+">" + user.name +"</input></div>");				
			});				
						
//■ failーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー			
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラーが発生しました！");
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		});
	});

//■ ユーザー一覧から招待候補を確定する。
    $(document).on("click", ".addUser", function() {	
    	var userName = $(this).text();
    	var userId = $(this).find(".childAddUser").val();
    	
    	$(".invite-list").append("<div><input type=\"hidden\" name=\"userList\" value="+userId+">"+userName+"</div>");
    	$(this).remove();
    });

});

