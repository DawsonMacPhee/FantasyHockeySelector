playerList = {
                "players":[
                {"rank":1, "firstName":"David", "lastName":"Pastrnak", "playerId":8477956, "team":"bos"},
                {"rank":2, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":3, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":4, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":5, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":6, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":7, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":8, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":9, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"}
                ]
            }



$("document").ready( function () {
    document.getElementById("card1").src = "https://cms.nhl.bamgrid.com/images/headshots/current/168x168/8478401@2x.jpg";
    addPlayerRanks(playerList, "player_rank_grid");
}); 

function addPlayerRanks(playerList, element) {
    document.getElementById(element).innerHTML = "";
    for (let i in playerList.players) {
        playerImg = "https://cms.nhl.bamgrid.com/images/headshots/current/168x168/" + playerList.players[i].playerId + "@2x.jpg";
        teamImg = "../assets/logos/logo_" + playerList.players[i].team + ".svg";
        document.getElementById(element).innerHTML +=
        `<div  class="card">
            <img id="card1" src="${playerImg}" class="image_fullw">
            <img src="${teamImg}" class="image_upper_right"><br>
            <h4>${playerList.players[i].firstName} ${playerList.players[i].lastName}</h4>
            <h4>Rank: ${playerList.players[i].rank}</h4>
        </div>`
    }
}