//■ jQuery

$(function() {

	const btn = document.querySelector('#voice');
	const speech = new webkitSpeechRecognition();
	speech.lang = 'ja-JP';
	var rec;
		  	  
	$("#voice").on("click", function() {
		if(rec != true){
			rec = true;
			$('#voice').removeClass('fas fa-microphone fa-lg');
			$('#voice').addClass('fas fa-spinner fa-pulse fa-lg');
			speech.start();					
		}else{
			rec = false;
			$('#voice').removeClass('fas fa-microphone-slash fa-lg');
			$('#voice').removeClass('fas fa-spinner fa-pulse fa-lg');
			$('#voice').addClass('fas fa-microphone fa-lg');	
			speech.stop();
		}
    });		  	
	
	//■ onresult > 何らかの言語が入力されたら > eに全部入る。
	speech.onresult = (e) => { 
		if (e.results[0].isFinal) {
			console.log(e.results[0][0].transcript);
			$("#content").val($("#content").val() + e.results[0][0].transcript);
			
			$('#voice').removeClass('fas fa-spinner fa-pulse fa-lg');
			$('#voice').addClass('fas fa-microphone-slash fa-lg');
		}
	};

	//■ 話し出したらボタンのラベルが変わる
	speech.onsoundstart = () => {
		$('#voice').removeClass('fas fa-microphone-slash fa-lg');
		$('#voice').addClass('fas fa-spinner fa-pulse fa-lg');
	};
	
	//■ 話終わった時 > (今回は)喋れる状態に戻る
	speech.onend = () => {
		if(rec == true){
			speech.start();				
		}
	};
});