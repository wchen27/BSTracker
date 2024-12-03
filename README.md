# BSTracker

## CSC207 Project Group 186 - GriesGaming

![Alt text](src/resources/logo.png "Logo")

## Table of Contents
* [Overview](#overview)

* [User Stories](#userstories)

* [Features and Usage](#features)

* [License](#license)

* [Feedback](#feedback)

## Members:
Wilson Chen - wchen27

Thomas Lascaud - thigamore

Justin Du - dujstn

Zehao Peng - harrypenguin

Lawrence Bae - mgbae-uoft

<a name="overview"></a>
## Overview

Brawl Stars is a mobile game where each user plays a specific character (brawler) and tries to beat their opponents in various game modes in individual matches to gain additional trophies. These matches range from solo game modes to 5v5. Each player’s brawler is able to deal damage to enemy brawlers and score takedowns and assists to help further their team’s progress towards an objective decided by the game mode. The game measures the player’s skill through trophies, where each win will grant the player trophies and each loss will deduct trophies. The player’s brawlers each have their own individual trophy count, as well as a total trophy count for all the brawlers that the player owns. At the end of each match, the player who the game deems the most impactful towards the objective is awarded the title of “Star Player,” with additional titles being granted to the player with the most damage and the most healing. Players can join Clubs, creating groups of players who can compete in different events together. 

This application provides the user with statistics of their recent performance, as well as letting them see the recent performances of other players. In addition, this application lets the user check the statistics of all the users in a given club, quickly allowing users to track their competition when facing off against other clubs. 

<a name="userstories"></a>
## User Stories
### Team Story: 
Grom wants to be able to search different users to be able to see some basic information about them such as win/loss statistics, top brawlers played, and recent matches.

### Wilson: 
As a competitive Brawl Stars Player, I want to view my aggregate performance in recent games to see if I am playing better or worse than in the past. 

### Justin: 
As a player who would like to gain trophies as efficiently as possible, I want to
retrieve a list of the most popular brawlers played in recent games to be able to know which of the brawlers are strong.

### Thomas:
As a player who wants to effectively gain trophies, I want to view relevant statistics (average damage, knockouts, etc. per game) and compare it with my performance to see if I am performing up to par with other players on the same brawler.

### Zehao: 
As a new Brawl Stars player, I want to rank my friends’ (who are in the same club) performance to understand how they play and who is a good player.

### Lawrence:
As a competitive Brawl Stars player, I want to analyze my gameplay and compare it with top players, so I can understand my strengths and weaknesses across different brawlers, maps, and game modes. I also want insights into the performance of my friends and teammates to evaluate if our playstyles align or if adjustments are needed to improve our synergy. Lastly, I want to track my own skill progression over time to see my improvement in various game metrics, such as damage dealt, knockouts, and win rates, which will help me make strategic decisions on which brawlers and strategies to focus on.

<a name="features"></a>
## Features and Usage
### Player Search
By entering a **player tag** and pressing the Search Player button, Brawl Stars Tracker will fetch basic player information and display it to the user. This information includes:
* Username
* User Tag (unique identifier)
* Maximum Trophies
* Current Trophies
* Overview of recent matches

This information allows users to get a quick overview of a player of their choice, allowing them to compare another user's statistics to their own.

### Match Search
By entering a **player tag** and pressing the Search Match button, Brawl Stars Tracker will fetch more complex details about a player's recent matches, as well as calculate some statistics. Each match displays: 
* The result of the match (victory or defeat)
* The trophy change as a result of the match
* The date and time the match was played
* The mode of the match
* The map the match was played on
* The Star Player in the match
  
In addition, various statistics such as the player's winrate in recent matches and the aggregate number of trophies gained or lost in recent matches. This information allows a user to dive deeper into their own or another player's match history in order to check recent performance.

### Club Search
By entering a **club tag** and pressing the Search Club button, Brawl Stars Tracker will fetch the members of that club, ranked by trophy count (high to low). In addition, each player has a clickable button that takes the user to the User Search screen for that particular player. 

## Usage
In order to run the program, the user must have Java installed and a Brawl Stars API key connected to their current IP address. An API key can be obtained here: [Brawl Stars API](https://developer.brawlstars.com/#/)

Then, by running ```Main.java``` the user is then able to use all the features of Brawl Stars Tracker.
<img width="912" alt="image" src="https://github.com/user-attachments/assets/21a399fe-7f6c-4842-9c0e-5dd6471f488f">

The search field can be populated by various different parameters. 
* A **User Tag** is a unique identifier provided to every user in Brawl Stars. Since usernames are not necessarily unique, a User Tag is used instead to search for player information. This User Tag can be found through the Brawl Stars mobile app.
* A **Club Tag** is a unique identifier for a club, or a group of users. These tags can be found through the *Clubs* screen in the Brawl Stars mobile app.

After finding what tag they want to search, the user can then press the corresponding button in order to find which information they want to find. 

**NOTE:** User tags and Club tags are distinct! Do not try to search for a club using a user tag, as doing so is likely to lead to an error. 

<a name="license"></a>
## License
The license for this software can be found in the LICENSE file. Please be sure to check the license before using this software. 

<a name="feedback"></a>
## Feedback
Any feedback about the software can be emailed to ```csc207team1862024@gmail.com```. If you would like to contribute to the development of Brawl Stars Tracker, make a fork of this repository on GitHub and create a pull request. In your pull request, be sure to include comments on what features were added, as well as a short description of how those features were implemented. In addition, ensure that the new features adhere to Clean Architecture and SOLID Design principles in order for your pull request to be reviewed. 
