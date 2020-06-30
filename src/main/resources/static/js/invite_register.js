$(function() {
//■ ajaxでユーザー候補を取得する。
	$('#searchUser').on("keyup", function() {
		
		var hostUrl = 'http://localhost:8080/api-user-search/find-by-name';
		var name = $("#searchUser").val();    
		$.ajax({
			url : hostUrl,
			type : 'POST',
//			dataType : 'json', ■ userListがnullの場合 > 強制的にエラーになる > ここをコメントアウトする > なんか上手くいく
			data : {
				 name : name
			},
			async: true
		}).done(function(data) {
			console.log(data);
			console.dir(JSON.stringify(data));
			
			//■ #addSearchUserを検索前の状態に戻す。> 「.empty()」> 子要素のみ消す。
			$("#addSearchUser").empty();
			
			$.each(data, function(index,user) {				
				$("#addSearchUser").append("<div class=\"addUser\" ><input class=\"childAddUser\" type=\"hidden\" value="+user.userId+">" + user.name +"</input></div>");				
//				$("#addSearchUser").append("<input type=\"hidden\" value="+user.userId+">" + user.name +"</input>");				
			});				
			
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

