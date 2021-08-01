playerList = {
                "players":[
                {"rank":1, "firstName":"Connor", "lastName":"McDavid", "playerId":8478402, "team":"edm"},
                {"rank":2, "firstName":"Leon", "lastName":"Draisaitl", "playerId":8477934, "team":"edm"},
                {"rank":3, "firstName":"Brad", "lastName":"Marchand", "playerId":8473419, "team":"bos"},
                {"rank":4, "firstName":"Mitchell", "lastName":"Marner", "playerId":8478483, "team":"tor"},
                {"rank":5, "firstName":"Mikko", "lastName":"Rantanen", "playerId":8478420, "team":"col"},
                {"rank":6, "firstName":"Patrick", "lastName":"Kane", "playerId":8474141, "team":"chi"},
                {"rank":7, "firstName":"Auston", "lastName":"Matthews", "playerId":8479318, "team":"tor"},
                {"rank":8, "firstName":"Nathan", "lastName":"MacKinnon", "playerId":8477492, "team":"col"},
                {"rank":9, "firstName":"Mark", "lastName":"Scheifele", "playerId":8476460, "team":"win"}
                ]
            }
leaderList = {
                "points":[
                    {"statVal":"105", "firstName":"Connor", "lastName":"McDavid", "playerId":8478402},
                    {"statVal":"84", "firstName":"Leon", "lastName":"Draisaitl", "playerId":8477934},
                    {"statVal":"69", "firstName":"Brad", "lastName":"Marchand", "playerId":8473419},
                    {"statVal":"67", "firstName":"Mitchell", "lastName":"Marner", "playerId":8478483},
                    {"statVal":"66", "firstName":"Mikko", "lastName":"Rantanen", "playerId":8478420},
                    {"statVal":"66", "firstName":"Patrick", "lastName":"Kane", "playerId":8474141},
                    {"statVal":"66", "firstName":"Auston", "lastName":"Matthews", "playerId":8479318},
                    {"statVal":"65", "firstName":"Nathan", "lastName":"MacKinnon", "playerId":8477492},
                    {"statVal":"63", "firstName":"Mark", "lastName":"Scheifele", "playerId":8476460}
                ],
                "goals":[
                    {"statVal":"105", "firstName":"Connor", "lastName":"McDavid", "playerId":8478402},
                    {"statVal":"84", "firstName":"Leon", "lastName":"Draisaitl", "playerId":8477934},
                    {"statVal":"69", "firstName":"Brad", "lastName":"Marchand", "playerId":8473419},
                    {"statVal":"67", "firstName":"Mitchell", "lastName":"Marner", "playerId":8478483},
                    {"statVal":"66", "firstName":"Mikko", "lastName":"Rantanen", "playerId":8478420},
                    {"statVal":"66", "firstName":"Patrick", "lastName":"Kane", "playerId":8474141},
                    {"statVal":"66", "firstName":"Auston", "lastName":"Matthews", "playerId":8479318},
                    {"statVal":"65", "firstName":"Nathan", "lastName":"MacKinnon", "playerId":8477492},
                    {"statVal":"63", "firstName":"Mark", "lastName":"Scheifele", "playerId":8476460}
                ],
                "assists":[
                    {"statVal":"105", "firstName":"Connor", "lastName":"McDavid", "playerId":8478402},
                    {"statVal":"84", "firstName":"Leon", "lastName":"Draisaitl", "playerId":8477934},
                    {"statVal":"69", "firstName":"Brad", "lastName":"Marchand", "playerId":8473419},
                    {"statVal":"67", "firstName":"Mitchell", "lastName":"Marner", "playerId":8478483},
                    {"statVal":"66", "firstName":"Mikko", "lastName":"Rantanen", "playerId":8478420},
                    {"statVal":"66", "firstName":"Patrick", "lastName":"Kane", "playerId":8474141},
                    {"statVal":"66", "firstName":"Auston", "lastName":"Matthews", "playerId":8479318},
                    {"statVal":"65", "firstName":"Nathan", "lastName":"MacKinnon", "playerId":8477492},
                    {"statVal":"63", "firstName":"Mark", "lastName":"Scheifele", "playerId":8476460}
                ],
                "toi/gp":[
                    {"statVal":"26:23", "firstName":"Drew", "lastName":"Doughty", "playerId":8474563},
                    {"statVal":"26:17", "firstName":"Thomas", "lastName":"Chabot", "playerId":8478469},
                    {"statVal":"26:09", "firstName":"Brent", "lastName":"Burns", "playerId":8470613},
                    {"statVal":"25:38", "firstName":"Darnell", "lastName":"Nurse", "playerId":8477498},
                    {"statVal":"25:14", "firstName":"Seth", "lastName":"Jones", "playerId":8477495},
                    {"statVal":"25:05", "firstName":"Aaron", "lastName":"Ekblad", "playerId":8477932},
                    {"statVal":"25:03", "firstName":"Victor", "lastName":"Hedman", "playerId":8475167},
                    {"statVal":"25:00", "firstName":"Ivan", "lastName":"Provorov", "playerId":8478500},
                    {"statVal":"24:58", "firstName":"Miro", "lastName":"Heiskanen", "playerId":8480036}
                ],
                "sv%":[
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"104", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956}
                ],
                "gaa":[
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"110", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956},
                    {"statVal":"105", "firstName":"David", "lastName":"Pastrnak", "playerId":8477956}
                ]
            }

$("document").ready( function () {
    $.getJSON("https://4232e958-09d2-4b3e-8621-7e53ea616367.mock.pstmn.io/stats", function(data) {
        addStatPanel(data);
    })
    .fail(function() {
        addStatPanel(leaderList);
        console.log("stats data response failed");
    });

    $.getJSON("https://4232e958-09d2-4b3e-8621-7e53ea616367.mock.pstmn.io/fantasy", function(data) {
        addPlayerRanks(data);
    })
    .fail(function() { 
        addPlayerRanks(playerList); 
        console.log("fantasy data response failed");
    });
}); 

/**
 * Adds player rank cards to stat column of landing page
 * @param {JSON LIST} playerList 
 */
function addPlayerRanks(playerList) {
    element = "player_rank_grid"; //doc element to add cards to
    document.getElementById(element).innerHTML = ""; //flush div
    for (let i in playerList.players) { //cycle through players
        playerImg = "https://cms.nhl.bamgrid.com/images/headshots/current/168x168/" + playerList.players[i].playerId + "@2x.jpg"; //save player image src
        teamImg = "../assets/logos/logo_" + playerList.players[i].team + ".svg"; //save player team logo src

        //add player card
        document.getElementById(element).innerHTML +=
        `<div  class="card">
            <img id="card1" src="${playerImg}" class="image_fullw">
            <img src="${teamImg}" class="image_upper_right"><br>
            <h4>${playerList.players[i].firstName} ${playerList.players[i].lastName}</h4>
            <h4>Rank: ${playerList.players[i].rank}</h4>
        </div>`
    }
}

/**
 * Add stat panels on right side of landing page
 * @param {JSON LIST of statistic leaders} leaderList 
 */
function addStatPanel(leaderList) {
    element = "right_column";
    statList = ["Points", "Goals", "Assists", "TOI/GP", "SV%", "GAA"]; //list of stats
    document.getElementById(element).innerHTML = ""; //flush div 
    for (let i in statList) { //create panel for each stat
        addTable(leaderList, element, statList[i]);
    }
}

/**
 * Creates table of leaders for given stat
 * @param {JSON LIST of statistic leaders} leaderList 
 * @param {doc element} element 
 * @param {stat table to be created} stat 
 */
function addTable(leaderList, element, stat) {
    //add table title and top boarder
    document.getElementById(element).innerHTML +=
    `<div class="stat_panel">
        <h4>${stat}</h4>
        <hr color="white">
        <table id="${stat}" style="width:100%">
        </table>
    </div>`;

    for (let i in leaderList[stat.toLowerCase()]) { //add player rows
        playerImg = "https://cms.nhl.bamgrid.com/images/headshots/current/168x168/" + leaderList[stat.toLowerCase()][i].playerId + "@2x.jpg"; //store player img src
        document.getElementById(stat).innerHTML +=
        `<tr>
            <td>${(parseInt(i)+1)}. <img src="${playerImg}" class="image_table"> ${leaderList[stat.toLowerCase()][i].firstName} ${leaderList[stat.toLowerCase()][i].lastName}</td>
            <td>${leaderList[stat.toLowerCase()][i].statVal}</td>
        </tr>`;
    }
}