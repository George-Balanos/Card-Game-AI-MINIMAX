# Card-Game-AI-MINIMAX
Card Game between 2 players(Player vs PC) that utilizes MINIMAX algorithm.

Short summarization of the game:

* There are M identical cards on the table, divided into K groups. Each group Oi (i=1,…,K) initially contains Ai≥2 cards, and the sum of all Ai equals M.
* During the game, each player takes a turn and removes one or more cards from a single group.
* For each group Oi, there's a maximum number of cards Bi (Bi<Ai) that a player can remove in a single turn.
* The player who removes the last card from the table wins the game.

MAX -> PC
MIN -> PLAYER

PC aims to lead the game to a state which has positive "score".On the other hand the player intent to guide the game to a state with negative "score".
This is achieved with each movement(choice) that is made on every round.

"MAX" pre-evaluates the game tree in advance, considering the provided inputs such as the number of card teams, and then makes decisions during each turn
based on the available paths that hold the potential for winning the game.

The value of the root in the game tree can give a quick answer to who can win. 
* If valueOfRoot == 1 then MAX (always) wins regardless of the player's movements.
* If valueOfRoot < 0 then MIN can possibly win.
