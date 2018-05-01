let cardValue1 = "";
let cardValue2 = "";
let cardOne;
let cardTow;
var clickN = 0;
var movesNew = 0;
var Length = 0;
//Create a list that holds all cards
let confirmList = [];
var starsLength;
var gameBeginH, gameBeginM, gameBeginS;
var H, M, S;
timelength();
//A List with all the Image using Font Awesome  
var cards = ["fa-diamond", "fa-diamond" + " 1", "fa-paper-plane-o", "fa-paper-plane-o" + " 1", "fa-anchor", "fa-anchor" + " 1", "fa-bolt", "fa-bolt" + " 1", "fa-cube", "fa-cube" + " 1", "fa-bicycle", "fa-bicycle" + " 1", "fa-unlink", "fa-unlink" + " 1", "fa-bomb", "fa-bomb" + " 1",]
// Display the cards on the page
creatdeck();
function creatdeck() {
    //get Time of the game when started 
    timelengthBegin();
    //shuffle the list of cards using the provided "shuffle" method below
    cards = shuffle(cards);
    let deck = document.getElementById("deck");
    //loop through each card and create its HTML
    for (let i = 0; i <= 15; i++) {
        const cardlist = document.createElement("LI");
        //add each card's HTML to the page
        deck.appendChild(cardlist);
        cardlist.setAttribute("class", "card");
        const cardI = document.createElement("I");
        cardlist.appendChild(cardI);
        cardI.setAttribute("class", "fa " + cards[i]);
        cardlist.addEventListener("click", function showcarda() {
            classshow = cardlist.getAttribute("class")

            if (classshow == "card open show") {
                console.log(cardlist.getAttribute("class"));
                return;
            }
            else {
                cardlist.setAttribute("class", "card open show");
            }

            //Switch method to match the card 
            switch (clickN) {
                case clickN = 0:
                    cardValue1 = cards[i];
                    cardOne = cardlist;
                    clickN = 1;
                    break;
                case clickN = 1:
                    cardValue2 = cards[i];
                    cardTow = cardlist;
                    clickN = 0;
                    setTimeout(check, 250);
                    break;
            }


        });


    }

};
function check(setTimeout) {

    movesNew = movesNew + 1;
    stars();
    console.log(movesNew);
    if (cardValue1.split(" ")[0] == cardValue2.split(" ")[0] & cardValue1.split(" ")[1] !== cardValue2.split(" ")[1]) {
        cardOne.setAttribute("class", "card match prvent") & cardTow.setAttribute("class", "card match prvent")
        confirmList.push(cardOne, cardTow);
        //win the Game if all cards are matched 


        if (confirmList.length == 16) {
            timelength();
            // confirm from https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm2
            var winBoard = confirm("You Won the Game in " + H + ":" + M + ":" + S + " Hours " + " \n With " + movesNew + "  Moves " + "\n And " + starsLengthT + " Stars " + " \n Play Again?? ");
            if (winBoard == true) {
                location.reload();
            }
            else {
                stopTimer();
                console.log("Canceled");
            }
        }
    }
    else {
        cardOne.setAttribute("class", "card") & cardTow.setAttribute("class", "card")
        stars();
    };

}
//run the Timer when the game start
var timer = setInterval(function () {
    timelength();
    document.getElementById("movesid").innerText = movesNew + " " + " Moves " + H + ":" + M + ":" + S;
}, 1000);

function stopTimer() {
    clearInterval(timer);
}
const restart = document.getElementById("restart");
restart.addEventListener("click", function refresh() {
    location.reload();
});
// Shuffle function from http://stackoverflow.com/a/2450976
function shuffle(array) {
    var currentIndex = array.length, temporaryValue, randomIndex;
    while (currentIndex !== 0) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    } return array;
}
//Get the Game Start Time
function timelengthBegin() {
    gameBeginH = new Date().getHours();
    gameBeginM = new Date().getMinutes();
    gameBeginS = new Date().getSeconds();
}
//Get the Game End Time
function timelength() {
    var HoursE = new Date().getHours();
    var MinutesE = new Date().getMinutes();
    var SecondsE = new Date().getSeconds();
    H = Math.abs(gameBeginH - HoursE);
    M = Math.abs(gameBeginM - MinutesE);
    S = Math.abs(SecondsE - gameBeginS);
}

//Remove One star when move Number equal an Even Number
function stars() {
    if (starsLength <= -1) {
        console.log("You lost")
        starsLengthT = 0;
    }
    else {
        starsLength = document.getElementsByClassName("stars")[0].childElementCount - 1;
        if (movesNew > 8 & movesNew % 2 == 0) {
            var starsS = document.getElementsByClassName("stars")[0];
            starsS.removeChild(starsS.firstChild);
            console.log("A stars was removed " + starsLength);
            starsLengthT = starsLength;
        }

    }
}