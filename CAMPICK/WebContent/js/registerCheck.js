/**
 * 
 */
  function register(){
        	var joinForm = document.joinForm;
        	var id = joinForm.user_id.value;
        	var pw = joinForm.user_pw.value;
        	var pw2 = joinForm.user_pw2.value;
        	var idCheck = joinForm.idCheckResult.value;
        	
        	
        	if(pw.length<5||pw.length>12){
        		alert("비밀번호는 5자 이상 12자 이하로 해주세요.");
        		joinForm.pw.focus();
        		return false;
        	}else if(pw!=pw2){
        		alert("입력하신 비밀번호가 다릅니다.");
        		return false;
        	}else if(idCheck!="idOK"){
        		alert("id 중복체크는 필수입니다!");
        		return false;
        	}else{
        		joinForm.method="post";
        		joinForm.action="user.do?action=register";
        	}
        	}
        
 function idCheck(){
	 var id = joinForm.user_id.value;

        }