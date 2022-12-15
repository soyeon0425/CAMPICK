/**
 * 
 */
  function register(){
        	var joinForm = document.joinForm;
        	var id = joinForm.user_id.value;
        	var pw = joinForm.user_pw.value;
        	var pw2 = joinForm.user_pw2.value;
        	var idCheck = joinForm.idCheckResult.value;
        	
        	
        	if(pw.length<8||pw.length>12){
        		alert("비밀번호는 8자 이상 12자 이하로 해주세요.");
        		joinForm.pw.focus();
        		return false;
        	}else if(pw!=pw2){
        		alert("입력하신 비밀번호가 다릅니다.");
        		return false;
        		
        	}else{
        		joinForm.method="post";
        		joinForm.action="user.do?action=register";
        	}
        	}
        
 function idCheck(){
	 
	 var id = joinForm.user_id.value;
	 if(id!=null){

		 var popup = window.open('idCheck.jsp', 'ID중복체크', 'width=500px,height=400px');
	 }
 }
 
	// alert("id check!");
	// var id = joinForm.user_id.value;
	 
	 
	// if(id!=null){
	//	 joinForm.method="post";
	//	 joinForm.action="user.do?action=checkID";
	//	 joinForm.submit();
		 
/*		 if(result){
			 alert('사용 가능한 아이디입니다.');
			 joinForm.idCheckResult.value = "idOK";
			 joinForm.user_id.value = "thisisID";
			 joinForm.user_id.readOnly = true;
			 
		 }else{
			 alert('다른 아이디를 입력해 주세요.');
		 }
		*/
	//	}
     //  }
  
 
 function resultCheck(){
	 
 }