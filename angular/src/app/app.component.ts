import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpClientModule} from '@angular/common/http';
import { concat } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'calculator ';

  
  input = '0';
  b:any
  result:any
  constructor(private http:HttpClient){}


 
press(num:string){  
      if(num==="-" && this.input==="0"){
        this.input=num
      }else{
        this.input=this.input+num
      }
  }
  
clear() {
  if (this.input !="0" ) {
    this.input=this.input.substr(0, this.input.length-1)
  }
}

allClear() {
  this.input = '0';
  this.result = '';
  this.b = '';
}

sendresult(y:string){
  this.http.get('http://localhost:5156/print/calculate1',{
    responseType:'text',
    params:{
      expre:y,
    },
    observe:'response'   
  }).subscribe(response=>{
    this.result=response.body
    this.input=this.result
    if(this.result==="\"Infinity\""){
      console.log("E")
    }else{
      console.log(this.result)
    }
   
    
   
  })
}
getAnswer(){
  this.sendresult(this.input)

}

send2(y:string){
  this.http.get('http://localhost:5156/print/calculate2',{
    responseType:'text', 
    params:{
      expres:y,  
    },
    observe:'response'   
  }).subscribe(response=>{
    this.result=response.body
    this.input=this.result
    console.log(this.result)
    
  })
}
getAnswer2(z:String){

  this.input=this.input+z
  this.send2(this.input)
}


}
