LearningModdingMod
==================

This mod is based upon many different tutorials and covers most aspects of modding today.
Some assets used are from the tutorials and belong to there respective authors.

Setup development environment:

To setup extract the zip downloaded from the repo to a folder.
Then shift right click and click open command window here.
Then execute without qoutes "gradlew setupDecompWorkspace" 
Then based upon your chosen IDE execute:

"gradlew idea" for intellij idea
or
"gradlew eclipse" for eclipse

Finally open the project in the respective IDE and everything should be setup.

NOTE: if you are using idea the run configurations may not be setup in this case you must have opened the project
at least one and then you can execute in cmd without qoutes "gradlew genIntellijRuns" to create them.

If you want to build execute without qoutes "gradlew build"

To see all the gradle tasks execute without qoutes "gradlew tasks"
