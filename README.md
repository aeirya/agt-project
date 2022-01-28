# Algorithmic Game Theory Course Project

The goal of this project is to simulate a multi-agent PoW blockchain system in order to study consensus within a network of miners.

*Instructor: Prof. Mojtaba Tefagh*

Fall 2021

## How it works

Each game consists of *players* who can choose their *strategy* each round and their strategy chooses thier *action* based on its implementation.

Classes are kept abstract and two type of games have been implemented; The game in evolution of trust and a *blockchain game*, in which there's a 
forkable blockchain where players could fight over gaining the most reward.


## Results
Primary results are available at [link to the slides (requires access)](https://docs.google.com/presentation/d/1jq2OEZZU3zC5GOrO7TOuvmvNi01CRXhmQpGdU2cr8qI/edit#slide=id.g10b4953eb45_0_0). In summary, in a blockchain where everybody is rational (i.e either plays honestly or maliciously), playing maliciously is the dominant strategy and the main chain consists of invalid blocks, but in the presence of some honest players, the main chain will only consist of valid blocks, and rational players will "learn" to use honest strategy.

## Contributors
Aeirya Mohammadi, Taha Enayat, AmirHossein Jadidi
 
## Reference
[Reward Mechanism for Blockchains Using Evolutionary Game Theory](
https://arxiv.org/pdf/2104.05849.pdf
)

