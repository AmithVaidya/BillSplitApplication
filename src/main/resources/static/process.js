var members = []
var transactions = []
var counter=0

function deleteRecord(index) {
    members.splice(index, 1);
    mapList();
    counter-=1
    var url='http://localhost:8080/deleteMember/'+index
    fetch(url, {
    	method: 'DELETE',
    })
    .then(res => console.log(res))
    .catch(error => console.log(error))
}



function addMember() {
	counter+=1
    var newName = document.getElementById('newName').value
    var newID = counter //document.getElementById('newID').value
    var newAmount = document.getElementById('newAmount').value
    var temp_obj = {id: parseInt(newID), name:newName, amount:newAmount, balance:0 }
    members.unshift(temp_obj)
    
    

    document.getElementById('newAmount').value = ''
    document.getElementById('newName').value = ''
    
    textChanged()
    mapList()
    addMembers(temp_obj)
}

function deleteMember() {
    alert('Do you want to clear allrecords ?')
    	members=[]
    	mapList()
    	fetch('http://localhost:8080/deleteAllMembers', {
    		method: 'DELETE'
    	})
    	.then(res => console.log(res))
    	.catch(error => console.log(error))
}

function textChanged() {
    var newID = 12
    var newName = document.getElementById('newName').value
    var newSalary = document.getElementById('newAmount').value
    

    if (newSalary.length == 0 || newName.length == 0) {
        document.getElementById('addButton').disabled = true
    }
    else {
        document.getElementById('addButton').disabled = false
    }
}



function mapList() {
    

    var list = ''
    for (var i = 0; i < members.length; i++) {
            list += "<tr class='table-default'>"
            //+ "<td>"
           // + (i+1)
           // +"</td>" 
            + "<td>"
            + members[i].id
            +"</td>"
            + "<td>"

            + members[i].name
            +"</td>"
            + "<td>"
            + members[i].amount
            +"</td>"
            
            +"<td>"
            + "<button class='btn btn-link text-danger' onclick='deleteRecord(" + i + ")'>"
            + "<i class='fas fa-trash'></i>"
            + "</button>"
            +"</td>"
            + "</tr>"
        
    }
    document.getElementById('list').innerHTML = list;
}




function mapTransactions(){

    var content = '<table class="table" ><tr><th>Sender</th><th>Reciver</th><th>Amount</th></tr><tr>'
    for(i=0; i<transactions.length; i++){
        content+='<td>'
            +transactions[i].senderName
            +'</td><td>'
            +transactions[i].recieverName
            +'</td><td>'
            +transactions[i].amount
            +'</td></tr>'
    }
    content+='</tr></table>'

    document.getElementById('Transactions').innerHTML = content;
}

/*
function filter(){
    searchVal = document.getElementById('searchVal').value
    document.getElementById('searchVal').value=''
    var list = ''
    for (var i = 0; i < members.length; i++) {
    if(
        members[i].salary==searchVal ||
        members[i].id == searchVal ||
        members[i].name == searchVal
    )
        {        list += "<tr class='table-default'>"
            + "<td>"
            + (i+1)
            +"</td>"
            + "<td>"
            + members[i].name
            +"</td>"
            + "<td>"

            + members[i].id
            +"</td>"
            + "<td>"
            + members[i].salary
            +"</td>"
            
            +"<td>"
            + "<button class='btn btn-link text-danger' onclick='deleteRecord(" + i + ")'>"
            + "<i class='fas fa-trash'></i>"
            + "</button>"
            +"</td>"
            + "</tr>"
    }
        
    }
    document.getElementById('list').innerHTML = list;
}

 */

 function getTransactions(){
   fetch('http://localhost:8080/getTransactions')
   .then(res => res.json())
   .then(data => {
   	transactions = data
   	mapTransactions()
   })
   .catch(error => {
	   console.log("ERROR")
   })
   
}

function addMembers(member) {
	fetch('http://localhost:8080/addMember', {
		method:'POST',
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(member)
	})
	.then(res => console.log(res))
	.catch(error => {
		console.log(error)
	})
}
