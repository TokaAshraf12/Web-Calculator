package com.cal.cal;

import org.springframework.web.bind.annotation.*;

@RestController 
@CrossOrigin
@RequestMapping("/print")
public class function {
	
	@GetMapping("/calculate1")
	public String calculations1(@RequestParam String expre ) {
		String[] array;
		
		double no1=0.0,no2=0.0,t;
		
        if(expre.startsWith("-") && !expre.contains("--")) {
        	 array = expre.split("[-|×|÷]|[' ']",-2);
        	 t=Double.valueOf(array[1]);
		     no1=-t;
     		 no2=Double.valueOf(array[2]);
        	 
        }else if(expre.startsWith("-") && expre.contains("--")){
       	  expre=expre.replace("--", " ");
       	 array = expre.split(" ");
       	 no1=Double.valueOf(array[0]);
   		 no2=Double.valueOf(array[1]);
       }else if(!(expre.startsWith("--")) && (expre.contains("--"))) {
        expre=expre.replace("--", " ");
       	array = expre.split(" ");
     	    no1=Double.valueOf(array[0]);
  		    no2=Double.valueOf(array[1]);
       }
        else {
        	array = expre.split("[-|×|÷]|[' ']");
        	  no1=Double.valueOf(array[0]);
     		 no2=Double.valueOf(array[1]);
        }
	   
		char p ;
		String oper="";
		for(int i=0;i<expre.length();++i) {
		    p=expre.charAt(i);
			if((p =='-' && i!=0) || p==' ' || p=='÷' || p=='×') {
				oper=String.valueOf(p);
				break;
			}
		}
		double result=0.0;

		String op=String.valueOf(oper);
		switch(op) {
		case" "://+	
		    result=no1+no2;  
			return String.valueOf(result);
		case"-":
			result=no1-no2;
			
			return String.valueOf(result);
		case"×":
			result=no1*no2;
			return String.valueOf(result);
		case"÷":
			if(no2==0) {
				return "E";
			}else {
				return String.valueOf(result);
			}
	
		default:
			 return String.valueOf(0);
			
		}
	}
		@GetMapping("/calculate2")
		public String calculations2(@RequestParam String expres ) {
			char oper=' ';
			String[] arr=new String[10];
			
			arr = expres.split("[% | / | √ | ² | ~]");
			
		
			double no1=Double.valueOf(arr[0]);
			for(int i=0;i<expres.length();++i) {
				if(!Character.isDigit(expres.charAt(i)) && expres.charAt(i)!='.' ) {
					 oper=expres.charAt(i);
				}
			}
			String op=String.valueOf(oper);
			double res;
			String r;
			switch(op) {
			case"%":
				res= no1/100;
				r=String.valueOf(res);
				return r;
			case"/":
				if(no1==0) {
					return "E";
				}else {
					res=1/no1;
					r=String.valueOf(res);
					return r;
				}
				
			case"²":
				res=Math.pow(no1,2);
				r=String.valueOf(res);
				return r;
			case"√":
				if(no1<0) {
					return "E";
				}else {
					res=Math.sqrt(no1);
					r=String.valueOf(res);
					return r;
				}
			   
			case"~":
				if(no1==0) {
					res=0;
					r=String.valueOf(res);
					return r;
				}else {
					res=(-no1);	
					r=String.valueOf(res);
					return r;
				}
				
			
			default:
				return String.valueOf(0);
				
			}
	}

}