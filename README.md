# StarSearch

Team 20: Star Search
Antonio V. Alvillar
Gabriel Houle

Valid User: 
	User Name = bob1
	Password = 1234
Following are test cases that were used: 
* Search for a Star: 
o Valid Star: Alpha Sagittarii 
* Will Return and display information about the star and put the search bar at the top for future searches. Also, will activate the return to home button.
o Invalid Star: alpaca Sagittaris
* Will display a message that there are no stars by that name.
* After Searching: 
o Return to home will be activated.
* When pressed, the home page is reloaded and just as application was newly opened. 
* Log In:
o Valid User: User Name = bob1    and Password = 1234
* You will be logged in with a Welcome message at the top. Upon searching the view will be specific to a user. Display Favorites and Add to Favorites buttons will both activate if there is a star returned but if not then only Display Favorites will activate as you cannot favorite a star that does not exist. Instead of return to home, users have a log out button that will return to home as well as log the user out. 
o Invalid User: User Name = Robert  and Password = 1112
* You will receive a message for invalid input. Cannot proceed without registering. You may still search of course. 
* New Account:
o New user: Input your information, the system will check and warn you for invalid information such an email not correctly formatted or if there are blank fields, also if the username or email exist already. To prevent SQL injection single quotes are not permitted. If information is correct you will see a dialog box open indicated that your new account has been added. You may now sign in like normal. No need to restart the program. 
* Logged In As User: 
o Search for a star: Zeta Sagittarii
* Add star to favorite. You will get a message saying that the star was added and you can view it in your display favorite’s button now. 
o Display Favorites: 
* Your list of favorites will display one at a time and you can push next to see any other stars in your list or circle back. 
* From display favorites you can also delete favorites. If you have no favorites then the delete and next buttons will be inactive. 
* You can also leave a comment from the display favorites windows but also rate that particular star. 
* You can also edit a favorite stars comments or rating just by overwriting it and pressing Update Comment/Rating.
o Log out: If you log out there will be no trace of your log in so you can feel confident that you will need to resign in to continue as a user or just search without logging in.
Java Files: 
* StarSearch: Project  (GIT) https://github.com/ghoule636/StarSearch.git
o SRC Folder
* golden_star.png (was going to be background image)
* night_sky.jpg (used as frame icon)
o Model Package: 
* DBAccess
* DBConnection
* Favorite
* Star
* User
o View Package:
* FavoritesPanel
* GhostText
* HomePage
* Main
* NewUser
* ResultsPanel

