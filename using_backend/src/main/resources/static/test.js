
  
 var membersObj = [{id: 10, name: 'Amith1', amount: 1001, balance: 100}]
  
  function send(){

    fetch('http://localhost:8080/addMembers', {
    	method:'POST',
    	headers: {
    		"Content-Type":"application/json"},
    	
    	body: JSON.stringify(membersObj)
    })
    .then(data => console.log(data))
}