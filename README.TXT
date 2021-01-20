------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Tank Battle
PURPOSE OF PROJECT: Have fun. Grade 11 Computer Science  
VERSION or DATE: 2020.11.23
HOW TO START THIS PROJECT:
AUTHORS: Steven Zhu
USER INSTRUCTIONS: 
1.Operation
Red tank: Use arrow keys to move, press space bar to shoot 
Green tank: Use "w", "a", "s", "d" to move, press "j" to shoot

2.Goal
First tank reaches 21 points is the winner!

3.Score
Shoot tank or eat coin can both increase 1 or 2 points. BE CAREFUL, you may kill yourself by your own bullet.

	There are certain challenging problems when I just reached Greenfoot. Although Greenfoot is an integrated development environment using Java, there are many new codes and methods that I haven't heard before. For me, there are two most difficult parts: SimplerTimer and null pointer exception. The SimplerTimer is an import class from Greenfoot, allowing user to keep track of how much time has passed between events. My game requires many SimpleTimer to mark the timer and executes methods after the timer passes a certain time. However, I can't figure out which methods or constructors is the best place to mark the time, leading to an unresponsive result or causing an error.  Frankly, the most time I spend in Greenfoot was dealing with the SimpleTimer and the NullPointerException, the nightmare that I can barely forget. The reason that causing NullPointerException is no object is created and the compiler does not detect it. For instance, when I tries to call a increaseScore method at my both tanks as they shoots down each other, there is always a NullPointerException since the compiler can't detect the coordinates of the current tank as they have been already removed while they got shoot down.
	Frankly, I haven't have enough experiences to solve the NullPointerException and the SimpleTimer. Therefore, I looked up at the best human tool, Google, to solve my problem. There are many great responses toward some common questions in Greenfoot community. However, many of the responses actually did not help me since everyone's project has their unique ideas. Sometimes, I'm really scared that my error can't ever solve since I can't figure out how to fix it. Thankfully, Mr.Chan, our computer science teacher, posted three really useful videos to explain some methods that commonly used in each games. Whenever I had a similar method with Mr.Chan's that I can't figure it out, I watched the video again to familiar the methods. Also, I would like to thank my friend, Benny, who helped me to fix many error when I had troubles. I explained my program and error to him, then we overcame the error together. Overall, I had a deeper understanding toward the use of classes and objects while I created new class and called different methods. And last but not least, I feel great about my accomplishment and I appreciate that I had a such amazing way to learn coding.

Enjoy your game!