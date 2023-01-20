

var expsenses = [];
var senders = [];
var receivers = [];
var total=0, avg=0;
var users = [];
var counter = 1;


function addUser(){
    users.push({"name":document.getElementById('newUser').value, "id":counter});
    counter++;
    document.getElementById('userList').innerHTML 
        += '<option value='+(counter-1)+'>'+document.getElementById('newUser').value+'</option>';
}


function addExpense(){
    let uid = document.getElementById('userList').value;
        uid = Number.parseInt(uid);
//    let userName = document.getElementById('userName').value;
    let amountPaid = document.getElementById('amountPaid').value;
        amountPaid = Number.parseFloat(amountPaid);
    let description = document.getElementById('description').value;

    let expense = {
        "userId": Number.parseInt(uid),
        "userName": getNameFor(uid),
        "amountPaid":Number.parseFloat(amountPaid),
        "description":description
    };

    //Calculations
    expsenses.push(expense);
    total += amountPaid;
    avg = total/users.length;

    //Rendering display
    let expenseTableRef = document.getElementById('expenseTable');

    expenseTableRef.innerHTML += 
    '<tr>'+
        '<td>'+getNameFor(uid)+'</td>'+
        '<td>'+amountPaid+'</td>'+
        '<td>'+description+'</td>'+
    '</tr>';
}


function calculateExpenses(){
    for(let user of users){
        calculateExpenseFor(user.id);
    }

    let display = document.getElementById('display');
    display.innerHTML += '<p>Total: Rs.'+total+'</p><p>Per Person expense: Rs.'+avg+'</p>';
    generateTransactions();
}


function calculateExpenseFor(uid){
    let amtSpent=0;
    for(let expense of expsenses){
        if(expense.userId == uid)
            amtSpent += expense.amountPaid;
    }

    if((amtSpent - avg) > 0)
        receivers.push({"userId": uid, "amount":(amtSpent - avg), "name": getNameFor(uid)});
    else if((amtSpent - avg) < 0)
        senders.push({"userId": uid, "amount":(avg-amtSpent), "name":getNameFor(uid)});
    else { /*expense settled.*/ }


}


function getNameFor(uid){
    for(let user of users)
        if(user.id == uid)
            return user.name;
}

function generateTransactions(){
    /* 
        1. loop through receivers
        2. Check paying capacity
        3. settle for each receiver and iterate
    */

    for(let receiver of receivers){
        for(let sender of senders){
            if((sender.amount >= receiver.amount) && sender.amount!=0 && receiver.amount!=0){ 
                /* Current sender will settle receiver*/
                displayTransaction(sender, receiver, sender.amount);
                sender.amount = sender.amount - receiver.amount;
                receiver.amount = 0;
            }
            else if((receiver.amount > sender.amount) && sender.amount!=0 && receiver.amount!=0) { 
                /*Current sender will pay all to current receiver */
                displayTransaction(sender, receiver, sender.amount);
                receiver.amount = receiver.amount - sender.amount;
                sender.amount = 0;
            }
            else {
                /*Transaction Settled. */
            }
        }
    }
}


function displayTransaction(sender, receiver, amount){
    //Code to render transaction table.
    var dispContent = document.getElementById('display');
    dispContent.innerHTML +=
    '<p>'+
    getNameFor(sender.userId)+'('+sender.userId+') pays '+ getNameFor(receiver.userId)+'('+receiver.userId+') Rs.'+amount+
    '</p>';
}

