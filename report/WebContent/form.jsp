<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
   <style>
   a{
       text-align: center
   }</style>
   <style>
   input.bigcheckbox{
    
      transform: scale(1);
   }</style>
</head>
<body>   
	<h1>WORK IN PROGRESS...</h1><br/>
  
   <h1 style="color: black"><b>PART- I : PERSONAL DATA</b></h1>
   <h1 style="color: black"><b>(to be filled by the employee reported upon)</b></h1>
   <hr>
        <form action="submit.jsp" method="post">
            <div class="personal_details">
                <label>Name of the employee:</label>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type = "text" name = "nameform1" value = "" maxlength = "120" /></div>
                <br>
                <div class="personal_details"> <label>Service to which the employee belongs:</label> 
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  MGMT<input type="checkbox" name="cb1form1" >GIA<input type="checkbox" name="cb2form1" value="GIA">
  <br></div> 
  <div class="personal_details">           
<label > Name of the department to which you belong:</label>
<input type = "text" name = "depform1" value = "" maxlength = "120" /></div>
                <br>
                <div class="personal_details"> 
                    <label>Residential Address:</label> 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#8195;&#8195;&#8195;&#8195;
                
             
                   <textarea rows = "3" cols = "50" name = "Addressform1">
                   
                </textarea></div>
            
            <br>
            <div class="personal_details"> 
            <label>Mobile Number:</label> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type = "text" name = "Mobileform1" value = "" maxlength = "10" /></div>
                <br>
                <div class="personal_details"> 
               <label> E-mail:  </label>
             &#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&nbsp;
                <input type = "text" name = "e-mailform1" value = "" maxlength = "30" /></div>
                                <br>
                                <div class="personal_details"> 
                               <label> Post held during the year/period of report:  </label> 
                                &#8195;&nbsp;&nbsp;               
                <input type = "text" name = "postform1" value = "" maxlength = "30" />
                </div><br>
                <div class="personal_details"> 
                <label>Date of Birth: </label> 
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#8195;
                <input type="date" name='dateofbirthform1'><br>
                Educational Qualification:
                
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#8195;
            
         
                <textarea rows = "3" cols = "50" name = "Educationalform1">
                
             </textarea>
             </div>
             <br>

<button type="submit" value="submit">Submit</button>
     	
         </form>
    </body>
</html>